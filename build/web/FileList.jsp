
<%@page import="com.shadley000.alarms.pages.PageUniqueAlarms"%>
<%@page import="com.shadley000.alarms.pages.PageErrorLog"%>
<%@page import="com.shadley000.alarms.beans.FileBean"%>
<%@page import="java.util.List"%>
<%@page import="com.shadley000.alarms.IConstants"%>
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
                <%   {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);
                        UserSessionBean userBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);
                        List<FileBean> dataList = (List<FileBean>) reportBean.getDataObject(ReportBean.DO_FILEBEAN_LIST);

                %>
                <H1><%= reportBean.getTitle()%></H1>
                <TABLE>
                    <TR>
                        <TH><%=userBean.getLabelsBean().getDate()%></TH> 
                        <TH align="right"><%=userBean.getLabelsBean().getFileName()%></TH> 
                        <TH><%=userBean.getLabelsBean().getLines()%></TH> 
                        <TH><%=userBean.getLabelsBean().getInserted()%></TH> 
                        <TH><%=userBean.getLabelsBean().getSkipped()%></TH> 
                        <TH><%=userBean.getLabelsBean().getErrors()%></TH> 
                    </TR>
                    <%
                        for (Iterator<FileBean> rowIterator = dataList.iterator(); rowIterator.hasNext();)
                        {
                            FileBean bean = rowIterator.next();

                    %>
                    <TR>
                        <TD align="left"><%= bean.getloadDate()%></TD>
                        <TD align="left"><%= bean.getFilename()%></TD>
                        <TD><%=bean.getDataLines()%></TD>
                        <TD><%= bean.getDataInserted()%></TD>
                        <TD><%= bean.getDataSkipped()%></TD>
                        <TD><%= bean.getDataError()%>
                            <%
                                if (bean.getDataError() > 0)
                                {
                            %>
                           
                            <FORM action="<%= IConstants.servlet_url%>" method="post" >
                                    <INPUT type="hidden" name ="<%= IConstants.PARAM_REPORT_NAME%>" value="<%= PageErrorLog.class.getName()%>">
                                    <INPUT type="hidden" name ="<%= IConstants.PARAM_FILE_ID%>" value="<%= bean.getFileID()%>">
                                <input type="image"  id="button" src="images\Magnifier.ico" alt="<%= userBean.getLabelsBean().getGo()%>"  width="24px" height="24px" >
                                </FORM>
                           <%
                                }
                            %>
                        </TD>
                    </TR>
                    <%
                        }
                    %>
                </TABLE>
                <%
                    }
                %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>

