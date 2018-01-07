
<%@page import="com.shadley000.alarms.beans.AlarmTypeBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.pages.PageAlarmDetail"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="com.shadley000.histogram.Histogram2D"%>
<%@page import="java.util.SortedSet"%>
<%@page import="com.shadley000.histogram.Histogram"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="java.util.Iterator"%>


<%    {
        ReportBean alarmTypeReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
        UserSessionBean alarmTypeUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

        if (alarmTypeReportBean != null && alarmTypeUserBean != null)
        {
            Histogram2D alarmTypeHistogram = (Histogram2D) alarmTypeReportBean.getDataObject(ReportBean.DO_ALARM_TYPE_HISTOGRAM);

            if (alarmTypeHistogram != null)
            {
                SortedSet<Object> orderedRows = alarmTypeHistogram.getRows();
                SortedSet<Object> orderedColumns = alarmTypeHistogram.getColumns();
                if (orderedRows.size() > 0)
                {
%>
<TABLE>
    <TR>  
       <TH><%=alarmTypeUserBean.getLabelsBean().getTag()%></TH>
       <TH><%=alarmTypeUserBean.getLabelsBean().getPriority()%></TH>
        <TH><%=alarmTypeUserBean.getLabelsBean().getAction()%></TH> 
        <TH><%=alarmTypeUserBean.getLabelsBean().getSystem()%></TH> 
        <TH><%=alarmTypeUserBean.getLabelsBean().getSubsystem()%></TH> 
        <TH><%=alarmTypeUserBean.getLabelsBean().getType()%></TH> 
        <TH><%=alarmTypeUserBean.getLabelsBean().getDescription()%></TH>

        <%
            for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
            {
                Object column = orderedColumnsIterator.next();
                out.println("<TH id=\"tdaction\">" + column.toString() + "</TH>");
            }
        %>
    </TR>
    <%
        for (Iterator<Object> rowIterator = orderedRows.iterator(); rowIterator.hasNext();)
        {
            AlarmTypeBean alarmTypeBean = (AlarmTypeBean) rowIterator.next();
            Histogram hist = alarmTypeHistogram.getHistogram(alarmTypeBean);
    %>
    <TR>
       
         <TD id="tdtext"><%= alarmTypeBean.getTagName()%></TD>
         <TD id="tdimage" ><img src="images\flags\<%= Integer.toString(alarmTypeBean.getFlagIDPriority()) %>.png"  style="width:24px;height:24px;"><img src="images\flags\<%= Integer.toString(alarmTypeBean.getFlagIDMaintenance()) %>.png"  style="width:24px;height:24px;"></TD>
        <TD id="tdimage"> 
            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageAlarmDetail.class.getName()%>">
                <INPUT type="hidden" name ="<%= IConstants.PARAM_TAGNAME%>" value="<%= alarmTypeBean.getTagName()%>">
                <input type="image"  id="button" src="images\Info.ico" alt="<%= alarmTypeUserBean.getLabelsBean().getGo()%>"  width="24px" height="24px" >
            </FORM>
        </TD>

        <TD id="tdtext"><%= alarmTypeBean.getSystem()%></TD>
        <TD id="tdtext"><%= alarmTypeBean.getSubSystem()%></TD>
        <TD id="tdtext"><%= alarmTypeBean.getMessageType()%></TD>
        <TD id="tdtext" ><%= alarmTypeBean.getDescription()%></TD>
            <%
                for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                {
                    Object column = orderedColumnsIterator.next();
                    int count = hist.getCount(column);
                    out.print("<TD id=\"tdnumber\">");
                    if (count > 0)
                    {
                        out.print("" + count);
                    }
                    out.print("</TD>");
                }
            %>
    </TR>
    <%
        }
    %>
</TABLE>
<%            } else
                {
                    out.print("<P>" + alarmTypeUserBean.getLabelsBean().getNoRows() + "</P>");
                }
            }
        } else
        {
            out.print("<H2>Histogram Error</H2>");
        }
    }
%>