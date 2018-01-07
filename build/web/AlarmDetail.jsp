
<%@page import="com.shadley000.utils.NameValuePair"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.beans.NoteBean"%> 
<%@page import="com.shadley000.alarms.pages.PageHistory"%> 
<%@page import="com.shadley000.histogram.Histogram2D"%>
<%@page import="java.util.List"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%> 
<%@page import="com.shadley000.alarms.beans.AlarmRecordBean"%>
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
                <%  {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        LabelsBean labelsBean = userBean.getLabelsBean();
                        String tagName = request.getParameter(IConstants.PARAM_TAGNAME);

                        out.println("<H1>" + reportBean.getTitle() + "</H1>");

                        if (tagName == null)
                        {
                            out.println("<H2>" + labelsBean.getNoTagSelected() + "</H2>");
                        }
                %>

                <BR>
                <ul class="tab">
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'histogram')" id="defaultOpen"><%= labelsBean.getHistogram()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'history')"><%= labelsBean.getHistory()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'group')"><%= labelsBean.getGroupAnalysis()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'notes')"><%= labelsBean.getNotes()%></a></li>
                </ul>
                <%
                    }
                %>
                <div id="histogram" class="tabcontent">
                    <%@ include file="_alarmTypeHistogram.jsp" %>
                </div>

                <div id="history" class="tabcontent">
                    <%@ include file="_alarmRecordList.jsp" %>
                </div>


                <div id="group" class="tabcontent">
                    <%@ include file="_alarmRecordMultiHistoryList.jsp" %>  
                </div>

                <div id="notes" class="tabcontent">
                    <%@ include file="_notes.jsp" %>
                </div>

                <%@ include file="_tabSwitchScript.jsp" %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
