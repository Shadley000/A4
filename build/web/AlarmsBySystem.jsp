
<%@page import="com.shadley000.alarms.beans.AlarmClassification"%>
<%@page import="com.shadley000.alarms.pages.PageUniqueAlarms"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>

<%@page import="com.shadley000.histogram.Histogram2D"%>
<%@page import="java.util.SortedSet"%>
<%@page import="com.shadley000.histogram.Histogram"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="java.util.Iterator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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

                <%{

                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        // Histogram2D priorityRateHistogram = (Histogram2D) reportBean.getDataObject(ReportBean.DO_PRIORITY_RATE_HISTOGRAM);
                        Histogram2D histogram = (Histogram2D) reportBean.getDataObject(ReportBean.DO_SOURCE_HISTOGRAM);
                        String mode = request.getParameter(IConstants.PARAM_MODE);
                        String systemChecked = "";
                        String subSystemChecked = "";
                        String messageTypeChecked = "";

                        if (IConstants.PARAM_SUBSYSTEM.equals(mode))
                        {
                            systemChecked = "checked";

                        } else if (IConstants.PARAM_SYSTEM.equals(mode))
                        {
                            subSystemChecked = "checked";

                        } else if (IConstants.PARAM_MESSAGE_TYPE.equals(mode))
                        {
                            messageTypeChecked = "checked";

                        } else
                        {
                            systemChecked = "checked";

                        }

                %><H1><%= reportBean.getTitle()%></H1>

                <FORM action="<%= IConstants.servlet_url%>" method="post" >
                    <input type="radio" name="<%=IConstants.PARAM_MODE%>" value="<%=IConstants.PARAM_SYSTEM%>" onchange="this.form.submit()" <%=systemChecked%>>
                    <%=userBean.getLabelsBean().getSystem()%>
                    </input>
                    <input type="radio" name="<%=IConstants.PARAM_MODE%>" value="<%=IConstants.PARAM_SUBSYSTEM%>"  onchange="this.form.submit()" <%=subSystemChecked%>>
                    <%=userBean.getLabelsBean().getSubsystem()%>
                    </input>
                    <input type="radio" name="<%=IConstants.PARAM_MODE%>" value="<%=IConstants.PARAM_MESSAGE_TYPE%>"  onchange="this.form.submit()" <%=messageTypeChecked%>>
                    <%=userBean.getLabelsBean().getType()%>
                    </input>
                </FORM>
                <BR>
                <%

                    SortedSet<Object> orderedRows = histogram.getRows();
                    SortedSet<Object> orderedColumns = histogram.getColumns();
                    if (orderedRows.size() > 0)
                    {
                %>
                <TABLE>
                    <TR>
                        <TH></TH> 
                        <TH><%=userBean.getLabelsBean().getSystem()%></TH> 
                            <%
                                if (IConstants.PARAM_SUBSYSTEM.equals(mode))
                                {
                            %>
                        <TH><%=userBean.getLabelsBean().getSubsystem()%></TH> 
                            <%
                            } else if (IConstants.PARAM_MESSAGE_TYPE.equals(mode))
                            {
                            %>
                        <TH><%=userBean.getLabelsBean().getSubsystem()%></TH> 
                        <TH><%=userBean.getLabelsBean().getType()%></TH> 
                            <%
                                }

                                for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                {
                                    Object column = orderedColumnsIterator.next();

                                    out.print("<TH>" + column.toString() + "</TH>");
                                }
                            %>
                    </TR>
                    <%
                        for (Iterator<Object> rowIterator = orderedRows.iterator(); rowIterator.hasNext();)
                        {
                            //String system = (String) rowIterator.next();
                            AlarmClassification classification = (AlarmClassification) rowIterator.next();
                            Histogram hist = histogram.getHistogram(classification);
                    %>
                    <TR>
                        <TD>
                            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                                <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageUniqueAlarms.class.getName()%>">
                                <INPUT type="hidden" name ="<%= IConstants.PARAM_SYSTEM%>" value="<%= classification.getSystem()%>">
                                <input type="image"  id="button" src="images\Magnifier.ico" alt="<%= userBean.getLabelsBean().getGo()%>"  width="24px" height="24px" >
                            </FORM>
                        </TD>
                        <TD align="left"><%= classification.getSystem()%></TD>

                        <%
                            if (IConstants.PARAM_SUBSYSTEM.equals(mode))
                            {
                        %>
                        <TD align="left"><%= classification.getSubSystem()%></TD>
                            <%
                            } else if (IConstants.PARAM_MESSAGE_TYPE.equals(mode))
                            {
                            %>
                        <TD align="left"><%= classification.getSubSystem()%></TD>
                        <TD align="left"><%= classification.getMessageType()%></TD>
                            <%
                                }

                                for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                {
                                    Object column = orderedColumnsIterator.next();
                                    int count = hist.getCount(column);
                                    if (count > 0)
                                    {
                                        out.print("<TD>" + count + "</TD>");
                                    } else
                                    {
                                        out.print("<TD></TD>");
                                    }
                                }
                            %>
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
        </div>
        <%@ include file="_footer.jsp" %>
    </div>
</BODY>
</HTML>
