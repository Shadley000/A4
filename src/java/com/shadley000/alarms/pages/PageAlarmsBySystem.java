package com.shadley000.alarms.pages;

import com.shadley000.alarms.IConstants;
import com.shadley000.utils.AlarmsSQLConnect;

import com.shadley000.histogram.Histogram2D;
import com.shadley000.histogram.TimeBin;
import com.shadley000.alarms.beans.AlarmRecordBean;
import static com.shadley000.alarms.IConstants.PARAM_USERBEAN;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.histogram.Histogram;
import com.shadley000.utils.NameValuePair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class PageAlarmsBySystem extends Page
{

    public PageAlarmsBySystem()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        String mode = request.getParameter(PARAM_MODE);
        if (mode == null)
        {
            mode = IConstants.PARAM_SYSTEM;
            request.setAttribute(PARAM_MODE, PARAM_SYSTEM);
        }
        ReportBean reportBean = new ReportBean("/AlarmsBySystem.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_SOURCE_HISTOGRAM, AlarmsDB.getAlarmsVolumeBySystem(connection, userBean, mode));
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getAlarms_by_source();
    }
}
