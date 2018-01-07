
<%@page import="com.shadley000.alarms.beans.AlarmTypeBean"%>
<%@page import="com.shadley000.alarms.pages.PageAlarmDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.shadley000.alarms.IConstants"%>
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
                <%  {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        List<AlarmTypeBean> dataList = (List<AlarmTypeBean>) reportBean.getDataObject(ReportBean.DO_ALARM_TYPE_LIST);
                        String searchString = request.getParameter(IConstants.PARAM_INPUT1);
                        if (searchString == null)
                        {
                            searchString = "";
                        }
                %>
                <H1><%= reportBean.getTitle()%></H1>
                <FORM action="<%=reportBean.servlet_url%>" method="post" onchange="this.form.submit()">
                    <input type="text" name="<%=IConstants.PARAM_INPUT1%>" value="<%=searchString%>">
                    <input type="image"  id="button" src="images\Magnifier.ico" alt="<%= userBean.getLabelsBean().getGo()%>" width="24" height="24">
                </FORM>
                <BR>
                <%
                    if (dataList != null)
                    {
                %>
                <TABLE>
                    <TR>
                        <TH><%=userBean.getLabelsBean().getAction()%></TH> 
                        <TH><%=userBean.getLabelsBean().getSystem()%></TH> 
                        <TH><%=userBean.getLabelsBean().getSubsystem()%></TH> 
                        <TH><%=userBean.getLabelsBean().getType()%></TH> 
                        <TH><%=userBean.getLabelsBean().getPriority()%></TH> 
                        <TH><%=userBean.getLabelsBean().getDescription()%></TH> 
                    </TR>
                    <%
                        for (Iterator<AlarmTypeBean> rowIterator = dataList.iterator(); rowIterator.hasNext();)
                        {
                            AlarmTypeBean alarmType = rowIterator.next();
                    %>
                    <TR>
                        <TD>
                            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                                <INPUT type="hidden" name ="<%=  IConstants.PARAM_REPORT_NAME%>" value="<%= PageAlarmDetail.class.getName()%>">
                                <INPUT type="hidden" name ="<%=  IConstants.PARAM_TAGNAME%>" value="<%= alarmType.getTagName()%>">
                                <input type="image"  id="button" src="images\Info.ico" alt="<%= userBean.getLabelsBean().getGo()%>" width="24" height="24">
                            </FORM>

                        </TD>
                        <TD align="left"><%= alarmType.getSystem()%></TD>
                        <TD align="left"><%= alarmType.getSubSystem()%></TD>
                        <TD align="left"><%= alarmType.getMessageType()%></TD>
                        <TD align="left"><%= alarmType.getPriority()%></TD>
                        <TD align="left"><%= alarmType.getDescription()%></TD>
                    </TR>
                    <%
                        }
                    %>
                </TABLE>
                <%
                        }
                    }
                %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
