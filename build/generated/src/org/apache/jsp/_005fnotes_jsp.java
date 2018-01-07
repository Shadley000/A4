package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.beans.NoteBean;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.ReportBean;
import com.shadley000.alarms.pages.PageAlarmDetail;
import com.shadley000.alarms.IConstants;
import java.util.List;
import java.util.Iterator;

public final class _005fnotes_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    {
        ReportBean notesReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
        UserSessionBean notesUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

        String tagname =notesUserBean.getAlarmTypeFilter().getSelectedKey();

      out.write("\n");
      out.write("<UL  style=\"list-style: none;\">\n");
      out.write("    ");

        if (notesUserBean.hasPermission(IConstants.PERMISSION_COMMENT) )
        {
    
      out.write("\n");
      out.write("    <LI>\n");
      out.write("        <FORM action=\"");
      out.print( IConstants.servlet_url);
      out.write("\" method=\"post\" >\n");
      out.write("            <table>\n");
      out.write("                <TR>\n");
      out.write("                    <TD  colspan=\"1\">\n");
      out.write("                        <input type=\"image\"  id=\"button\" src=\"images\\Save.ico\" alt=\"");
      out.print( notesUserBean.getLabelsBean().getGo());
      out.write("\"  width=\"24px\" height=\"24px\" >\n");
      out.write("                        ");
      out.print( notesUserBean.getLabelsBean().getCreateNewNote());
      out.write("\n");
      out.write("                    </TD>\n");
      out.write("                    <TD colspan=\"2\">");
      out.print( notesUserBean.getLabelsBean().getFlag());
      out.write("\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"0\" checked><img src=\"images\\flags\\0.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"1\"><img src=\"images\\flags\\1.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"2\"><img src=\"images\\flags\\2.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"3\"><img src=\"images\\flags\\3.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"4\"><img src=\"images\\flags\\4.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"5\"><img src=\"images\\flags\\5.png\"  style=\"width:24px;height:24px;\"></input>                   \n");
      out.write("                        <input type=\"radio\" name=\"");
      out.print( IConstants.PARAM_FLAG_ID);
      out.write("\" value=\"6\"><img src=\"images\\flags\\6.png\"  style=\"width:24px;height:24px;\"></input>\n");
      out.write("                    </TD> \n");
      out.write("                </TR>\n");
      out.write("                \n");
      out.write("                <TR><TD colspan=\"3\">\n");
      out.write("                        <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_TAGNAME);
      out.write("\" value=\"");
      out.print(tagname);
      out.write("\">\n");
      out.write("                        <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_NOTE_MODE);
      out.write("\" value=\"");
      out.print( IConstants.MODE_ADD);
      out.write("\">\n");
      out.write("                        <textarea rows=\"10\" cols=\"100\" name=\"");
      out.print(IConstants.PARAM_NOTE_TEXT);
      out.write("\"></textarea>\n");
      out.write("                    </TD></TR>\n");
      out.write("            </TABLE>\n");
      out.write("        </FORM>\n");
      out.write("    </LI>\n");
      out.write("    <LI><BR></LI>\n");
      out.write("        ");

            }
            if (notesReportBean != null && notesUserBean != null)
            {
                List<NoteBean> nList = (List<NoteBean>) notesReportBean.getDataObject(ReportBean.DO_NOTES_LIST);

                if (nList != null && nList.size() > 0)
                {
                    for (Iterator<NoteBean> rowIterator = nList.iterator(); rowIterator.hasNext();)
                    {
                        NoteBean note = (NoteBean) rowIterator.next();
                        AlarmTypeBean noteAlarmTypeBean = null;//(AlarmTypeBean) notesUserBean.getAlarmTypeFilter(). getItem(note.getTagName());
        
      out.write("\n");
      out.write("    <LI>\n");
      out.write("        <table width='100%'>\n");
      out.write("            <TR>\n");
      out.write("                <TD rowspan=\"7\">\n");
      out.write("                    ");

                        if (notesUserBean.hasPermission(IConstants.PERMISSION_DELETE_COMMENT))
                        {
                    
      out.write("\n");
      out.write("                    <FORM action=\"");
      out.print( IConstants.servlet_url);
      out.write("\" method=\"post\" >\n");
      out.write("                        <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_NOTE_MODE);
      out.write("\" value=\"");
      out.print( IConstants.MODE_DELETE);
      out.write("\">\n");
      out.write("                        <INPUT type=\"hidden\" name =\"");
      out.print( IConstants.PARAM_NOTE_ID);
      out.write("\" value=\"");
      out.print( note.getNoteID());
      out.write("\">\n");
      out.write("                        <input type=\"image\"  id=\"button\" src=\"images\\Trash.ico\" alt=\"");
      out.print( notesUserBean.getLabelsBean().getGo());
      out.write("\"  width=\"24px\" height=\"24px\" >\n");
      out.write("                    </FORM>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                    <img src=\"images\\flags\\");
      out.print( note.getFlagID());
      out.write(".png\"  style=\"width:24px;height:24px;\">\n");
      out.write("                </TD>\n");
      out.write("            </TR>           \n");
      out.write("            <TR><TD>");
      out.print(notesUserBean.getLabelsBean().getDescription());
      out.write("</TD><TD>");
      out.print( note.getDescription());
      out.write("</TD></TR>\n");
      out.write("            <TR><TD>");
      out.print(notesUserBean.getLabelsBean().getUsername());
      out.write("</TD><TD>");
      out.print( note.getUserName());
      out.write("</TD></TR>\n");
      out.write("            <TR><TD>");
      out.print(notesUserBean.getLabelsBean().getTime());
      out.write("</TD><TD>");
      out.print( note.getCreated());
      out.write("</TD></TR>\n");
      out.write("            <TR>\n");
      out.write("                <TD colspan=\"3\">");
      out.print( note.getNote());
      out.write("</TD>\n");
      out.write("            </TR>\n");
      out.write("        </table>\n");
      out.write("    </LI>\n");
      out.write("    <LI><BR></LI>\n");
      out.write("        ");

                        }
                    }
                }
            }
        
      out.write("\n");
      out.write("</table>\n");
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
