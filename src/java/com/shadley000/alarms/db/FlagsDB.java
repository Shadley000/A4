/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.beans.ClientBean;
import com.shadley000.alarms.beans.FlagBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shadl
 */
public class FlagsDB
{
  
    
     public static List<FlagBean> getFlagsBeans(Connection connection) throws SQLException
    {
         String sql = "select flag_id, description from FLAGS   order by flag_id;";
        
        List<FlagBean> dataList = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            dataList.add(new FlagBean(rs));
        }
        rs.close();
        stmt.close();

        return dataList;
    }
}
