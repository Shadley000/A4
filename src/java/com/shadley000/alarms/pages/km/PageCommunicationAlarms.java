package com.shadley000.alarms.pages.km;

import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.pages.Page;
import com.shadley000.histogram.Histogram2D;
import java.sql.Connection;
import java.util.Iterator;
import java.util.SortedSet;
import javax.servlet.http.HttpServletRequest;

public class PageCommunicationAlarms extends Page
{

    static String[] comm_message_types =
    {
        "Comm", "comm", "Com.", "IO Error", "Feedback fault", "Profibus Error", "RIO: Open loop/Cable break", "IO error", "Input signal error", "Checksum error"
    };

    public PageCommunicationAlarms()
    {

    }

    public boolean messageTypeTest(String messageType)
    {
        for (int i = 0; i < comm_message_types.length; i++)
        {
            if (messageType.contains(comm_message_types[i]))
            {
                return true;
            }

        }
        return false;
    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        Histogram2D alarmTypeHistogram = AlarmsDB.getUniqueAlarms(connection, userBean);

        SortedSet<Object> orderedRows = alarmTypeHistogram.getRows();

        Histogram2D communicationAlarmsHistogram = new Histogram2D();

        for (Iterator<Object> orderedRowsIterator = orderedRows.iterator(); orderedRowsIterator.hasNext();)
        {
            AlarmTypeBean type = (AlarmTypeBean) orderedRowsIterator.next();
            if (messageTypeTest(type.getMessageType()))
            {
                communicationAlarmsHistogram.put(type, alarmTypeHistogram.getHistogram(type));
            }
        }

        ReportBean reportBean = new ReportBean("/CommunicationAlarms.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM, communicationAlarmsHistogram);
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getCommunicationAlarms();
    }

}
