package com.shadley000.alarms.pages;

import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.NoteBean;
import com.shadley000.alarms.db.NotesDB;
import com.shadley000.histogram.Histogram2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class PageAlarmDetail extends Page
{

    public PageAlarmDetail()
    {

    }

    @Override
    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        String selectedTagName = request.getParameter(IConstants.PARAM_TAGNAME);

        processNotesRequest(connection, request);

        List<List<AlarmRecordBean>> activeList = new ArrayList<>();

        List<NoteBean> notesList = null;
        List<AlarmRecordBean> tagHistory = null;

        if (selectedTagName != null && !selectedTagName.trim().equals(""))
        {
            tagHistory = AlarmsDB.getAlarmListbyTag(connection, userBean, selectedTagName);
            notesList = NotesDB.getNotesByTagName(connection, userBean, selectedTagName);

            List<AlarmRecordBean> history = new ArrayList<>();

            PreparedStatement stmt = AlarmsDB.getSelectAlarms(connection,userBean,true,null);
         
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                AlarmRecordBean record = new AlarmRecordBean(rs);

                history.add(0, record);

                while (record.getAlarmTime().getTime() - history.get(history.size() - 1).getAlarmTime().getTime() > 2 * ONE_MINUTE || history.size() > 100)
                {
                    history.remove(history.size() - 1);
                }

                if (selectedTagName.equals(record.getTagName()))
                {
                    List<AlarmRecordBean> historyCopy = new ArrayList<>();

                    for (Iterator<AlarmRecordBean> it = history.iterator(); it.hasNext();)
                    {
                        historyCopy.add(it.next());
                    }
                    activeList.add(historyCopy);
                }

            }
            rs.close();
            stmt.close();

        }

        Histogram2D histogram = new Histogram2D();
        for (List<AlarmRecordBean> history : activeList)
        {
            AlarmRecordBean recordFirst = null;

            for (AlarmRecordBean record : history)
            {
                if (recordFirst == null)
                {
                    recordFirst = record;
                }

                long deltaTime = recordFirst.getAlarmTime().getTime() - record.getAlarmTime().getTime();
                String column = "";

                if (deltaTime < ONE_MINUTE)
                {
                    if (deltaTime < ONE_SECOND)
                    {
                        column = "1 " + userBean.getLabelsBean().getSeconds();
                    } else if (deltaTime < 10 * ONE_SECOND)
                    {
                        column = "10 " + userBean.getLabelsBean().getSeconds();
                    } else if (deltaTime < 30 * ONE_SECOND)
                    {
                        column = "30 " + userBean.getLabelsBean().getSeconds();
                    } else if (deltaTime < ONE_MINUTE)
                    {
                        column = "60 " + userBean.getLabelsBean().getSeconds();
                    }

                    histogram.increment(column, (AlarmTypeBean) record, 1);
                }
            }
        }

        ReportBean reportBean = new ReportBean("/AlarmDetail.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM, histogram);
        reportBean.setDataObject(ReportBean.DO_MULTI_HISTORY_LIST, activeList);
        reportBean.setDataObject(ReportBean.DO_NOTES_LIST, notesList);
        reportBean.setDataObject(ReportBean.DO_ALARM_RECORD_LIST, tagHistory);
        return reportBean;
    }
    
     public void processNotesRequest(Connection connection, HttpServletRequest request) throws Exception
    {

        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        String selectedTagName = request.getParameter(IConstants.PARAM_TAGNAME);

        String newNote = request.getParameter(IConstants.PARAM_NOTE_TEXT);
        String noteIDstr = request.getParameter(IConstants.PARAM_NOTE_ID);
        String flagIDstr = request.getParameter(IConstants.PARAM_FLAG_ID);
        String mode = request.getParameter(IConstants.PARAM_NOTE_MODE);

        try
        {
            if (mode != null)
            {
                if (mode.equals(IConstants.MODE_ADD))
                {
                    if (newNote != null && selectedTagName != null && !selectedTagName.trim().equals(""))
                    {
                        NotesDB.insertNote(connection, userBean, selectedTagName, Integer.parseInt(flagIDstr), newNote);
                    }
                } else if (mode.equals(IConstants.MODE_DELETE))
                {
                    if (noteIDstr != null)
                    {
                        NotesDB.deleteNote(connection, userBean, Integer.parseInt(noteIDstr));
                    }
                } else if (mode.equals(IConstants.MODE_UPDATE))
                {
                    if (noteIDstr != null &&flagIDstr!=null && newNote!=null )
                    {
                        int noteID = Integer.parseInt(noteIDstr);
                        int flagID = Integer.parseInt(flagIDstr);
                        NotesDB.updateNote(connection, userBean, noteID, flagID, newNote);
                    }
                }
            }
        } catch (NumberFormatException e)
        {
            //message
        }
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getAlarmDetailReport();
    }

}
