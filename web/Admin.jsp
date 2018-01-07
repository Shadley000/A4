
<%@page import="com.shadley000.alarms.UserSessionBean"%> 
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>

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
                <%                    ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                    UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                %>

                <H1><%= reportBean.getTitle()%></H1>

                <%
                    if (userBean.hasPermission(IConstants.PERMISSION_ADMIN))
                    {
                %>
                <ul class="tab">
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Users')" id="defaultOpen" > <%= userBean.getLabelsBean().getBlank() %></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Logins')"><%= userBean.getLabelsBean().getBlank()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Permissions')"><%= userBean.getLabelsBean().getBlank()%></a></li>
                </ul>
                <div id="Users" class="tabcontent">
                    <P>under construction</P>
                </div>
                <div id="Logins" class="tabcontent">
                    <P>under construction</P>
                </div>
                <div id="Permissions" class="tabcontent">
                    <P>under construction</P>
                </div>
        
                    <%@ include file="_tabSwitchScript.jsp" %>
                
                <%
                } else
                {
                %>
                <H2>Permission Denied</H2>
                    <%
                        }
                    %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
