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
import java.util.Objects;

/**
 *
 * @author shadl
 */
public class AlarmClassification implements  Serializable, Comparable, ColumnHeaders
{
    private String system;
    private String subSystem;
    private String messageType;
   
    
     public AlarmClassification(ResultSet rs) throws SQLException
    {
        loadData(rs);
    }

    public void loadData(ResultSet rs) throws SQLException
    {
        system = rs.getString(CH_SYSTEM);
        subSystem = rs.getString(CH_SUBSYSTEM);
       messageType = rs.getString(CH_MSGTYPE);
    }
    
    public String getSystem()
    {
        return system;
    }

    public String getSubSystem()
    {
        return subSystem;
    }

    public String getMessageType()
    {
        return messageType;
    }
 
    public void setSystem(String s)
    {
         system =s;
    }

    public void setSubSystem(String s)
    {
         subSystem=s;
    }

    public void setMessageType(String s)
    {
         messageType =s;
    }
 
    

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof AlarmClassification)
        {
            AlarmClassification other = (AlarmClassification) o;
            return (system.equals(other.system)&& subSystem.equals(other.subSystem)&&messageType.equals(other.messageType));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.system);
        hash = 59 * hash + Objects.hashCode(this.subSystem);
        hash = 59 * hash + Objects.hashCode(this.messageType);
        return hash;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof AlarmClassification)
        {
            AlarmClassification other = (AlarmClassification) o;

            if (system.equals(other.system))
            {
                if (subSystem.equals(other.subSystem))
                {
                    return messageType.compareTo(other.messageType);
                } else
                {
                    return subSystem.compareTo(other.subSystem);
                }
            } else
            {
                return system.compareTo(other.system);
            }

        } else
        {
            throw new UnsupportedOperationException();
        }

    }
}
