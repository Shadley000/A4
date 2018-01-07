<%-- 
    Document   : notes
    Created on : Nov 26, 2016, 3:23:26 PM
    Author     : shadl
--%>

<%@page import="com.shadley000.alarms.beans.AlarmTypeBean"%>
<%@page import="com.shadley000.alarms.beans.NoteBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.pages.PageAlarmDetail"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    {
        ReportBean notesReportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
        UserSessionBean notesUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

        String tagname =notesUserBean.getAlarmTypeFilter().getSelectedKey();
%>
<UL  style="list-style: none;">
    <%
        if (notesUserBean.hasPermission(IConstants.PERMISSION_COMMENT) )
        {
    %>
    <LI>
        <FORM action="<%= IConstants.servlet_url%>" method="post" >
            <table>
                <TR>
                    <TD  colspan="1">
                        <input type="image"  id="button" src="images\Save.ico" alt="<%= notesUserBean.getLabelsBean().getGo()%>"  width="24px" height="24px" >
                        <%= notesUserBean.getLabelsBean().getCreateNewNote()%>
                    </TD>
                    <TD colspan="2"><%= notesUserBean.getLabelsBean().getFlag()%>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="0" checked><img src="images\flags\0.png"  style="width:24px;height:24px;"></input>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="1"><img src="images\flags\1.png"  style="width:24px;height:24px;"></input>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="2"><img src="images\flags\2.png"  style="width:24px;height:24px;"></input>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="3"><img src="images\flags\3.png"  style="width:24px;height:24px;"></input>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="4"><img src="images\flags\4.png"  style="width:24px;height:24px;"></input>
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="5"><img src="images\flags\5.png"  style="width:24px;height:24px;"></input>                   
                        <input type="radio" name="<%= IConstants.PARAM_FLAG_ID%>" value="6"><img src="images\flags\6.png"  style="width:24px;height:24px;"></input>
                    </TD> 
                </TR>
                
                <TR><TD colspan="3">
                        <INPUT type="hidden" name ="<%= IConstants.PARAM_TAGNAME%>" value="<%=tagname%>">
                        <INPUT type="hidden" name ="<%= IConstants.PARAM_NOTE_MODE%>" value="<%= IConstants.MODE_ADD%>">
                        <textarea rows="10" cols="100" name="<%=IConstants.PARAM_NOTE_TEXT%>"></textarea>
                    </TD></TR>
            </TABLE>
        </FORM>
    </LI>
    <LI><BR></LI>
        <%
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
        %>
    <LI>
        <table width='100%'>
            <TR>
                <TD rowspan="7">
                    <%
                        if (notesUserBean.hasPermission(IConstants.PERMISSION_DELETE_COMMENT))
                        {
                    %>
                    <FORM action="<%= IConstants.servlet_url%>" method="post" >
                        <INPUT type="hidden" name ="<%= IConstants.PARAM_NOTE_MODE%>" value="<%= IConstants.MODE_DELETE%>">
                        <INPUT type="hidden" name ="<%= IConstants.PARAM_NOTE_ID%>" value="<%= note.getNoteID()%>">
                        <input type="image"  id="button" src="images\Trash.ico" alt="<%= notesUserBean.getLabelsBean().getGo()%>"  width="24px" height="24px" >
                    </FORM>
                    <%
                        }
                    %>
                    <img src="images\flags\<%= note.getFlagID()%>.png"  style="width:24px;height:24px;">
                </TD>
            </TR>           
            <TR><TD><%=notesUserBean.getLabelsBean().getDescription()%></TD><TD><%= note.getDescription()%></TD></TR>
            <TR><TD><%=notesUserBean.getLabelsBean().getUsername()%></TD><TD><%= note.getUserName()%></TD></TR>
            <TR><TD><%=notesUserBean.getLabelsBean().getTime()%></TD><TD><%= note.getCreated()%></TD></TR>
            <TR>
                <TD colspan="3"><%= note.getNote()%></TD>
            </TR>
        </table>
    </LI>
    <LI><BR></LI>
        <%
                        }
                    }
                }
            }
        %>
</table>
