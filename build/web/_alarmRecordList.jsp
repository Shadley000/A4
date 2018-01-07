
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

<%                    {
        ReportBean alarmRecordListReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);

        UserSessionBean alarmRecordListUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
        List<AlarmRecordBean> alarmRecordList = (List<AlarmRecordBean>) alarmRecordListReportBean.getDataObject(ReportBean.DO_ALARM_RECORD_LIST);
        if (alarmRecordList != null && alarmRecordList.size() > 0)
        {
%>
<TABLE>
    <TR>
        <TH><%=alarmRecordListUserBean.getLabelsBean().getAction()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getTime()%></TH> 
        <TH><%= alarmRecordListUserBean.getLabelsBean().getSystem()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getSubsystem()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getType()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getPriority()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getDescription()%></TH> 
        <TH><%=alarmRecordListUserBean.getLabelsBean().getStatus()%></TH> 
    </TR>
    <%
        for (Iterator<AlarmRecordBean> rowIterator = alarmRecordList.iterator(); rowIterator.hasNext();)
        {
            AlarmRecordBean alarmRecord = rowIterator.next();
          
            String alarmDateStr = IConstants.htmlDateFormat.format(alarmRecord.getAlarmTime());
            String alarmTimeStr = IConstants.htmlTimeFormat.format(alarmRecord.getAlarmTime());
          
    %>
    <TR>
        <TD> 
            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageAlarmDetail.class.getName()%>">
                <INPUT type="hidden" name ="<%= IConstants.PARAM_TAGNAME%>" value="<%= alarmRecord.getTagName()%>">
                <input type="image" id="button" src="images\Info.ico" alt="<%= alarmRecordListUserBean.getLabelsBean().getGo()%>" width="24" height="24">
            </FORM>
            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageHistory.class.getName()%>">
                <INPUT type="hidden" name ="<%= IConstants.PARAM_START_DATE%>" value=" <%= alarmDateStr%>">
                <INPUT type="hidden" name ="<%= IConstants.PARAM_START_TIME%>" value=" <%= alarmTimeStr%>">
                <input type="image"  id="button" src="images\time.png" alt="<%= alarmRecordListUserBean.getLabelsBean().getGo()%>" width="24" height="24">
            </FORM>
        </TD>
        <TD align="left">
            <%=alarmDateStr%> <%=alarmTimeStr%>
        </TD>
        <TD align="left"><%= alarmRecord.getSystem()%></TD>
        <TD align="left"><%= alarmRecord.getSubSystem()%></TD>
        <TD align="left"><%= alarmRecord.getMessageType()%></TD>
        <TD id="tdimage" > <img src="images\flags\<%= Integer.toString(alarmRecord.getFlagIDPriority()) %>.png"  style="width:24px;height:24px;"></TD>
        <TD align="left"><%= alarmRecord.getDescription()%></TD>
        <TD align="left"><%= alarmRecord.getStatus()%></TD>
    </TR>
    <%    
        }
    %>
</TABLE>
<%
        } else
        {
            out.print("<P>" + alarmRecordListUserBean.getLabelsBean().getNoRows() + "</P>");
        }
    }
%>

