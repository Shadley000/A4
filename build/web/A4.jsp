
<%@page import="com.shadley000.alarms.UserSessionBean"%> 
<%@page import="com.shadley000.alarms.ReportBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>

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
                
                <% {
                        ReportBean reportBean = (ReportBean) request.getAttribute(IConstants.PARAM_REPORTBEAN);

                        if (reportBean != null && reportBean.getTitle() != null)
                        {
                            out.println("<H1>" + reportBean.getTitle() + "</H1>");
                        }
                    }
                %>
            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
