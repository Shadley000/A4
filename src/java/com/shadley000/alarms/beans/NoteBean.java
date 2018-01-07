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
public class NoteBean implements Serializable, ColumnHeaders
{

    private int clientID;
    private int installationID;
    private int vendorID;
    private int userID;
    private int noteID;
    private int flagID;
    private String tagName;
    private String description;
    private java.util.Date created;
    private String note;
    private String userName;

    public NoteBean()
    {

    }

    public NoteBean(ResultSet rs) throws SQLException
    {
        clientID = rs.getInt(CH_CLIENT_ID);
        installationID = rs.getInt(CH_INSTALLATION_ID);
        vendorID = rs.getInt(CH_VENDOR_ID);
        userID = rs.getInt(CH_USER_ID);
        flagID = rs.getInt(CH_FLAG_ID);
        tagName = rs.getString(CH_TAGNAME);
        noteID = rs.getInt(CH_NOTES_ID);
        description = rs.getString(CH_DESCRIPTION);
        created = rs.getTimestamp(CH_CREATED);
        note = rs.getString(CH_USERCOMMENT);
        userName = rs.getString(CH_LOGIN);

    }

    public int getClientID()
    {
        return clientID;
    }

    public int getNoteID()
    {
        return noteID;
    }

    public int getInstallationID()
    {
        return installationID;
    }

    public int getVendorID()
    {
        return vendorID;
    }

    public int getUserID()
    {
        return userID;
    }
    
    
    public int getFlagID(){return flagID;}

    public String getUserName()
    {
        return userName;
    }

    public String getTagName()
    {
        return tagName;
    }

    public String getDescription()
    {
        return description;
    }

    public java.util.Date getCreated()
    {
        return created;
    }

    public String getNote()
    {
        return note;
    }
}
