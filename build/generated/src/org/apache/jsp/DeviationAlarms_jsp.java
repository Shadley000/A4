package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.IConstants;
import java.util.Enumeration;
import com.shadley000.alarms.beans.ClientBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.IConstants;
import com.shadley000.utils.SelectableFilter;
import java.sql.SQLException;
import com.shadley000.alarms.db.ClientDB;
import com.shadley000.utils.AlarmsSQLConnect;
import java.sql.Connection;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.beans.ClientBean;
import com.shadley000.alarms.beans.VendorBean;
import java.util.Iterator;
import com.shadley000.alarms.beans.InstallationBean;
import com.shadley000.alarms.UserSessionBean;
import java.util.List;
import com.shadley000.alarms.IConstants;
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
import java.util.Date;

public final class DeviationAlarms_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(6);
    _jspx_dependants.add("/_head.jsp");
    _jspx_dependants.add("/_debug.jsp");
    _jspx_dependants.add("/_header.jsp");
    _jspx_dependants.add("/_nav.jsp");
    _jspx_dependants.add("/_alarmTypeHistogram.jsp");
    _jspx_dependants.add("/_footer.jsp");
  }

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
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html lang=\\\"en-US\\\">\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write('\n');

    {
         out.println("<!-- Request -->");
        for (Enumeration<String> values = request.getAttributeNames(); values.hasMoreElements();)
        {
            String name = values.nextElement();
            out.println("<!-- "+name+":"+request.getParameter(name)+" -->");
        }

        out.println("<!-- Session -->");
        for (Enumeration<String> values = request.getSession().getAttributeNames(); values.hasMoreElements();)
        {
            String name = values.nextElement();
            out.println("<!-- "+name+":"+request.getSession().getAttribute(name)+" -->");
        }
    }

      out.write('\n');
      out.write('\n');

    if(request.getSession()!=null)
    {
        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
        if(reportBean!=null)
                out.println("<!-- "+reportBean.getJSPURL()+" -->");
        if (userBean != null)
        {
            LabelsBean labelsBean = userBean.getLabelsBean();

            if (labelsBean != null)
            {
                out.println("<title>" + labelsBean.getApplicationName() + "</title>");
            } else
            {
                out.println("<title>A4 Alarm Manager</title>");
            }
        }
    }

      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"styles.css\">\n");
      out.write("<link rel=\"shortcut icon\" href=\"images\\Red1.ico\" />\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <BODY>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    ");

        if (request.getSession() != null)
        {
            ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
            UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
            if (userBean != null)
            {
                LabelsBean labelsBean = userBean.getLabelsBean();
                ClientBean clientBean = userBean.getClientBean();

                out.print("<h1>");
                if (clientBean != null && clientBean.getLogoFile() != null)
                {
                    out.print("<img src=\"" + clientBean.getLogoFile() + "\"  style=\"width:48px;height:48px;\">");
                }
                if (labelsBean != null)
                {
                    out.print(" " + labelsBean.getApplicationName());
                }
                out.println("</h1>");
            } else
            {
                out.println("<h1>A4 Alarm Manager</h1>");
            }
        }
    
      out.write("\n");
      out.write("</header>");
      out.write("\n");
      out.write("            ");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    SelectableFilter clientFilter = (SelectableFilter) request.getSession().getAttribute(IConstants.PARAM_CLIENTBEAN_LIST);

    if (clientFilter == null)
    {
        try
        {
            AlarmsSQLConnect mySQLConnect = new AlarmsSQLConnect();
            Connection connection = mySQLConnect.connect();
            clientFilter = new SelectableFilter(ClientDB.getClientBeans(connection));
            mySQLConnect.disconnect();
            request.getSession().setAttribute(IConstants.PARAM_CLIENTBEAN_LIST, clientFilter);
        } catch (SQLException e)
        {
            out.print("<P>" + e.toString() + "</P>");
        }
    }
    if (request.getSession() != null)
    {

        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

        if (userBean != null && userBean.isAuthenticated())
        {
            LabelsBean labelsBean = userBean.getLabelsBean();

            String installationLogo = "";
            String vendorLogo = "";

            InstallationBean ibean = (InstallationBean) userBean.getInstallationFilter().getSelectedItem();
            VendorBean vbean = (VendorBean) userBean.getVendorFilter().getSelectedItem();
            if (ibean != null && ibean.getLogoFile() != null)
            {
                installationLogo = ibean.getLogoFile();
            }
            if (vbean != null && vbean.getLogoFile() != null)
            {
                vendorLogo = vbean.getLogoFile();
            }
            String startDate = IConstants.htmlDateFormat.format(userBean.getStart());
            String startTime = IConstants.htmlTimeFormat.format(userBean.getStart());
            String endDate = IConstants.htmlDateFormat.format(userBean.getEnd());
            String endTime = IConstants.htmlTimeFormat.format(userBean.getEnd());

      out.write("\n");
      out.write("<nav>\n");
      out.write("    <FORM action=\"A4\" method=\"post\"> \n");
      out.write("        <UL>\n");
      out.write("            <li>  ");
      out.print( userBean.getInstallationFilter().getSelectHTML(IConstants.PARAM_INSTALLATION_ID, true));
      out.write("</li>\n");
      out.write("            <li>");
      out.print( userBean.getVendorFilter().getSelectHTML(IConstants.PARAM_VENDOR_ID, true));
      out.write("</li>\n");
      out.write("        </UL> \n");
      out.write("    </FORM>\n");
      out.write("    <UL>\n");
      out.write("        <li><img src=\"");
      out.print(installationLogo);
      out.write("\" style=\\\"width:128px;height:128px;\\\"></li>\n");
      out.write("        <li><img src=\"");
      out.print(vendorLogo);
      out.write("\" style=\\\"width:128px;height:128px;\\\"></li>\n");
      out.write("    </UL> \n");
      out.write("    <FORM action=\"A4\" method=\"post\">            \n");
      out.write("        <UL>\n");
      out.write("            <li>");
      out.print( labelsBean.getReport());
      out.write("</li>       \n");
      out.write("            <li>");
      out.print( userBean.getPageFilter().getSelectHTML(IConstants.PARAM_REPORT_NAME, true));
      out.write("</li>\n");
      out.write("        </UL>      \n");
      out.write("    </FORM>\n");
      out.write("    <FORM action=\"A4\" method=\"post\">\n");
      out.write("        <ul>\n");
      out.write("            <li>");
      out.print( labelsBean.getSystem());
      out.write("</li>  \n");
      out.write("            <li>");
      out.print( userBean.getSystemFilter().getSelectHTML(IConstants.PARAM_SYSTEM, true));
      out.write("</li>\n");
      out.write("            <li>");
      out.print( labelsBean.getSubsystem());
      out.write("</li>  \n");
      out.write("            <li>");
      out.print( userBean.getSubSystemFilter().getSelectHTML(IConstants.PARAM_SUBSYSTEM, true));
      out.write("</li>\n");
      out.write("            <li>");
      out.print( labelsBean.getType());
      out.write("</li>  \n");
      out.write("            <li>");
      out.print( userBean.getMessageTypeFilter().getSelectHTML(IConstants.PARAM_MESSAGE_TYPE, true));
      out.write("</li>\n");
      out.write("            <li>");
      out.print( labelsBean.getTag());
      out.write("</li>  \n");
      out.write("            <li>");
      out.print( userBean.getAlarmTypeFilter().getSelectHTML(IConstants.PARAM_TAGNAME, true));
      out.write("</li>\n");
      out.write("            <li>");
      out.print( labelsBean.getStatus());
      out.write("</li>  \n");
      out.write("            <li>");
      out.print( userBean.getStatusFilter().getSelectHTML(IConstants.PARAM_STATUS, true));
      out.write("</li>           \n");
      out.write("            <li><BR></li>\n");
      out.write("            <li>");
      out.print( labelsBean.getAlarmFlagFilter());
      out.write("</li>\n");
      out.write("            <li>");
      out.print(  userBean.getPriorityFilter().getFlagRadioHTML(IConstants.PARAM_PRIORITY_FLAG_FILTER, 3, true));
      out.write("</li>\n");
      out.write("            <li><BR></li>\n");
      out.write("            <li>");
      out.print( labelsBean.getMaintFlagFilter());
      out.write("</li>\n");
      out.write("            <li>");
      out.print(  userBean.getMaintFilter().getFlagRadioHTML(IConstants.PARAM_MAINTENANCE_FLAG_FILTER, 3, true));
      out.write("</li>\n");
      out.write("        </ul>\n");
      out.write("    </form>\n");
      out.write("    <FORM action=\"A4\" method=\"post\">\n");
      out.write("        <ul>\n");
      out.write("            <li>");
      out.print( labelsBean.getStart());
      out.write("</li>\n");
      out.write("            <li><INPUT id=\"menu\" type=\"text\" name=\"");
      out.print(IConstants.PARAM_START_DATE);
      out.write("\" value=\"");
      out.print(startDate);
      out.write("\"> </INPUT></li>\n");
      out.write("            <li><INPUT id=\"menu\" type=\"text\" name=\"");
      out.print(IConstants.PARAM_START_TIME);
      out.write("\" value=\"");
      out.print(startTime);
      out.write("\"> </INPUT></li>\n");
      out.write("            <li>");
      out.print( labelsBean.getEnd());
      out.write("</li>\n");
      out.write("            <li><INPUT  id=\"menu\" type=\"text\" name=\"");
      out.print(IConstants.PARAM_END_DATE);
      out.write("\" value=\"");
      out.print(endDate);
      out.write("\"> </INPUT></li>\n");
      out.write("            <li><INPUT id=\"menu\" type=\"text\" name=\"");
      out.print(IConstants.PARAM_END_TIME);
      out.write("\" value=\"");
      out.print(endTime);
      out.write("\"> </INPUT></li>\n");
      out.write("            <li>");
      out.print( labelsBean.getTimeResolution());
      out.write("</li>\n");
      out.write("            <li>");
      out.print(  userBean.getTimeBinFilter().getSelectHTML(IConstants.PARAM_TIMEBIN, false));
      out.write("</li>\n");
      out.write("            <li><INPUT  id=\"menu\" type=\"submit\"  value=\"");
      out.print( labelsBean.getGo());
      out.write("\"> </li>\n");
      out.write("        </ul>\n");
      out.write("    </FORM>\n");
      out.write("</nav>\n");

} else
{

      out.write("\n");
      out.write("<nav> \n");
      out.write("    <FORM action=\"A4\" method=\"post\">\n");
      out.write("        <UL>\n");
      out.write("            <li>");
      out.print( clientFilter.getSelectHTML(IConstants.PARAM_CLIENT_ID, false));
      out.write("</li>  \n");
      out.write("            <li>User:<INPUT   id=\"menu\" type=\"text\" name=\"");
      out.print(IConstants.PARAM_USER_NAME);
      out.write("\" value=\"\"></INPUT>\n");
      out.write("            <li>Password:<INPUT   id=\"menu\"  type=\"password\" name=\"");
      out.print(IConstants.PARAM_PASSWORD);
      out.write("\" value=\"\"></INPUT>         \n");
      out.write("            <li><INPUT  id=\"menu\" type=\"submit\" name=\"go\"></INPUT></li>\n");
      out.write("        </ul>\n");
      out.write("    </FORM>\n");
      out.write("</nav>\n");

        }
    }

      out.write('\n');
      out.write("\n");
      out.write("            <article>\n");
      out.write("\n");
      out.write("                ");
   {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

                        if (reportBean != null)
                        {
                            out.println("<H1>" + reportBean.getTitle() + "</H1>");

                        }
                    }
                
      out.write("\n");
      out.write("                ");
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

      out.write("\n");
      out.write("\n");
      out.write("            </article>\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<footer>");
      out.print(new Date().toString() );
      out.write("</footer>");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </BODY>\n");
      out.write("</HTML>");
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
