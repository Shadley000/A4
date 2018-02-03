package com.shadley000.alarms;

import com.shadley000.alarms.beans.PageBean;
import com.shadley000.alarms.db.ClientDB;
import com.shadley000.alarms.pages.Page;
import com.shadley000.alarms.db.UsersDB;
import com.shadley000.utils.AlarmsSQLConnect;
import com.shadley000.utils.SelectableFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shadl
 */
public class AlarmServlet extends HttpServlet implements IConstants
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        AlarmsSQLConnect mySQLConnect = new AlarmsSQLConnect();
        Connection connection = mySQLConnect.connect();

        if (connection == null)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/A4_Error.jsp");
            dispatcher.forward(request, response);
        } else
        {

            HttpSession session = request.getSession();

            UserSessionBean userBean = (UserSessionBean) session.getAttribute(IConstants.PARAM_USERBEAN);

            ReportBean reportBean = null;
            try
            {
                if (session.getAttribute(PARAM_CLIENTBEAN_LIST) == null)
                {
                    session.setAttribute(PARAM_CLIENTBEAN_LIST, new SelectableFilter(ClientDB.getClientBeans(connection)));
                }
                if (userBean == null || !userBean.isAuthenticated())
                {
                    reportBean = login(request, connection);
                } else
                {
                    reportBean = buildReport(request, connection);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                reportBean = buildExceptionReport(request, connection, e);
            }

            mySQLConnect.disconnect();

            request.setAttribute(PARAM_REPORTBEAN, reportBean);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(reportBean.getJSPURL());
            dispatcher.forward(request, response);
        }

    }

    private ReportBean buildExceptionReport(HttpServletRequest request, Connection connection, Exception e)
    {
        StringWriter stackTrace = new StringWriter();
        e.printStackTrace(new PrintWriter(stackTrace));

        ReportBean reportBean = new ReportBean("/Exception.jsp", "Unexpected Error");

        reportBean.setDataObject(ReportBean.DO_NAME, e.getClass().getName());
        reportBean.setDataObject(ReportBean.DO_EXCEPTION_TRACE, stackTrace.toString());
        return reportBean;
    }

    private ReportBean buildReport(HttpServletRequest request, Connection connection) throws Exception
    {
        HttpSession session = request.getSession();
        UserSessionBean userBean = (UserSessionBean) session.getAttribute(PARAM_USERBEAN);
        if (userBean == null)
        {
            throw new Exception("AlarmServlet:buildReport:UserBean = null");
        }

        userBean.update(request, connection);
        ReportBean reportBean;

        String className = userBean.getPageFilter().getSelectedKey();
        if (className != null)
        {
            try
            {
                Page page = (Page) Class.forName(className).newInstance();
                reportBean = page.build(connection, request);
            } catch (ClassNotFoundException e)
            {
                reportBean = new ReportBean("/ReportNotFound.jsp", "");
            }
        } else
        {
            reportBean = new ReportBean("/A4.jsp", "");
        }

        return reportBean;
    }

    private ReportBean login(HttpServletRequest request, Connection connection) throws Exception
    {
        HttpSession session = request.getSession();

        String clientID = request.getParameter(PARAM_CLIENT_ID);
        String userName = request.getParameter(PARAM_USER_NAME);
        String password = request.getParameter(PARAM_PASSWORD);

        if (clientID != null && userName != null && password != null)
        {
            UsersDB.logLogin(connection, clientID, userName, request.getRemoteAddr());
            UserSessionBean userBean = new UserSessionBean(request, connection, clientID, userName, password);
            if (userBean.isAuthenticated())
            {
                session.setAttribute(PARAM_USERBEAN, userBean);
            }
            else
            {
                System.out.println("login failed");
            }

        } else
        {

        }

        return new ReportBean("/A4.jsp", "");
    }

}
