package com.shadley000.alarms.pages.km;

import com.shadley000.alarms.pages.*;
import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.histogram.Histogram2D;
import com.shadley000.histogram.TimeBin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;



public class PageFNGTesting extends Page
{

    public PageFNGTesting()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean)request.getSession().getAttribute(PARAM_USERBEAN);
        
        ReportBean reportBean = new ReportBean( "/UniqueAlarms.jsp",getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM,AlarmsDB.getFNGTestingAlarms( connection,  userBean));
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getFNGalarms();
    }
}
