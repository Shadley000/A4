package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.IConstants;
import java.util.Enumeration;

public final class _005fhead_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/_debug.jsp");
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
