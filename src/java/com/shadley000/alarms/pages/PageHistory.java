package com.shadley000.alarms.pages;

import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class PageHistory extends Page
{

    public PageHistory()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        ReportBean reportBean = new ReportBean("/History.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_RECORD_LIST, AlarmsDB.getSelectOrderedAlarms(connection, userBean));
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getHistory();
    }

}
