/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms;

import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.beans.ClientBean;
import com.shadley000.alarms.beans.FiltersDB;
import com.shadley000.alarms.beans.PermissionsBean;
import com.shadley000.alarms.db.InstallationsDB;
import com.shadley000.alarms.beans.UserBean;
import com.shadley000.alarms.db.ClientDB;
import com.shadley000.alarms.db.FlagsDB;
import com.shadley000.alarms.db.LabelsBeanDB;
import com.shadley000.alarms.db.PagesDB;
import com.shadley000.alarms.db.UsersDB;
import com.shadley000.alarms.db.VendorDB;
import com.shadley000.histogram.TimeBin;
import com.shadley000.utils.SelectableFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shadl
 */
public class UserSessionBean implements IConstants
{

    private LabelsBean labelsBean = null;

    private ClientBean clientBean = null;
    private UserBean userBean = null;
    private Map<Integer, PermissionsBean> permissionsMap = null;

    Date startDate = null;
    Date endDate = null;

    SelectableFilter installationFilter = null;
    SelectableFilter vendorFilter = null;

    SelectableFilter pageFilter = null;
    SelectableFilter alarmTypeFilter = null;
    SelectableFilter priorityFilter = null;
    SelectableFilter maintFilter = null;
    SelectableFilter systemFilter = null;
    SelectableFilter subSystemFilter = null;
    SelectableFilter messageTypeFilter = null;
    SelectableFilter statusFilter = null;

    SelectableFilter timeBinFilter = null;

    public UserSessionBean(HttpServletRequest request, Connection connection, String clientID, String userName, String password) throws SQLException
    {
        clientBean = ClientDB.getClientBean(connection, clientID);
        userBean = UsersDB.getUserBean(connection, clientBean.getClientID(), userName, password);

        if (userBean != null)
        {   //successfull login
            //load the users labelsBean

            initializeData(request, connection);

            Date defaultStartDate = new Date(System.currentTimeMillis() - (14 * ONE_DAY));
            Date defaultEndDate = new Date();
            try
            {
                startDate = htmlDateFormat.parse(htmlDateFormat.format(defaultStartDate));
                endDate = htmlDateFormat.parse(htmlDateFormat.format(defaultEndDate));

            } catch (ParseException ex)
            {
            }
        } else
        {   //load default labelsBean
            labelsBean = LabelsBeanDB.getLabelsBean(connection, null);

        }
    }

    public BasicContext getBasicContext()
    {

        BasicContext context = new BasicContext();
        context.setClientID(clientBean.getClientID());
        context.setUserID(userBean.getUserID());
        context.setInstallationID(installationFilter.getSelectedKey());
        context.setVendorID(vendorFilter.getSelectedKey());
        return context;

    }

    public void update(HttpServletRequest request, Connection connection) throws SQLException
    {
        boolean installationChanged = installationFilter.setSelectedItem(request.getParameter(IConstants.PARAM_INSTALLATION_ID));
        boolean vendorChanged = vendorFilter.setSelectedItem(request.getParameter(IConstants.PARAM_VENDOR_ID));

        if (installationChanged || vendorChanged)
        {
            initializeData(request, connection);
        } else
        {

            BasicContext context = getBasicContext();

            boolean pageChanged = pageFilter.setSelectedItem(request.getParameter(PARAM_REPORT_NAME));
            boolean statusChanged = statusFilter.setSelectedItem(request.getParameter(PARAM_STATUS));

            boolean priorityChanged = priorityFilter.setSelectedItem(request.getParameter(PARAM_PRIORITY_FLAG_FILTER));
            boolean maintChanged = maintFilter.setSelectedItem(request.getParameter(PARAM_MAINTENANCE_FLAG_FILTER));

            boolean systemChanged = systemFilter.setSelectedItem(request.getParameter(PARAM_SYSTEM));
            boolean subSystemChanged = subSystemFilter.setSelectedItem(request.getParameter(PARAM_SUBSYSTEM));
            boolean messageTypeChanged = messageTypeFilter.setSelectedItem(request.getParameter(PARAM_MESSAGE_TYPE));
            boolean alarmTypeChanged = alarmTypeFilter.setSelectedItem(request.getParameter(PARAM_TAGNAME));

            if (systemChanged)
            {
                String systemKey = systemFilter.getSelectedKey();

                String oldKey = subSystemFilter.getSelectedKey();

                subSystemFilter = new SelectableFilter(FiltersDB.getSubSystemBeanList(connection, context, systemKey));
                subSystemFilter.setSelectedItem(oldKey);
                subSystemChanged = true;

            }

            if (systemChanged || subSystemChanged)
            {

                String systemKey = systemFilter.getSelectedKey();
                String subSystemKey = subSystemFilter.getSelectedKey();

                String oldKey = messageTypeFilter.getSelectedKey();
                messageTypeFilter = new SelectableFilter(FiltersDB.getMessageTypeBeanList(connection, context, systemKey, subSystemKey));
                messageTypeFilter.setSelectedItem(oldKey);
                messageTypeChanged = true;
            }

            if (priorityChanged || maintChanged || systemChanged || subSystemChanged || messageTypeChanged)
            {
                String oldKey = alarmTypeFilter.getSelectedKey();

                String priorityKey = priorityFilter.getSelectedKey();
                String maintKey = maintFilter.getSelectedKey();
                String systemKey = systemFilter.getSelectedKey();
                String subSystemKey = subSystemFilter.getSelectedKey();
                String messageTypeKey = messageTypeFilter.getSelectedKey();

                alarmTypeFilter = new SelectableFilter(FiltersDB.getAlarmTypeBeanList(connection, context, priorityKey, maintKey, systemKey, subSystemKey, messageTypeKey));
                alarmTypeFilter.setSelectedItem(oldKey);
                alarmTypeChanged = true;
            }
        }

        Date myStart = makeDate(request.getParameter(IConstants.PARAM_START_DATE), request.getParameter(IConstants.PARAM_START_TIME));
        Date myEnd = makeDate(request.getParameter(IConstants.PARAM_END_DATE), request.getParameter(IConstants.PARAM_END_TIME));

        if (myStart != null)
        {
            startDate = myStart;
        }
        if (myEnd != null)
        {
            endDate = myEnd;
        }

        long deltaT = endDate.getTime() - startDate.getTime();

        if (deltaT < 0)
        {
            endDate = new Date(startDate.getTime() + ONE_DAY * 14);

        } else if (deltaT > IConstants.ONE_MONTH * 6)
        {
            endDate = new Date(startDate.getTime() + ONE_MONTH * 6);
        }

        timeBinFilter.setSelectedItem(request.getParameter(IConstants.PARAM_TIMEBIN));
    }

    public void initializeData(HttpServletRequest request, Connection connection) throws SQLException
    {

        BasicContext context = new BasicContext();
        context.setClientID(clientBean.getClientID());
        context.setUserID(userBean.getUserID());

        installationFilter = new SelectableFilter(InstallationsDB.getInstallationList(connection, context));
        installationFilter.setSelectedItem(request.getParameter(IConstants.PARAM_INSTALLATION_ID));
        context.setInstallationID(installationFilter.getSelectedKey());

        vendorFilter = new SelectableFilter(VendorDB.getVendorList(connection, context));
        vendorFilter.setSelectedItem(request.getParameter(IConstants.PARAM_VENDOR_ID));
        context.setVendorID(vendorFilter.getSelectedKey());

        labelsBean = LabelsBeanDB.getLabelsBean(connection, context);
        permissionsMap = UsersDB.getPermissions(connection, context);

        pageFilter = new SelectableFilter(PagesDB.loadPageList(connection, context, labelsBean, permissionsMap));
        priorityFilter = new SelectableFilter(FlagsDB.getFlagsBeans(connection));
        maintFilter = new SelectableFilter(FlagsDB.getFlagsBeans(connection));

        systemFilter = new SelectableFilter(FiltersDB.getSystemBeanList(connection, context));
        subSystemFilter = new SelectableFilter(FiltersDB.getSubSystemBeanList(connection, context, null));
        statusFilter = new SelectableFilter(FiltersDB.getStatusBeanList(connection, context));

        messageTypeFilter = new SelectableFilter(FiltersDB.getMessageTypeBeanList(connection, context, null, null));

        alarmTypeFilter = new SelectableFilter(FiltersDB.getAlarmTypeBeanList(connection, context, null, null, null, null, null));

        timeBinFilter = new SelectableFilter(TimeBin.getBinList(labelsBean));

    }

    private static java.util.Date makeDate(String date, String time)
    {
        if (date != null && time != null)
        {
            try
            {
                return new Date(htmlDateTimeFormat.parse(date + " " + time).getTime());
            } catch (ParseException e)
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    public boolean hasPermission(Integer key)
    {
        if (permissionsMap == null)
        {
            return false;
        }
        return permissionsMap.containsKey(key);
    }

    public boolean isAuthenticated()
    {
        return (userBean != null);
    }

    public void invalidate()
    {
        userBean = null;
    }

    public LabelsBean getLabelsBean()
    {
        return labelsBean;
    }

    public ClientBean getClientBean()
    {
        return clientBean;
    }

    public UserBean getUserBean()
    {
        return userBean;
    }

    public Date getStart()
    {
        return startDate;
    }

    public Date getEnd()
    {
        return endDate;
    }

    public SelectableFilter getInstallationFilter()
    {
        return installationFilter;
    }

    public SelectableFilter getVendorFilter()
    {
        return vendorFilter;
    }

    public SelectableFilter getPageFilter()
    {
        return pageFilter;
    }

    public SelectableFilter getAlarmTypeFilter()
    {
        return alarmTypeFilter;
    }

    public SelectableFilter getPriorityFilter()
    {
        return priorityFilter;
    }

    public SelectableFilter getMaintFilter()
    {
        return maintFilter;
    }

    public SelectableFilter getSystemFilter()
    {
        return systemFilter;
    }

    public SelectableFilter getSubSystemFilter()
    {
        return subSystemFilter;
    }

    public SelectableFilter getMessageTypeFilter()
    {
        return messageTypeFilter;
    }

    public SelectableFilter getStatusFilter()
    {
        return statusFilter;
    }

    public SelectableFilter getTimeBinFilter()
    {
        return timeBinFilter;
    }
}
