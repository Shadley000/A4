

<%@page import="java.util.Iterator"%>
<%@page import="com.shadley000.alarms.beans.LabelsBean"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<FORM action="A4" method="post">
    <TABLE>
        <%
            {
                UserSessionBean filterUserBean = (UserSessionBean) request.getSession().getAttribute(IConstants.PARAM_USERBEAN);

                if (filterUserBean != null && filterUserBean.isAuthenticated())
                {
                    LabelsBean filterLabelsBean = filterUserBean.getLabelsBean();
                    %>
                    <TR>
                        <TD><%= filterLabelsBean.getAlarmFlagFilter() %></TD>
                        <TD><%=  filterUserBean.getPriorityFilter().getFlagRadioHTML(IConstants.PARAM_PRIORITY_FLAG_FILTER, 3, true) %></TD>
                    </TR>
                    <TR>
                        <TD><%= filterLabelsBean.getMaintFlagFilter() %></TD>
                        <TD><%=  filterUserBean.getMaintFilter().getFlagRadioHTML(IConstants.PARAM_PRIORITY_FLAG_FILTER, 3, true) %></TD>
                    </TR>
                    <%
                }
            }
        %>
    </table>
</FORM>
