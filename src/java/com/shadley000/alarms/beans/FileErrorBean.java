/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.alarms.IConstants;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shadl
 */
public class FileErrorBean implements Serializable, ColumnHeaders
{

    private int clientID;
    private int installationID;
    private int vendorID;
    private int file_ID;
    private String errorMessage;
    private int linenum;

    public FileErrorBean()
    {
    }
    
    
    public FileErrorBean(ResultSet rs) throws SQLException
    {
        clientID = rs.getInt(CH_CLIENT_ID);
        installationID = rs.getInt(CH_INSTALLATION_ID);
        vendorID = rs.getInt(CH_VENDOR_ID);

        file_ID = rs.getInt(CH_FILE_ID);
        errorMessage = rs.getString(CH_ERROR_MESSAGE);
        linenum = rs.getInt(CH_LINENUM);

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
    public int getFileID()
    {
        return file_ID;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
    public int getLineNumber()
    {
        return linenum;
    }
}
