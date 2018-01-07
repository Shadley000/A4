/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.ClientBean;
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
public class ClientDB
{

    static private String getClientBeans()
    {
        return "select distinct "
                + " c.client_id, "
                + " c.client_name, "
                + " c.client_logo "
                + " from A4CLIENT c "
                + " order by c.client_name DESC;";
    }
    
    static private String getClientBean()
    {
        return "select distinct "
                + " c.client_id, "
                + " c.client_name, "
                + " c.client_logo "
                + " from A4CLIENT c "
                + "where c.client_id = ?;";
    }

    public static ClientBean getClientBean(Connection connection, String clientID) throws SQLException
    {
        ClientBean clientBean = null;

        PreparedStatement stmt = connection.prepareStatement(getClientBean());
        stmt.setString(1, clientID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            clientBean = new ClientBean(rs);
        }
        rs.close();
        stmt.close();

        return clientBean;
    }
    
     public static List<ClientBean> getClientBeans(Connection connection) throws SQLException
    {
        List<ClientBean> clientBeanList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(getClientBeans());
         ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            clientBeanList.add( new ClientBean(rs));
        }
        rs.close();
        stmt.close();

        return clientBeanList;
    }


}
