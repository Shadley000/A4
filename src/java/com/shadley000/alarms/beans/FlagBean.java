/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.shadley000.utils.ISelectable;

/**
 *
 * @author shadl
 */
public class FlagBean implements Serializable, ColumnHeaders, ISelectable
{

    int flagID;
    String description;

    public FlagBean()
    {
    }

    public FlagBean(ResultSet rs) throws SQLException
    {
        flagID = rs.getInt(CH_FLAG_ID);
        description = rs.getString(CH_DESCRIPTION);
    }

    public int getFlagID()
    {
        return flagID;
    }

    public String getDescription()
    {
        return description;
    }

    public String getSelectableKey()
    {
        return Integer.toString(getFlagID());
    }

    public String getSelectableLabel()
    {
        return getDescription();
    }
}
