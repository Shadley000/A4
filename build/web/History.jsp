
<%@page import="com.shadley000.alarms.beans.AlarmTypeBean"%>
<%@page import="com.shadley000.alarms.beans.AlarmRecordBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.pages.PageAlarmDetail"%>
<%@page import="com.shadley000.alarms.pages.PageHistory"%>
<%@page import="java.util.List"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="java.util.SortedSet"%>
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
                <%@ include file="_alarmRecordList.jsp" %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
