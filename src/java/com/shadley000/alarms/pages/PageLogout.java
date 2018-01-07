package com.shadley000.alarms.pages;

import static com.shadley000.alarms.IConstants.PARAM_USERBEAN;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class PageLogout extends Page
{

    public PageLogout()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

         UserSessionBean userBean = (UserSessionBean)request.getSession().getAttribute(PARAM_USERBEAN);

        userBean.invalidate();

        request.getSession().setAttribute(PARAM_USERBEAN, null);
        request.getSession().invalidate();

        ReportBean reportBean = new ReportBean( "/A4.jsp","");

        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getLogout();
    }

}
