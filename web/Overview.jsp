<%@page import="com.shadley000.alarms.beans.AlarmClassification"%>
<%@page import="java.util.SortedSet"%>
<%@page import="com.shadley000.histogram.Histogram2D"%>
<!DOCTYPE html>


<html lang=\"en-US\">

    <head>
        <%@ include file="_head.jsp" %>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">

            <%{

                    ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                    UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                    Histogram2D histogram = (Histogram2D) reportBean.getDataObject(ReportBean.DO_SOURCE_HISTOGRAM);
                    Histogram2D priorityHist = (Histogram2D) reportBean.getDataObject(ReportBean.DO_PRIORITY_RATE_HISTOGRAM);
            %>
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawLineChart);
            google.charts.setOnLoadCallback(drawChartPie);

            function drawChartPie() {

            <% {
                    out.println("var data = google.visualization.arrayToDataTable([ ");
                    out.println("['" + userBean.getLabelsBean().getSystem() + "', '" + userBean.getLabelsBean().getCount() + "']");
                    SortedSet<Object> totalOrderedRows = histogram.getRows();
                    for (Iterator<Object> rowIterator = totalOrderedRows.iterator(); rowIterator.hasNext();)
                    {
                        AlarmClassification classification = (AlarmClassification) rowIterator.next();
                        int total = histogram.getRowTotalCount(classification);

                        out.println(", ['" + classification.getSystem() + "', " + total + "]");
                    }
                    out.println("]);");
                }
            %>

                var options = {
                    title: '<%=userBean.getLabelsBean().getAlarms_by_source()%>'

                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }



            function drawLineChart() {


            <% {
                    out.println("var data = google.visualization.arrayToDataTable([ ");
                    SortedSet<Object> priorities = priorityHist.getRows();
                    SortedSet<Object> dates = priorityHist.getColumns();

                    out.print("['" + userBean.getLabelsBean().getDate() + "'");
                    for (Iterator<Object> columnIterator = priorities.iterator(); columnIterator.hasNext();)
                    {
                        String prioritystr = (String) columnIterator.next();

                        out.print(" , '" + prioritystr + "' ");
                    }
                    out.println("]");
                    for (Iterator<Object> rowIterator = dates.iterator(); rowIterator.hasNext();)
                    {
                        String datestr = (String) rowIterator.next();

                        out.print(",['" + datestr + "'");
                        for (Iterator<Object> columnIterator = priorities.iterator(); columnIterator.hasNext();)
                        {
                            String priority = (String) columnIterator.next();
                            int count = priorityHist.getCount(priority, datestr);
                            out.print(" , " + count + " ");
                        }

                        out.println("]");
                    }
                    out.println("]);");
                }
            %>



                var options = {
                    title: '<%=userBean.getLabelsBean().getAlarm_rate()%>',

                    legend: {position: 'bottom'}
                };

                var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                chart.draw(data, options);
            }

            <%
                }
            %>
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

                %>
                <h1><%=userBean.getLabelsBean().getOverview()%></h1>

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
                <%  }
                %>
                <div id="piechart"  style="width: 900px; height: 500px;"></div>
                <div id="curve_chart"  style="width: 900px; height: 500px"></div>

            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>

