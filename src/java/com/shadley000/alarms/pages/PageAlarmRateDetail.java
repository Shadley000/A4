package com.shadley000.alarms.pages;

import com.shadley000.utils.AlarmsSQLConnect;

import com.shadley000.histogram.Histogram2D;
import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

public class PageAlarmRateDetail extends Page
{

    public PageAlarmRateDetail()
    {

    }

    @Override
    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        ReportBean reportBean = new ReportBean("/AlarmRateDetail.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_RATE_HISTOGRAM, AlarmsDB.getRateHistogram(connection, userBean));
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getAlarm_rate();
    }
}
