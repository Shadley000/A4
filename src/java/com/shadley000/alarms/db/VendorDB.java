/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.BasicContext;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.InstallationBean;
import com.shadley000.alarms.beans.UserBean;
import com.shadley000.alarms.beans.VendorBean;
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
public class VendorDB
{

    static public String getVendors()
    {
        return "SELECT distinct  "
                + " v.client_id, "
                + " v.installation_id, "
                + " v.vendor_id, "
                + " v.vendor_name, "
                + " v.logo_file "
                + " from USERS_TO_PERMISSIONS p, INSTALLATION i,  VENDOR v "
                + " where "
                + " p.installation_id = i.installation_id "
                + " and p.installation_id = v.installation_id "
                + " and p.client_ID = i.client_ID "
                + " and p.client_ID = v.client_ID "
                + " and p.vendor_id = v.vendor_id  "
                + " and p.client_ID = ? "
                + " and i.installation_id = ? "
                + " and p.user_id = ? "
                + " order by  v.vendor_id;";

    }

    public static List<VendorBean> getVendorList(Connection connection,BasicContext context) throws SQLException
    {
        List<VendorBean> dataList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(getVendors());
        stmt.setInt(1, context.getClientID());
        stmt.setInt(2,context.getInstallationID());
        stmt.setInt(3, context.getUserID());

        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            dataList.add(new VendorBean(rs));
        }
        rs.close();
        stmt.close();

        return dataList;
    }

}
