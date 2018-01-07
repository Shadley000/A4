/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlarmRecordBean extends AlarmTypeBean
{

    private int fileID;
    private java.util.Date almTime;
    private String almID;
    private String sourceNode;
    private String status;

    public AlarmRecordBean(ResultSet rs) throws SQLException
    {
        super(rs);
        loadData(rs);
    }

    public void loadData(ResultSet rs) throws SQLException
    {
        super.loadData(rs);
        fileID = rs.getInt(CH_FILE_ID);
        almTime = rs.getTimestamp(CH_ALM_TIME);
        almID = rs.getString(CH_ALM_ID);
        sourceNode = rs.getString(CH_SOURCE_NODE);
        status = rs.getString(CH_ALM_STATUS);

    }

    public int getFileID()
    {
        return fileID;
    }

    public java.util.Date getAlarmTime()
    {
        return almTime;
    }

    public String getAlarmID()
    {
        return almID;
    }

    public String getSourceNode()
    {
        return sourceNode;
    }

    public String getStatus()
    {
        return status;
    }

}
