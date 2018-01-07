
<%@page import="java.util.Enumeration"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="java.util.Iterator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=\"en-US\">

    <head>
        <%@ include file="_head.jsp" %>
    </head>
    <BODY>
        <div class="container">
            <%@ include file="_header.jsp" %>
            <%@ include file="_nav.jsp" %>
            <article>
                <%                    {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        if (reportBean != null)
                        {
                            String exceptionName = (String) reportBean.getDataObject(ReportBean.DO_NAME);
                            String exceptionTrace = (String) reportBean.getDataObject(ReportBean.DO_EXCEPTION_TRACE);

                %>

                <H1><%= reportBean.getTitle()%></H1>
                <H2><%=exceptionName%></H2>
                <P> <%=exceptionTrace%></P>

                <%
                        } else
                        {
                            out.print("<H2>Server Error</H2>");
                            out.print("<H3>reportBean=" + reportBean + "</H3>");
                            out.print("<H3>userBean=" + userBean + "</H3>");
                        }

                        out.println("<TABLE>");
                        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();)
                        {

                            String attributeName = e.nextElement();
                            out.print("<TR><TD>"+attributeName+"</TD><TD>"+request.getParameter(attributeName)+"</TD></TR>");
                        }
                        out.println("</TABLE>");
                    }
                %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
