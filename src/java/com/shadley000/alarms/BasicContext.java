/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms;

/**
 *
 * @author shadl
 */
public class BasicContext
{

    int clientID = -1;
     int userID = -1;
    int installationID  = -1;
    int vendorID = -1;
   
   public BasicContext()
    {
        
    }
    
    public void setClientID(int i){clientID = i;}
    public void setUserID(int i){userID = i;}
    public void setInstallationID(int i){installationID = i;}
    public void setVendorID(int i){vendorID = i;}
    
     public void setClientID(String s){if(s!=null) clientID = Integer.parseInt(s);}
    public void setUserID(String s){if(s!=null) userID = Integer.parseInt(s);}
    public void setInstallationID(String s){if(s!=null) installationID  = Integer.parseInt(s);}
    public void setVendorID(String s){if(s!=null) vendorID = Integer.parseInt(s);}
    
    public int getClientID(){return clientID;}
    public int getUserID(){return userID;}
    public int getInstallationID(){return installationID;}
    public int getVendorID(){return vendorID;}
    
    
}
