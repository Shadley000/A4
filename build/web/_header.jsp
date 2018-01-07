<%@page import="com.shadley000.alarms.beans.ClientBean"%>
<%@page import="com.shadley000.alarms.beans.LabelsBean"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.IConstants"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <%
        if (request.getSession() != null)
        {
            ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
            UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
            if (userBean != null)
            {
                LabelsBean labelsBean = userBean.getLabelsBean();
                ClientBean clientBean = userBean.getClientBean();

                out.print("<h1>");
                if (clientBean != null && clientBean.getLogoFile() != null)
                {
                    out.print("<img src=\"" + clientBean.getLogoFile() + "\"  style=\"width:48px;height:48px;\">");
                }
                if (labelsBean != null)
                {
                    out.print(" " + labelsBean.getApplicationName());
                }
                out.println("</h1>");
            } else
            {
                out.println("<h1>A4 Alarm Manager</h1>");
            }
        }
    %>
</header>