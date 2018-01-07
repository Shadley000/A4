package com.shadley000.alarms.pages;

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

public class PageTest extends Page
{

    public PageTest()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

        
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);
        ReportBean reportBean = new ReportBean("/Test.jsp", getName(userBean.getLabelsBean()));

        try
        {
           
        } catch (NumberFormatException e)
        {
            //error messsage here?
        }

        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return "Test";
    }

}
