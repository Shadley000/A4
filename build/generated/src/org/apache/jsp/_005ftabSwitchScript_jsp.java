package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _005ftabSwitchScript_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<script>\n");
      out.write("    function openMode(evt, cityName) {\n");
      out.write("        var i, tabcontent, tablinks;\n");
      out.write("        tabcontent = document.getElementsByClassName(\"tabcontent\");\n");
      out.write("        for (i = 0; i < tabcontent.length; i++) {\n");
      out.write("            tabcontent[i].style.display = \"none\";\n");
      out.write("        }\n");
      out.write("        tablinks = document.getElementsByClassName(\"tablinks\");\n");
      out.write("        for (i = 0; i < tablinks.length; i++) {\n");
      out.write("            tablinks[i].className = tablinks[i].className.replace(\" active\", \"\");\n");
      out.write("        }\n");
      out.write("        document.getElementById(cityName).style.display = \"block\";\n");
      out.write("        evt.currentTarget.className += \" active\";\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    // Get the element with id=\"defaultOpen\" and click on it\n");
      out.write("    document.getElementById(\"defaultOpen\").click();\n");
      out.write("</script>");
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
