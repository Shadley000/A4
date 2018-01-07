

<%@page import="com.shadley000.utils.SelectableFilter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.shadley000.alarms.db.ClientDB"%>
<%@page import="com.shadley000.utils.AlarmsSQLConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.shadley000.alarms.beans.LabelsBean"%>
<%@page import="com.shadley000.alarms.beans.ClientBean"%>
<%@page import="com.shadley000.alarms.beans.VendorBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.shadley000.alarms.beans.InstallationBean"%>
<%@page import="com.shadley000.alarms.UserSessionBean"%>
<%@page import="java.util.List"%>
<%@page import="com.shadley000.alarms.IConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
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
%>
<nav>
    <FORM action="A4" method="post"> 
        <UL>
            <li>  <%= userBean.getInstallationFilter().getSelectHTML(IConstants.PARAM_INSTALLATION_ID, true)%></li>
            <li><%= userBean.getVendorFilter().getSelectHTML(IConstants.PARAM_VENDOR_ID, true)%></li>
        </UL> 
    </FORM>
    <UL>
        <li><img src="<%=installationLogo%>" style=\"width:128px;height:128px;\"></li>
        <li><img src="<%=vendorLogo%>" style=\"width:128px;height:128px;\"></li>
    </UL> 
    <FORM action="A4" method="post">            
        <UL>
            <li><%= labelsBean.getReport()%></li>       
            <li><%= userBean.getPageFilter().getSelectHTML(IConstants.PARAM_REPORT_NAME, true)%></li>
        </UL>      
    </FORM>
    <FORM action="A4" method="post">
        <ul>
            <li><%= labelsBean.getSystem()%></li>  
            <li><%= userBean.getSystemFilter().getSelectHTML(IConstants.PARAM_SYSTEM, true)%></li>
            <li><%= labelsBean.getSubsystem()%></li>  
            <li><%= userBean.getSubSystemFilter().getSelectHTML(IConstants.PARAM_SUBSYSTEM, true)%></li>
            <li><%= labelsBean.getType()%></li>  
            <li><%= userBean.getMessageTypeFilter().getSelectHTML(IConstants.PARAM_MESSAGE_TYPE, true)%></li>
            <li><%= labelsBean.getTag()%></li>  
            <li><%= userBean.getAlarmTypeFilter().getSelectHTML(IConstants.PARAM_TAGNAME, true)%></li>
            <li><%= labelsBean.getStatus()%></li>  
            <li><%= userBean.getStatusFilter().getSelectHTML(IConstants.PARAM_STATUS, true)%></li>           
            <li><BR></li>
            <li><%= labelsBean.getAlarmFlagFilter()%></li>
            <li><%=  userBean.getPriorityFilter().getFlagRadioHTML(IConstants.PARAM_PRIORITY_FLAG_FILTER, 3, true)%></li>
            <li><BR></li>
            <li><%= labelsBean.getMaintFlagFilter()%></li>
            <li><%=  userBean.getMaintFilter().getFlagRadioHTML(IConstants.PARAM_MAINTENANCE_FLAG_FILTER, 3, true)%></li>
        </ul>
    </form>
    <FORM action="A4" method="post">
        <ul>
            <li><%= labelsBean.getStart()%></li>
            <li><INPUT id="menu" type="text" name="<%=IConstants.PARAM_START_DATE%>" value="<%=startDate%>"> </INPUT></li>
            <li><INPUT id="menu" type="text" name="<%=IConstants.PARAM_START_TIME%>" value="<%=startTime%>"> </INPUT></li>
            <li><%= labelsBean.getEnd()%></li>
            <li><INPUT  id="menu" type="text" name="<%=IConstants.PARAM_END_DATE%>" value="<%=endDate%>"> </INPUT></li>
            <li><INPUT id="menu" type="text" name="<%=IConstants.PARAM_END_TIME%>" value="<%=endTime%>"> </INPUT></li>
            <li><%= labelsBean.getTimeResolution()%></li>
            <li><%=  userBean.getTimeBinFilter().getSelectHTML(IConstants.PARAM_TIMEBIN, false)%></li>
            <li><INPUT  id="menu" type="submit"  value="<%= labelsBean.getGo()%>"> </li>
        </ul>
    </FORM>
</nav>
<%
} else
{
%>
<nav> 
    <FORM action="A4" method="post">
        <UL>
            <li><%= clientFilter.getSelectHTML(IConstants.PARAM_CLIENT_ID, false)%></li>  
            <li>User:<INPUT   id="menu" type="text" name="<%=IConstants.PARAM_USER_NAME%>" value=""></INPUT>
            <li>Password:<INPUT   id="menu"  type="password" name="<%=IConstants.PARAM_PASSWORD%>" value=""></INPUT>         
            <li><INPUT  id="menu" type="submit" name="go"></INPUT></li>
        </ul>
    </FORM>
</nav>
<%
        }
    }
%>
