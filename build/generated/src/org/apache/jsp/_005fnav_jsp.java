package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
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

public final class _005fnav_jsp extends org.apache.jasper.runtime.HttpJspBase
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
