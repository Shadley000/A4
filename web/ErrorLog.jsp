
<%@page import="com.shadley000.alarms.beans.FileBean"%>
<%@page import="com.shadley000.alarms.beans.FileErrorBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
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

                        FileBean fileBean = (FileBean) reportBean.getDataObject(ReportBean.DO_FILEBEAN);
                        List<FileErrorBean> dataList = (List<FileErrorBean>) reportBean.getDataObject(ReportBean.DO_FILE_ERRORBEAN_LIST);

                %>
                <H1><%= reportBean.getTitle()%></H1>
                    <%
                        if (fileBean != null && dataList != null)
                        {
                    %>
                <TABLE>
                    <TR>
                        <TH><%=userBean.getLabelsBean().getDate()%></TH> 
                        <TH align="right"><%=userBean.getLabelsBean().getFileName()%></TH> 
                        <TH><%=userBean.getLabelsBean().getLines()%></TH> 
                        <TH><%=userBean.getLabelsBean().getInserted()%></TH> 
                        <TH><%=userBean.getLabelsBean().getSkipped()%></TH> 
                        <TH><%=userBean.getLabelsBean().getErrors()%></TH> 
                    </TR>

                    <TR>
                        <TD align="left"><%= fileBean.getloadDate()%></TD>
                        <TD align="left"><%= fileBean.getFilename()%></TD>
                        <TD><%=fileBean.getDataLines()%></TD>
                        <TD><%= fileBean.getDataInserted()%></TD>
                        <TD><%= fileBean.getDataSkipped()%></TD>
                        <TD><%= fileBean.getDataError()%></TD>
                    </TR>
                </TABLE>
                <BR>
                <TABLE>
                    <TR>
                        <TH><%= userBean.getLabelsBean().getLineNum()%></TH> 
                        <TH><%=userBean.getLabelsBean().getError()%></TH> 
                    </TR>
                    <%
                        if (dataList != null)
                        {
                            for (Iterator<FileErrorBean> rowIterator = dataList.iterator(); rowIterator.hasNext();)
                            {
                                FileErrorBean bean = rowIterator.next();

                    %>
                    <TR>
                        <TD align="left"><%= bean.getLineNumber()%></TD>
                        <TD align="left"><%= bean.getErrorMessage()%></TD>
                    </TR>
                    <%
                            }
                        }
                    %>
                </TABLE>
                <%
                        }
                    }
                %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>

