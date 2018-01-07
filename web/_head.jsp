
<%@page import="com.shadley000.alarms.beans.LabelsBean"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.IConstants"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="_debug.jsp" %>
<%
    if(request.getSession()!=null)
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
        if(reportBean!=null)
                out.println("<!-- "+reportBean.getJSPURL()+" -->");
        if (userBean != null)
        {
            LabelsBean labelsBean = userBean.getLabelsBean();

            if (labelsBean != null)
            {
                out.println("<title>" + labelsBean.getApplicationName() + "</title>");
            } else
            {
                out.println("<title>A4 Alarm Manager</title>");
            }
        }
    }
%>

<link rel="stylesheet" href="styles.css">
<link rel="shortcut icon" href="images\Red1.ico" />
