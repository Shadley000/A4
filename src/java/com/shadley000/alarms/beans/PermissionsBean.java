/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shadl
 */
public class PermissionsBean implements Serializable, ColumnHeaders
{

    int permissionID;
    String description;

    public PermissionsBean()
    {
    }

    public PermissionsBean(ResultSet rs) throws SQLException
    {
        permissionID = rs.getInt(CH_PERMISSION_ID);
        description = rs.getString(CH_DESCRIPTION);
    }

    public int getPermissionID()
    {
        return permissionID;
    }

    public String getDescription()
    {
        return description;
    }
}
