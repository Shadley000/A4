
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
        ReportBean amhlReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);

        UserSessionBean amhlUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
        List<List<AlarmRecordBean>> amhlReportBeanList = (List<List<AlarmRecordBean>>) amhlReportBean.getDataObject(ReportBean.DO_MULTI_HISTORY_LIST);

        if (amhlReportBeanList != null && amhlReportBeanList.size() > 0)
        {
            int maxHistory = 0;
            for (Iterator<List<AlarmRecordBean>> it = amhlReportBeanList.iterator(); it.hasNext();)
            {
                List<AlarmRecordBean> history = it.next();
                if (maxHistory < history.size())
                {
                    maxHistory = history.size();
                }
            }

            out.println("<table>");
            for (int row = 0; row < maxHistory; row++)
            {
                out.print("<TR>");

                for (Iterator<List<AlarmRecordBean>> it = amhlReportBeanList.iterator(); it.hasNext();)
                {
                    List<AlarmRecordBean> history = it.next();
                    if (history.size() > row)
                    {
                        AlarmRecordBean alarmRecord = history.get(row);
                        String alarmDateStr = IConstants.htmlDateFormat.format(alarmRecord.getAlarmTime());
                        String alarmTimeStr = IConstants.htmlTimeFormat.format(alarmRecord.getAlarmTime());
%>
<TD>
    <TABLE>
        <TR>
            <TD>
                <FORM action="<%= IConstants.servlet_url%>" method="post" >
                    <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= amhlUserBean.getPageFilter().getSelectedKey() %>">
                    <INPUT type="hidden" name ="<%= IConstants.PARAM_TAGNAME%>" value="<%= alarmRecord.getTagName()%>">
                    <input type="image" id="button" src="images\Info.ico" alt="<%= amhlUserBean.getLabelsBean().getGo()%>" width="24" height="24">
                </FORM>
                <FORM action="<%= IConstants.servlet_url%>" method="post" >
                    <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageHistory.class.getName()%>">
                    <INPUT type="hidden" name ="<%= IConstants.PARAM_START_DATE%>" value=" <%= alarmDateStr%>">
                    <INPUT type="hidden" name ="<%= IConstants.PARAM_START_TIME%>" value=" <%= alarmTimeStr%>">
                    <input type="image"  id="button" src="images\time.png" alt="<%= amhlUserBean.getLabelsBean().getGo()%>" width="24" height="24">
                </FORM>
                <img src="images\flags\<%= Integer.toString(alarmRecord.getFlagIDPriority()) %>.png"  style="width:24px;height:24px;">
            </TD>
            <TD>
                <%= alarmRecord.getDescription()%><BR>
                <%=alarmDateStr%> <%=alarmTimeStr%>
            </TD>
        </TR>
    </TABLE>
</TD>
<%
                    } else
                    {
                        out.print("<TD></TD>");
                    }
                }
                out.println("</TR>");
            }

            out.println("</table>");
        } else
        {
            out.println("<P>" + amhlUserBean.getLabelsBean().getNoRows() + "</P>");
        }
    }
%>

