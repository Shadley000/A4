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
public class UserBean implements Serializable, ColumnHeaders
{

    private int clientID = 0;
    private int userID = 0;
    private String login = null;
    private String emailAddress = null;
    private String password = null;
    private String status = null;

    public UserBean()
    {

    }

    
    public UserBean(ResultSet rs) throws SQLException
    {
        clientID = rs.getInt(CH_CLIENT_ID);
        userID = rs.getInt(CH_USER_ID);
        login = rs.getString(CH_LOGIN);
        emailAddress = rs.getString(CH_EMAIL_ADDRESS );
        password = rs.getString(CH_USER_PASSWORD);
        status = rs.getString(CH_USERSTATUS);
    }

    public int getClientID()
    {
        return clientID;
    }

    public int getUserID()
    {
        return userID;
    }

    public String getLogin()
    {
        return login;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public String getStatus()
    {
        return status;
    }

    public void setClientID(int i)
    {
        clientID = i;
    }

    public void setUserID(int i)
    {
        userID = i;
    }

    public void setLogin(String s)
    {
        login = s;
        if (s == null)
        {
            login = "";
        }
        if (s.length() > 64)
        {
            login = s.substring(0, 64);
        }
    }

    public void setEmailAddress(String s)
    {
        emailAddress = s;
        if (s == null)
        {
            emailAddress = "";
        }
        if (s.length() > 128)
        {
            emailAddress = s.substring(0, 128);
        }
    }

    public void setPassword(String s)
    {
        password = s;
        if (s == null)
        {
            password = "";
        }
        if (s.length() > 64)
        {
            password = s.substring(0, 64);
        }
    }

    public void setStatus(String s)
    {
        status = s;
        if (s == null)
        {
            status = "";
        }
        if (s.length() > 48)
        {
            status = s.substring(0, 64);
        }
    }
}
