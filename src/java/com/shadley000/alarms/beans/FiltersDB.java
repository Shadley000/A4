/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.utils.SelectableBean;
import com.shadley000.alarms.BasicContext;
import com.shadley000.utils.SelectableFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shadl
 */
public class FiltersDB
{

    public static List<SelectableBean> getStatusBeanList(Connection connection, BasicContext context) throws SQLException
    {
        String sql = "SELECT DISTINCT ALM_STATUS 'ID', ALM_STATUS 'NAME' FROM ALARM_DATA where "
                + " CLIENT_ID = ?  AND INSTALLATION_ID = ?  AND VENDOR_ID = ?; ";

        List<SelectableBean> dataList = new ArrayList<>();
        dataList.add(new SelectableBean(SelectableFilter.DISABLE_SELECT_KEY, "---"));
        {
            try (PreparedStatement stmt = connection.prepareStatement(sql))
            {
                stmt.setInt(1, context.getClientID());
                stmt.setInt(2, context.getInstallationID());
                stmt.setInt(3, context.getVendorID());
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        dataList.add(new SelectableBean(rs));
                    }
                }
            }
        }
        return dataList;
    }

    public static List<SelectableBean> getSystemBeanList(Connection connection, BasicContext context) throws SQLException
    {
        String sql = "SELECT DISTINCT SYSTEM 'ID', SYSTEM 'NAME' FROM ALARM_TYPE where "
                + " CLIENT_ID = ?  AND INSTALLATION_ID = ?  AND VENDOR_ID = ?; ";

        List<SelectableBean> dataList = new ArrayList<>();
        dataList.add(new SelectableBean(SelectableFilter.DISABLE_SELECT_KEY, "---"));
        {
            try (PreparedStatement stmt = connection.prepareStatement(sql))
            {
                stmt.setInt(1, context.getClientID());
                stmt.setInt(2, context.getInstallationID());
                stmt.setInt(3, context.getVendorID());
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        dataList.add(new SelectableBean(rs));
                    }
                }
            }
        }
        return dataList;
    }

    public static List<SelectableBean> getSubSystemBeanList(Connection connection, BasicContext context, String system) throws SQLException
    {
        String sql = "SELECT DISTINCT SUBSYSTEM 'ID', SUBSYSTEM 'NAME' FROM ALARM_TYPE where "
                + " CLIENT_ID = ? "
                + " AND INSTALLATION_ID = ? "
                + " AND VENDOR_ID = ? ";
        if (system != null )
        {
            sql += " and SYSTEM = ? ;";
        }

        List<SelectableBean> dataList = new ArrayList<>();
        dataList.add(new SelectableBean(SelectableFilter.DISABLE_SELECT_KEY, "---"));
        {
            try (PreparedStatement stmt = connection.prepareStatement(sql))
            {
                stmt.setInt(1, context.getClientID());
                stmt.setInt(2, context.getInstallationID());
                stmt.setInt(3, context.getVendorID());
                if (system != null)
                {
                    stmt.setString(4, system);
                }
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        dataList.add(new SelectableBean(rs));
                    }
                }
            }
        }
        return dataList;
    }

    public static List<SelectableBean> getMessageTypeBeanList(Connection connection, BasicContext context, String systemFilter, String subSystemFilter) throws SQLException
    {
        String sql = "SELECT DISTINCT MSGTYPE 'ID', MSGTYPE 'NAME' FROM ALARM_TYPE where "
                + " CLIENT_ID = ?  AND INSTALLATION_ID = ?  AND VENDOR_ID = ? ";
        if (systemFilter != null)
        {
            sql += "AND SYSTEM = ? ";
        }
        if (subSystemFilter != null)
        {
            sql += "AND SUBSYSTEM = ? ";
        }

        List<SelectableBean> dataList = new ArrayList<>();
        dataList.add(new SelectableBean(SelectableFilter.DISABLE_SELECT_KEY, "---"));

        {
            try (PreparedStatement stmt = connection.prepareStatement(sql))
            {
                stmt.setInt(1, context.getClientID());
                stmt.setInt(2, context.getInstallationID());
                stmt.setInt(3, context.getVendorID());
                int i = 4;
                if (systemFilter != null)
                {
                    stmt.setString(i, systemFilter);
                    i++;
                }

                if (subSystemFilter != null)
                {
                    stmt.setString(i, subSystemFilter);
                    i++;
                }
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        dataList.add(new SelectableBean(rs));
                    }
                }
            }
        }
        return dataList;
    }

    public static List<SelectableBean> getAlarmTypeBeanList(Connection connection, BasicContext context,
            String priorityFilter, String maintFilter, String systemFilter, String subSystemFilter, String messageTypeFilter) throws SQLException
    {
        String sql = "SELECT DISTINCT TAGNAME 'ID', concat(SYSTEM, ' ' ,SUBSYSTEM, ' ' ,DESCRIPTION) 'NAME' "
                + " from ALARM_TYPE "
                + "where  "
                + "CLIENT_ID = ? "
                + "AND INSTALLATION_ID = ? "
                + "AND VENDOR_ID = ? ";
        if (priorityFilter != null)
        {
            sql += "AND FLAG_ID_PRIORITY >= ? ";
        }
        if (maintFilter != null)
        {
            sql += "AND FLAG_ID_MAINTENANCE >= ? ";
        }
        if (systemFilter != null)
        {
            sql += "AND SYSTEM = ? ";
        }
        if (subSystemFilter != null)
        {
            sql += "AND SUBSYSTEM = ? ";
        }

        if (messageTypeFilter != null)
        {
            sql += "AND MSGTYPE = ? ";
        }

        sql += "order by SYSTEM, SUBSYSTEM,DESCRIPTION; ";

        List<SelectableBean> dataList = new ArrayList<>();
        dataList.add(new SelectableBean(SelectableFilter.DISABLE_SELECT_KEY, "---"));
        {
            try (PreparedStatement stmt = connection.prepareStatement(sql))
            {
                stmt.setInt(1, context.getClientID());
                stmt.setInt(2, context.getInstallationID());
                stmt.setInt(3, context.getVendorID());
                int i = 4;
                if (priorityFilter != null)
                {
                    stmt.setString(i, priorityFilter);
                    i++;
                }
                if (maintFilter != null)
                {
                    stmt.setString(i, maintFilter);
                    i++;
                }
                if (systemFilter != null)
                {
                    stmt.setString(i, systemFilter);
                    i++;
                }

                if (subSystemFilter != null)
                {
                    stmt.setString(i, subSystemFilter);
                    i++;
                }
                if (messageTypeFilter != null)
                {
                    stmt.setString(i, messageTypeFilter);
                    i++;
                }
                try (ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next())
                    {
                        dataList.add(new SelectableBean(rs));
                    }
                }
            }
        }
        return dataList;
    }
}
