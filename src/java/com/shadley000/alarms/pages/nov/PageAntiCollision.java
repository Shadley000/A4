package com.shadley000.alarms.pages.nov;

import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.pages.Page;
import com.shadley000.histogram.Histogram2D;
import com.shadley000.histogram.TimeBin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class PageAntiCollision extends Page
{
    
    public PageAntiCollision()
    {
        
    }
    
    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);
        
        Histogram2D histogram = new Histogram2D();
        
        PreparedStatement stmt = AlarmsDB.getACSAlarms(connection, userBean);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            String columnName = TimeBin.findBin(record.getAlarmTime(), TimeBin.DAY);
            histogram.increment(columnName, type, 1);
            
            if (type.getDescription().contains("topped"))
            {
                AlarmTypeBean typeMachineStop = new AlarmTypeBean();
                typeMachineStop.setSystem(type.getSystem());
                typeMachineStop.setSubSystem(type.getSubSystem());
                typeMachineStop.setMessageType(type.getMessageType());
                typeMachineStop.setPriority(type.getPriority());
                typeMachineStop.setTagName(type.getSubSystem());
                typeMachineStop.setDescription("Total Machine Stops");
                histogram.increment(columnName, typeMachineStop, 1);
            }
        }
        rs.close();
        stmt.close();
        
        ReportBean reportBean = new ReportBean("/AntiCollision.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM, histogram);
        return reportBean;
    }
    
    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getAnticollision();
    }
    
}
