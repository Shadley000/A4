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
public class FileBean implements Serializable, ColumnHeaders
{

    private int clientID;
    private int installationID;
    private int vendorID;
    private int fileID;
    private String filename;
    private java.util.Date loadDate;
    private int dataLines;
    private int dataInserted;
    private int dataSkipped;
    private int dataError;

    public FileBean()
    {

    }

    
    public FileBean(ResultSet rs) throws SQLException
    {
        clientID = rs.getInt(CH_CLIENT_ID);
        installationID = rs.getInt(CH_INSTALLATION_ID);
        vendorID = rs.getInt(CH_VENDOR_ID);
        fileID = rs.getInt(CH_FILE_ID);
        filename = rs.getString(CH_FILE_NAME);
        loadDate = rs.getTimestamp(CH_LOAD_DATE);
        dataLines = rs.getInt(CH_DATA_LINES);
        dataInserted = rs.getInt(CH_DATA_INSERTED);
        dataSkipped = rs.getInt(CH_DATA_SKIPPED);
        dataError = rs.getInt(CH_DATA_ERROR);
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
        return fileID;
   }
    
    public String getFilename()
    {
        return filename;
    }
    public java.util.Date getloadDate()
    {
        return loadDate;
    }
    public int getDataLines()
    {
        return dataLines;
    }
    public int getDataInserted()
    {
        return dataInserted;
    }
    public int getDataSkipped()
    {
        return dataSkipped;
    }
    public int getDataError()
    {
        return dataError;
    }
}
