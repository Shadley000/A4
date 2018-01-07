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

public class PageErrorLog extends Page
{

    public PageErrorLog()
    {

    }

    public ReportBean build(Connection connection, HttpServletRequest request) throws Exception
    {

        
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(PARAM_USERBEAN);
        ReportBean reportBean = new ReportBean("/ErrorLog.jsp", getName(userBean.getLabelsBean()));

        try
        {
            int fileID = Integer.parseInt(request.getParameter(PARAM_FILE_ID));

            reportBean.setDataObject(ReportBean.DO_FILEBEAN, FilesDB.getFileBean(connection, userBean, fileID));
            reportBean.setDataObject(ReportBean.DO_FILE_ERRORBEAN_LIST, FilesDB.getFileErrorBeans(connection, userBean, fileID));
        } catch (NumberFormatException e)
        {
            //error messsage here?
        }

        return reportBean;
    }

    public String getName(LabelsBean labelsBean)
    {
        return labelsBean.getErrorLog();
    }

}
