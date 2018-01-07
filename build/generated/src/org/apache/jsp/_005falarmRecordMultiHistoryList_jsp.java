package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.pages.PageAlarmDetail;
import com.shadley000.alarms.pages.PageHistory;
import java.util.List;
import com.shadley000.alarms.IConstants;
import java.util.SortedSet;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.IConstants;
import java.util.Iterator;

public final class _005falarmRecordMultiHistoryList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\n");
                    {
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

      out.write("\n");
      out.write("<TD>\n");
      out.write("    <TABLE>\n");
      out.write("        <TR>\n");
      out.write("            <TD>\n");
      out.write("                <FORM action=\"");
      out.print( IConstants.servlet_url);
      out.write("\" method=\"post\" >\n");
      out.write("                    <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_REPORT_NAME);
      out.write("\" value=\"");
      out.print( amhlUserBean.getPageFilter().getSelectedKey() );
      out.write("\">\n");
      out.write("                    <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_TAGNAME);
      out.write("\" value=\"");
      out.print( alarmRecord.getTagName());
      out.write("\">\n");
      out.write("                    <input type=\"image\" id=\"button\" src=\"images\\Info.ico\" alt=\"");
      out.print( amhlUserBean.getLabelsBean().getGo());
      out.write("\" width=\"24\" height=\"24\">\n");
      out.write("                </FORM>\n");
      out.write("                <FORM action=\"");
      out.print( IConstants.servlet_url);
      out.write("\" method=\"post\" >\n");
      out.write("                    <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_REPORT_NAME);
      out.write("\" value=\"");
      out.print( PageHistory.class.getName());
      out.write("\">\n");
      out.write("                    <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_START_DATE);
      out.write("\" value=\" ");
      out.print( alarmDateStr);
      out.write("\">\n");
      out.write("                    <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_START_TIME);
      out.write("\" value=\" ");
      out.print( alarmTimeStr);
      out.write("\">\n");
      out.write("                    <input type=\"image\"  id=\"button\" src=\"images\\time.png\" alt=\"");
      out.print( amhlUserBean.getLabelsBean().getGo());
      out.write("\" width=\"24\" height=\"24\">\n");
      out.write("                </FORM>\n");
      out.write("                <img src=\"images\\flags\\");
      out.print( Integer.toString(alarmRecord.getFlagIDPriority()) );
      out.write(".png\"  style=\"width:24px;height:24px;\">\n");
      out.write("            </TD>\n");
      out.write("            <TD>\n");
      out.write("                ");
      out.print( alarmRecord.getDescription());
      out.write("<BR>\n");
      out.write("                ");
      out.print(alarmDateStr);
      out.write(' ');
      out.print(alarmTimeStr);
      out.write("\n");
      out.write("            </TD>\n");
      out.write("        </TR>\n");
      out.write("    </TABLE>\n");
      out.write("</TD>\n");

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

      out.write('\n');
      out.write('\n');
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
