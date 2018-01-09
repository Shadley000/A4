/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.utils.ISelectable;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author shadl
 */
public class AlarmTypeBean implements Serializable, Comparable, ColumnHeaders, ISelectable
{

    private String system;
    private String subSystem;
    private String tagName;
    private String messageType;
    private String priority;
    private String description;
    private int flagIDPriority;
    private int flagIDMaintenance;
    private int flagIDAttention;
    private int flagIDSelected;
    private int flagIDCustom1;
    private int flagIDCustom2;
    private int flagIDCustom3;
    private String detail1;
    private String customDetail;

    public String getSelectableKey()
    {
        return getTagName();
    }

    public String getSelectableLabel()
    {
        return getSystem() + ":" + getSubSystem() + ":" + getDescription();
    }

    public AlarmTypeBean() throws SQLException
    {
        system = "";
        subSystem = "";
        tagName = "";
        messageType = "";
        priority = "";
        description = "";
        flagIDPriority = 0;
        flagIDMaintenance = 0;
        flagIDAttention = 0;
        flagIDSelected = 0;
        flagIDCustom1 = 0;
        flagIDCustom2 = 0;
        flagIDCustom3 = 0;
        detail1 = "";
        customDetail = "";
    }

    public AlarmTypeBean(ResultSet rs) throws SQLException
    {
        loadData(rs);
    }

    public void loadData(ResultSet rs) throws SQLException
    {
        system = rs.getString(CH_SYSTEM);
        subSystem = rs.getString(CH_SUBSYSTEM);
        tagName = rs.getString(CH_TAGNAME);
        messageType = rs.getString(CH_MSGTYPE);
        priority = rs.getString(CH_PRIORITY);
        description = rs.getString(CH_DESCRIPTION);
        flagIDPriority = rs.getInt(CH_FLAG_ID_PRIORITY);
        flagIDMaintenance = rs.getInt(CH_FLAG_ID_MAINTENANCE);
        flagIDAttention = rs.getInt(CH_FLAG_ID_ATTENTION);
        flagIDSelected = rs.getInt(CH_FLAG_ID_SELECTED);
        flagIDCustom1 = rs.getInt(CH_FLAG_ID_CUSTOM1);
        flagIDCustom2 = rs.getInt(CH_FLAG_ID_CUSTOM2);
        flagIDCustom3 = rs.getInt(CH_FLAG_ID_CUSTOM3);
        detail1 = rs.getString(CH_DETAIL1);
        customDetail = rs.getString(CH_CUSTOMDETAIL);

    }

    public void setSystem(String val)
    {
        this.system = val;
    }

    public void setSubSystem(String val)
    {
        this.subSystem = val;
    }

    public void setTagName(String val)
    {
        this.tagName = val;
    }

    public void setMessageType(String val)
    {
        this.messageType = val;
    }

    public void setPriority(String val)
    {
        this.priority = val;
    }

    public void setDescription(String val)
    {
        this.description = val;
    }

    public int getFlagIDPriority()
    {

        return flagIDPriority;
    }

    public int getFlagIDMaintenance()
    {
        return flagIDMaintenance;
    }

    public int getflagIDAttention()
    {
        return flagIDAttention;
    }

    public int getFlagIDSelected()
    {
        return flagIDSelected;
    }

    public int getFlagIDCustom1()
    {
        return flagIDCustom1;
    }

    public int getFlagIDCustom2()
    {
        return flagIDCustom2;
    }

    public int getFlagIDCustom3()
    {
        return flagIDCustom3;
    }

    public String getDetail()
    {
        return detail1;
    }

    public String getCustomDetail()
    {
        return customDetail;
    }

    public String getSystem()
    {
        return system;
    }

    public String getSubSystem()
    {
        return subSystem;
    }

    public String getTagName()
    {
        return tagName;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public String getPriority()
    {
        return priority;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof AlarmTypeBean)
        {
            AlarmTypeBean other = (AlarmTypeBean) o;
            return (tagName.equals(other.tagName));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.tagName);
        return hash;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof AlarmTypeBean)
        {
            AlarmTypeBean other = (AlarmTypeBean) o;

            if (system.equals(other.system))
            {
                if (subSystem.equals(other.subSystem))
                {
                    if (messageType.equals(other.messageType))
                    {
                        return description.compareTo(other.description);
                    } else
                    {
                        return messageType.compareTo(other.messageType);
                    }
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
