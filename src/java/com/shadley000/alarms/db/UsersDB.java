package com.shadley000.alarms.db;

import com.shadley000.alarms.BasicContext;
import com.shadley000.alarms.beans.InstallationBean;
import com.shadley000.alarms.beans.PermissionsBean;
import com.shadley000.alarms.beans.UserBean;
import com.shadley000.alarms.beans.VendorBean;
import com.shadley000.utils.NameValuePair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDB
{

    static private String getUserBean()
    {
        return "select CLIENT_ID,USER_ID,LOGIN,USER_PASSWORD,EMAIL_ADDRESS,userStatus "
                + "from USERS where CLIENT_ID=? and login = ? and USER_PASSWORD = ?";
    }

    static private String insertLoginAttempt()
    {
        return " insert into LOGIN_ATTEMPT (CLIENT_ID,USER_NAME, LOGIN_TIME, USER_IP_ADDRESS) values (?,?, now(), ?);";
    }

    static private String getPermissions()
    {
        return "SELECT  p.PERMISSION_ID, p.DESCRIPTION "
                + " FROM USERS u, PERMISSIONS p, USERS_TO_PERMISSIONS t "
                + " WHERE  "
                + " u.USER_ID = t.USER_ID "
                + " AND u.CLIENT_ID = t.CLIENT_ID "
                + " AND t.PERMISSION_ID = p.PERMISSION_ID "
                + " and t.CLIENT_ID=? "
                + " and t.Installation_ID = ? "
                + " and t.vendor_id = ? "
                + " and u.user_ID = ? "
                + " order by p.PERMISSION_ID ";
    }

    public static Map<Integer, PermissionsBean> getPermissions(Connection connection, BasicContext context) throws SQLException
    {
        Map<Integer, PermissionsBean> dataMap = new HashMap<>();

        PreparedStatement stmt = connection.prepareStatement(getPermissions());
        stmt.setInt(1, context.getClientID());
        stmt.setInt(2, context.getInstallationID());
        stmt.setInt(3, context.getVendorID());
        stmt.setInt(4, context.getUserID());
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {

            PermissionsBean bean = new PermissionsBean(rs);
            dataMap.put(bean.getPermissionID(), bean);
        }
        rs.close();
        stmt.close();

        return dataMap;

    }

    public static void logLogin(Connection connection, String clientID, String userName, String ipaddress) throws SQLException
    {
        PreparedStatement stmt = connection.prepareStatement(insertLoginAttempt());
        stmt.setString(1, clientID);
        stmt.setString(2, userName);
        stmt.setString(3, ipaddress);
        stmt.execute();
        stmt.close();
    }

    public static UserBean getUserBean(Connection connection, int clientID, String userName, String password) throws SQLException
    {
        String userId = null;
        List<NameValuePair> tagList = new ArrayList<NameValuePair>();

        PreparedStatement stmt = connection.prepareStatement(getUserBean());
        stmt.setInt(1, clientID);
        stmt.setString(2, userName);
        stmt.setString(3, password);
        ResultSet rs = stmt.executeQuery();

        UserBean userBean = null;
        if (rs.next())
        {
            userBean = new UserBean(rs);
        }

        rs.close();
        stmt.close();

        return userBean;
    }
}
