/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.utils.ISelectable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shadl
 */
public class VendorBean implements Serializable, ColumnHeaders, ISelectable
{

    private int clientID;
    private int installationID;
    private int vendorID;
    private String name;
    private String logoFile;

    
    public String getSelectableKey()
    {
        return Integer.toString(getVendorID());
    }

    public String getSelectableLabel()
    {
        return getName();
    }

    public VendorBean(ResultSet rs) throws SQLException
    {
        clientID = rs.getInt(CH_CLIENT_ID);
        installationID = rs.getInt(CH_INSTALLATION_ID);
        vendorID = rs.getInt(CH_VENDOR_ID);
        name = rs.getString(CH_VENDOR_NAME);
        logoFile = rs.getString(CH_LOGO_FILE);
    }

    public int getClientID()
    {
        return clientID;
    }

    public int getInstallationID()
    {
        return installationID;
    }

    public int getVendorID()
    {
        return vendorID;
    }

    public String getName()
    {
        return name;
    }

    public String getLogoFile()
    {
        return logoFile;
    }
}
