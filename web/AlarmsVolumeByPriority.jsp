
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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>      
        <script type="text/javascript">

            <%{

                    ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                    UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                    Histogram2D histogram = (Histogram2D) reportBean.getDataObject(ReportBean.DO_PRIORITY_RATE_HISTOGRAM);
            %>
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChartPie);
            function drawChartPie() {



                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', '<%=userBean.getLabelsBean().getPriority()%>');
                data.addColumn('number', '<%=userBean.getLabelsBean().getCount()%>');
                data.addRows([
            <% {
                    //totalHistogram.compressBins(labelsBean.getOther(), 6);
                    SortedSet<Object> totalOrderedRows = histogram.getRows();
                    for (Iterator<Object> rowIterator = totalOrderedRows.iterator(); rowIterator.hasNext();)
                    {
                        String system = (String) rowIterator.next();
                        int total = histogram.getRowTotalCount(system);

                        out.println("['" + system + "', " + total + "],");
                    }
                }
            %>
                ]);
                // Set chart options
                var options = {
                    'width': 800,
                    'height': 600,
                    sliceVisibilityThreshold: .025,
                    slices: {
                        0: {color: 'red'},
                        1: {color: 'green'},
                        2: {color: 'yellow'}
                    }
                };
                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('Pie'));
                chart.draw(data, options);
            }

            <% }%>
        </script>
    </head>
    <BODY>
        <div class="container">
            <%@ include file="_header.jsp" %>
            <%@ include file="_nav.jsp" %>
            <article>
                <%{

                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        Histogram2D histogram = (Histogram2D) reportBean.getDataObject(ReportBean.DO_PRIORITY_RATE_HISTOGRAM);

                %><H1><%= reportBean.getTitle()%></H1>

                <ul class="tab">
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Table')" id="defaultOpen"><%= userBean.getLabelsBean().getAlarms_by_Priority()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Pie')"><%= userBean.getLabelsBean().getAlarmDistribution()%></a></li>
                    <li><a href="javascript:void(0)" class="tablinks" onclick="openMode(event, 'Column')"><%= userBean.getLabelsBean().getAlarm_rate()%></a></li>
                </ul>

                <div id="Table" class="tabcontent">
                    <%

                        SortedSet<Object> orderedRows = histogram.getRows();
                        SortedSet<Object> orderedColumns = histogram.getColumns();
                        if (orderedRows.size() > 0)
                        {
                    %>
                    <TABLE>
                        <TR>
                            <TH><%=userBean.getLabelsBean().getPriority()%></TH> 
                                <%

                                    for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                    {
                                        Object column = orderedColumnsIterator.next();
                                        out.print("<TH>" + column.toString() + "</TH>");
                                    }
                                %>
                            <TH><%= userBean.getLabelsBean().getTotal()%></TH>
                        </TR>
                        <%
                            for (Iterator<Object> rowIterator = orderedRows.iterator(); rowIterator.hasNext();)
                            {
                                String priority = (String) rowIterator.next();
                                Histogram hist = histogram.getHistogram(priority);
                        %>
                        <TR>
                            <TD align="left"><%= priority%></TD>
                                <%
                                    int total = 0;
                                    for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                    {
                                        Object column = orderedColumnsIterator.next();
                                        int count = hist.getCount(column);
                                        out.print("<TD>");
                                        if (count > 0)
                                        {
                                            out.print(count);
                                        }
                                        out.print("</TD>");
                                    }
                                    out.print("<TD>" + total + "</TH>");
                                %>
                        </TR>
                        <%
                            }
                        %>
                    </TABLE> 
                    <%
                        }
                    %>
                </div>

                <div id="Pie" class="tabcontent"></div>
                <div id="Column" class="tabcontent">
                    <P>under construction</P>
                </div>

                <%@ include file="_tabSwitchScript.jsp" %>
                <% }%>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
