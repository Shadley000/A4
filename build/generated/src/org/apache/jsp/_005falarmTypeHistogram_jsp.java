package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.pages.PageAlarmDetail;
import com.shadley000.alarms.IConstants;
import com.shadley000.histogram.Histogram2D;
import java.util.SortedSet;
import com.shadley000.histogram.Histogram;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.IConstants;
import java.util.Iterator;

public final class _005falarmTypeHistogram_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    {
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

      out.write("\n");
      out.write("<TABLE>\n");
      out.write("    <TR>  \n");
      out.write("       <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getTag());
      out.write("</TH>\n");
      out.write("       <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getPriority());
      out.write("</TH>\n");
      out.write("        <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getAction());
      out.write("</TH> \n");
      out.write("        <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getSystem());
      out.write("</TH> \n");
      out.write("        <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getSubsystem());
      out.write("</TH> \n");
      out.write("        <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getType());
      out.write("</TH> \n");
      out.write("        <TH>");
      out.print(alarmTypeUserBean.getLabelsBean().getDescription());
      out.write("</TH>\n");
      out.write("\n");
      out.write("        ");

            for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
            {
                Object column = orderedColumnsIterator.next();
                out.println("<TH id=\"tdaction\">" + column.toString() + "</TH>");
            }
            out.println("<TH id=\"tdaction\">Total</TH>");
        
      out.write("\n");
      out.write("    </TR>\n");
      out.write("    ");

        for (Iterator<Object> rowIterator = orderedRows.iterator(); rowIterator.hasNext();)
        {
            AlarmTypeBean alarmTypeBean = (AlarmTypeBean) rowIterator.next();
            Histogram hist = alarmTypeHistogram.getHistogram(alarmTypeBean);
    
      out.write("\n");
      out.write("    <TR>\n");
      out.write("       \n");
      out.write("         <TD id=\"tdtext\">");
      out.print( alarmTypeBean.getTagName());
      out.write("</TD>\n");
      out.write("         <TD id=\"tdimage\" ><img src=\"images\\flags\\");
      out.print( Integer.toString(alarmTypeBean.getFlagIDPriority()) );
      out.write(".png\"  style=\"width:24px;height:24px;\"><img src=\"images\\flags\\");
      out.print( Integer.toString(alarmTypeBean.getFlagIDMaintenance()) );
      out.write(".png\"  style=\"width:24px;height:24px;\"></TD>\n");
      out.write("        <TD id=\"tdimage\"> \n");
      out.write("            <FORM action=\"");
      out.print( IConstants.servlet_url);
      out.write("\" method=\"post\" >\n");
      out.write("                <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_REPORT_NAME);
      out.write("\" value=\"");
      out.print( PageAlarmDetail.class.getName());
      out.write("\">\n");
      out.write("                <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_TAGNAME);
      out.write("\" value=\"");
      out.print( alarmTypeBean.getTagName());
      out.write("\">\n");
      out.write("                <input type=\"image\"  id=\"button\" src=\"images\\Info.ico\" alt=\"");
      out.print( alarmTypeUserBean.getLabelsBean().getGo());
      out.write("\"  width=\"24px\" height=\"24px\" >\n");
      out.write("            </FORM>\n");
      out.write("        </TD>\n");
      out.write("\n");
      out.write("        <TD id=\"tdtext\">");
      out.print( alarmTypeBean.getSystem());
      out.write("</TD>\n");
      out.write("        <TD id=\"tdtext\">");
      out.print( alarmTypeBean.getSubSystem());
      out.write("</TD>\n");
      out.write("        <TD id=\"tdtext\">");
      out.print( alarmTypeBean.getMessageType());
      out.write("</TD>\n");
      out.write("        <TD id=\"tdtext\" >");
      out.print( alarmTypeBean.getDescription());
      out.write("</TD>\n");
      out.write("            ");

                int rowTotal = 0;
                for (Iterator<Object> orderedColumnsIterator = orderedColumns.iterator(); orderedColumnsIterator.hasNext();)
                {
                    Object column = orderedColumnsIterator.next();
                    int count = hist.getCount(column);
                    out.print("<TD id=\"tdnumber\">");
                    if (count > 0)
                    {
                        out.print("" + count);
                        rowTotal+=count;
                    }
                    out.print("</TD>");
                }
                out.println("<TH id=\"tdaction\">"+rowTotal+"</TH>");
            
      out.write("\n");
      out.write("    </TR>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("</TABLE>\n");
            } else
                {
                    out.print("<P>" + alarmTypeUserBean.getLabelsBean().getNoRows() + "</P>");
                }
            }
        } else
        {
            out.print("<H2>Histogram Error</H2>");
        }
    }

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
