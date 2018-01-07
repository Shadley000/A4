package com.shadley000.alarms.pages;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.FileBean;
import com.shadley000.alarms.beans.FileErrorBean;
import com.shadley000.alarms.db.FilesDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class PageOverview extends Page
{

    public PageOverview()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);
        ReportBean reportBean = new ReportBean("/Overview.jsp", getName(userBean.getLabelsBean()));

        String mode = request.getParameter(PARAM_MODE);
        if (mode == null)
        {
            mode = IConstants.PARAM_SYSTEM;
            request.setAttribute(PARAM_MODE, PARAM_SYSTEM);
        }

        reportBean.setDataObject(ReportBean.DO_SOURCE_HISTOGRAM, AlarmsDB.getAlarmsVolumeBySystem(connection, userBean, mode));
        reportBean.setDataObject(ReportBean.DO_PRIORITY_RATE_HISTOGRAM, AlarmsDB.getAlarmsVolumeByPriority(connection, userBean));
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getOverview();
    }

}
