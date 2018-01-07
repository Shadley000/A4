/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.pages.admin;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.pages.Page;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shadl
 */
public class PageAdmin  extends Page implements IConstants
{
    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {
         UserSessionBean userBean = (UserSessionBean)request.getSession().getAttribute(PARAM_USERBEAN);

        ReportBean reportBean = new ReportBean( "/Admin.jsp",getName(userBean.getLabelsBean()));
        return reportBean;

    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getAdmin();
    }

   

}
