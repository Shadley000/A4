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
public class InstallationsDB
{

    static public String getInstallations()
    {
        return "select distinct "
                + "i.client_id, "
                + "i.installation_id, "
                + "i.installation_name, "
                + "i.logo_file "
                + "from USERS_TO_PERMISSIONS p, INSTALLATION i "
                + "where "
                + "i.installation_id = p.installation_id "
                +" and p.client_ID = i.client_ID "
                + "and p.client_ID = ? "
                + "and p.user_id = ? "
                + "order by i.installation_id;";
    }

    public static List<InstallationBean> getInstallationList(Connection connection, BasicContext context) throws SQLException
    {
        List<InstallationBean> dataList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(getInstallations());
        stmt.setInt(1, context.getClientID());
        stmt.setInt(2, context.getUserID());
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            dataList.add(new InstallationBean(rs));
        }
        rs.close();
        stmt.close();

        return dataList;
    }

}
