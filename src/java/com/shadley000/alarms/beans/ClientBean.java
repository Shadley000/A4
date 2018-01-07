/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.alarms.IConstants;
import com.shadley000.utils.ISelectable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shadl
 */
public class ClientBean implements Serializable, ColumnHeaders, ISelectable
{
    
    int id;
    String name;
    String logoFile;
    
    
    public String getSelectableKey()
    {
        return Integer.toString(getClientID());
    }

    public String getSelectableLabel()
    {
        return getName();
    }
    
    public ClientBean(ResultSet rs) throws SQLException
    {
        
        id = rs.getInt(CH_CLIENT_ID);
        name = rs.getString(CH_CLIENT_NAME);
        logoFile = rs.getString(CH_CLIENT_LOGO);
    }
    
    public int getClientID() {return id;}
    public String getName() {return name;}
    public String getLogoFile() {return logoFile;}
}
