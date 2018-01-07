/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.utils;

import com.shadley000.alarms.beans.ColumnHeaders;
import com.shadley000.utils.ISelectable;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shadl
 */
public class SelectableBean  implements Serializable, ColumnHeaders, ISelectable
{
      String id;
    String name;
    
    
      @Override
    public String getSelectableKey()
    {
        return getStatus();
    }

      @Override
    public String getSelectableLabel()
    {
        return getName();
    }
    
     public SelectableBean(String id, String name) 
    {

       this.id = id;
        this.name = name;
    }
    
    public SelectableBean(ResultSet rs) throws SQLException
    {
        
        id = rs.getString("ID");
        name = rs.getString("NAME");
    }
    
    public String getStatus() {return id;}
    public String getName() {return name;}
}
