
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


<html lang=\"en-US\">
    <head>
        <%@ include file="_head.jsp" %>
    </head>
    <BODY>
        <div class="container">
            <%@ include file="_header.jsp" %>
            <%@ include file="_nav.jsp" %>
            <article>
               
                <%               {
                        ReportBean myReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

                        Histogram2D histogram = (Histogram2D) myReportBean.getDataObject(ReportBean.DO_RATE_HISTOGRAM);

                        SortedSet<Object> orderedRows = histogram.getRows();
                        SortedSet<Object> orderedColumns = histogram.getColumns();
                        out.println("<H1>" + myReportBean.getTitle() + "</H1>");
                %>
                <TABLE>
                    <TR>
                        <TH></TH> 
                            <%                for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                {
                                    Object column = orderedColumnsIterator.next();
                                    out.println("<TH id=\"tdaction\">" + column.toString() + "</TH>");
                                }
                            %>
                    </TR>
                    <%
                        for (Iterator<Object> rowIterator = orderedRows.iterator(); rowIterator.hasNext();)
                        {
                            String rowName = (String) rowIterator.next();
                            Histogram hist = histogram.getHistogram(rowName);
                    %>
                    <TR>
                        <TD id="tdtext"><%= rowName%></TD>
                            <%
                                    String colorMap[] =
                                    {
                                        "White", "LightYellow", "Yellow", "#FFAAAA", "Red", "Purple"
                                    };

                                    int threshhold[] =
                                    {
                                        0, 24, 60, 120, 240, 480
                                    };

                                    for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                                    {
                                        Object column = orderedColumnsIterator.next();
                                        int count = hist.getCount(column);
                                        if (count > 0)
                                        {
                                            int colorIndex = 0;
                                            for (int t = 0; t < threshhold.length; t++)
                                            {
                                                if (count < threshhold[t])
                                                {
                                                    break;
                                                }
                                                colorIndex = t;
                                            }
                                            out.print("<TD id=\"tdnumber\" bgcolor=\"" + colorMap[colorIndex] + "\">" + count + "</TD>");
                                        } else
                                        {
                                            out.print("<TD id=\"tdnumber\"></TD>");
                                        }
                                    }
                                }
                            %>
                </TABLE>
                <%  }
                %>

            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
