/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.FileBean;
import com.shadley000.alarms.beans.FileErrorBean;
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
public class FilesDB
{

    static public List<FileErrorBean> getFileErrorBeans(Connection connection, UserSessionBean userBean, int fileID) throws SQLException
    {
        String sql = "select f.CLIENT_ID, f.INSTALLATION_ID, f.VENDOR_ID,f.FILE_ID,e.ERROR_MESSAGE,e.linenum "
                + "from ERROR_LOG e, ALARM_FILE f "
                + " WHERE "
                + " f.CLIENT_ID = ? "
                + " AND f.CLIENT_ID = e.CLIENT_ID "
                + " AND e.FILE_ID = f.FILE_ID "
                + " AND f.INSTALLATION_ID = ? "
                + " AND f.VENDOR_ID = ? "
                + " AND  f.FILE_ID = ?"
                + " ORDER BY f.LOAD_DATE DESC; ";

        List<FileErrorBean> dataList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setInt(4, fileID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            dataList.add(new FileErrorBean(rs));
        }

        rs.close();
        stmt.close();
        return dataList;
    }

    static public FileBean getFileBean(Connection connection, UserSessionBean userBean, int fileID) throws SQLException
    {

        String sql = "SELECT f.CLIENT_ID, f.INSTALLATION_ID, f.VENDOR_ID, f.FILE_ID, f.FILE_NAME, f.LOAD_DATE, "
                + "f.DATA_LINES, f.DATA_INSERTED, f.DATA_SKIPPED, f.DATA_ERROR "
                + "from ALARM_FILE f, INSTALLATION i, VENDOR v "
                + "where "
                + "f.CLIENT_ID = ? "
                + "AND f.CLIENT_ID = i.CLIENT_ID "
                + "AND f.CLIENT_ID = v.CLIENT_ID "
                + "AND f.INSTALLATION_ID = i.INSTALLATION_ID "
                 + "AND f.INSTALLATION_ID = v.INSTALLATION_ID "      
                + "AND f.VENDOR_ID = v.VENDOR_ID "
                + "AND i.INSTALLATION_ID = ? "
                + "AND v.VENDOR_ID = ? "
                + "and f.FILE_ID = ?";

        FileBean fileBean = null;

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setInt(4, fileID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            fileBean = new FileBean(rs);
        }

        rs.close();
        stmt.close();
        return fileBean;
    }

    static public List<FileBean> getFileBeans(Connection connection, UserSessionBean userBean) throws SQLException
    {

        String sql = "SELECT f.CLIENT_ID, f.INSTALLATION_ID, f.VENDOR_ID, f.FILE_ID, f.FILE_NAME, f.LOAD_DATE, "
                + " f.DATA_LINES, f.DATA_INSERTED, f.DATA_SKIPPED, f.DATA_ERROR "
                + " from ALARM_FILE f, INSTALLATION i, VENDOR v "
                + " where "
                + " f.CLIENT_ID = ? "
                + " AND f.CLIENT_ID = i.CLIENT_ID "
                + " AND f.CLIENT_ID = v.CLIENT_ID "
                + " AND f.INSTALLATION_ID = i.INSTALLATION_ID "
                + "  AND f.INSTALLATION_ID = v.INSTALLATION_ID "
                + " AND f.VENDOR_ID = v.VENDOR_ID "
                + " AND i.INSTALLATION_ID = ? "
                + " AND v.VENDOR_ID = ? "
                + " ORDER BY f.LOAD_DATE; ";

        List<FileBean> dataList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            dataList.add(new FileBean(rs));
        }

        rs.close();
        stmt.close();
        return dataList;
    }
}
