/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.pages;

import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.utils.AlarmsSQLConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shadl
 */
public class PageTagSearch extends Page implements IConstants
{

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);

        String searchString = request.getParameter(IConstants.PARAM_INPUT1);

        List<AlarmTypeBean> dataList = null;

        if (searchString != null)
        {
            if (searchString.indexOf('%') == -1)
            {
                searchString = "%" + searchString + "%";
            }
            dataList = AlarmsDB.tagSearch(connection, userBean, searchString);
        }

        ReportBean reportBean = new ReportBean("/TagSearch.jsp", getName(userBean.getLabelsBean()));
        reportBean.setDataObject(ReportBean.DO_ALARM_TYPE_LIST, dataList);
        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getTagSearch();
    }

}
