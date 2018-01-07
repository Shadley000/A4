/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.pages;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shadl
 */
public abstract class Page implements IConstants
{

    public abstract ReportBean build(Connection connection, HttpServletRequest request) throws Exception;

    public abstract String getName(LabelsBean labelsBean);

}
