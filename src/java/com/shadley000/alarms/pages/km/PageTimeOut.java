package com.shadley000.alarms.pages.km;

import com.shadley000.alarms.pages.nov.*;
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

public class PageTimeOut extends Page
{
    
    public PageTimeOut()
    {
        
    }
    
    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);
        
        Histogram2D histogram = AlarmsDB.getTimeOutAlarms(connection, userBean);
        
        ReportBean reportBean = new ReportBean("/TimeOut.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM, histogram);
        return reportBean;
    }
    
    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getTimeOutAlarms();
    }
    
}
