

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

                        if (reportBean != null)
                        {
                            out.println("<H1>" + reportBean.getTitle() + "</H1>");

                        }
                    }
                %>
                <%@ include file="_alarmTypeHistogram.jsp" %>

            </article>
            <%@ include file="_footer.jsp" %>
        </div>
    </BODY>
</HTML>
