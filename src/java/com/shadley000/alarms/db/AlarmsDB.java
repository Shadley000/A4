/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.AlarmClassification;
import com.shadley000.alarms.beans.AlarmRecordBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.histogram.Histogram2D;
import com.shadley000.histogram.TimeBin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shadl
 */
public class AlarmsDB
{
    static public List<AlarmTypeBean> tagSearch(Connection connection, UserSessionBean userBean, String searchString) throws SQLException
    {
        String sql = "SELECT DISTINCT  SYSTEM, SUBSYSTEM, "
                + "TAGNAME, MSGTYPE, PRIORITY, DESCRIPTION,  FLAG_ID_PRIORITY, FLAG_ID_MAINTENANCE, FLAG_ID_ATTENTION, "
                + "FLAG_ID_SELECTED, FLAG_ID_CUSTOM1, FLAG_ID_CUSTOM2, FLAG_ID_CUSTOM3, DETAIL1, CUSTOMDETAIL "
                + " from ALARM_TYPE "
                + "WHERE  "
                + "CLIENT_ID = ?  "
                + "AND INSTALLATION_ID = ?  "
                + "AND VENDOR_ID = ? "
                + "AND DESCRIPTION LIKE ? "
                + "AND FLAG_ID_PRIORITY >= ? "
                + "AND FLAG_ID_MAINTENANCE >= ? ";

        List<AlarmTypeBean> dataList = new ArrayList<>();

        if (searchString != null && searchString.length() > 0)
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            stmt.setString(4, searchString);
            stmt.setString(5, userBean.getPriorityFilter().getSelectedKey());
            stmt.setString(6, userBean.getMaintFilter().getSelectedKey());
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next())
            {
                AlarmTypeBean type = new AlarmTypeBean(rs);

                dataList.add(type);
                rowCount++;
                if (rowCount > 500)
                {
                    break;
                }
            }
            rs.close();
            stmt.close();
        }
        return dataList;
    }
    
    public final static String sql_alarms = "SELECT  a.FILE_ID, a.ALM_TIME, a.ALM_ID, a.SOURCE_NODE,  a.SYSTEM,  a.SUBSYSTEM,  "
                + " a.TAGNAME,  a.MSGTYPE, a.PRIORITY,a.ALM_STATUS,a.DESCRIPTION, "
                + " t.FLAG_ID_PRIORITY,t.FLAG_ID_MAINTENANCE,t.FLAG_ID_ATTENTION,t.FLAG_ID_SELECTED,"
                + " t.FLAG_ID_CUSTOM1,t.FLAG_ID_CUSTOM2,t.FLAG_ID_CUSTOM3,t.DETAIL1,t.CUSTOMDETAIL "
                + " FROM ALARM_DATA a, ALARM_TYPE t "
                + " WHERE "
                + " a.CLIENT_ID = ? "
                + " AND a.INSTALLATION_ID = ?  "
                + " AND a.VENDOR_ID = ? "
                + " AND a.CLIENT_ID = t.CLIENT_ID"
                + " AND a.INSTALLATION_ID = t.INSTALLATION_ID  "
                + " AND a.VENDOR_ID = t.VENDOR_ID"
                + " AND a.TAGNAME = t.TAGNAME"
                + " AND a.ALM_TIME >=?  "
                + " AND  a.ALM_TIME <=? ";

    static public PreparedStatement getSelectAlarms(Connection connection,UserSessionBean userBean, boolean timeOrdered, String specailCondition) throws SQLException
    {
       String sql  = sql_alarms;
        
        String priorityStr = userBean.getPriorityFilter().getSelectedKey();
        String maintStr = userBean.getMaintFilter().getSelectedKey();
        String systemStr = userBean.getSystemFilter().getSelectedKey();
        String subSystemStr = userBean.getSubSystemFilter().getSelectedKey();
        String messageTypeStr = userBean.getMessageTypeFilter().getSelectedKey();
        String tagNameStr = userBean.getAlarmTypeFilter().getSelectedKey();
        String statusStr = userBean.getStatusFilter().getSelectedKey();

        if (priorityStr != null)
        {
            sql += " AND t.FLAG_ID_PRIORITY >= " + priorityStr + " ";
        }
        if (maintStr != null)
        {
            sql += " AND t.FLAG_ID_MAINTENANCE >= " + maintStr + " ";
        }
        if (systemStr != null && systemStr.length()>0)
        {
            sql += " AND a.SYSTEM = '" + systemStr + "'";
        }
        if (subSystemStr != null && subSystemStr.length()>0)
        {
            sql += " AND a.SUBSYSTEM = '" + subSystemStr + "' ";
        }
        if (messageTypeStr != null && messageTypeStr.length()>0)
        {
            sql += " AND  a.MSGTYPE = '" + messageTypeStr + "' ";
        }
        if (tagNameStr != null && tagNameStr.length()>0)
        {
            sql += " AND  a.TAGNAME = '" + tagNameStr + "' ";
        }
        if (statusStr != null && statusStr.length()>0)
        {
            sql += " AND  a.ALM_STATUS = '" + statusStr + "' ";
        }
        if(specailCondition!=null)   sql += specailCondition;
        if(timeOrdered)   sql += " order by a.ALM_TIME ";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setDate(4, new java.sql.Date(userBean.getStart().getTime()));
        stmt.setDate(5, new java.sql.Date(userBean.getEnd().getTime()));
        return stmt;
    }
    
    
    static public List<AlarmRecordBean> getSelectOrderedAlarms(Connection connection, UserSessionBean userBean) throws SQLException
    {
        List<AlarmRecordBean> dataList = new ArrayList<>();

        PreparedStatement stmt = getSelectAlarms( connection, userBean, true,null);
        ResultSet rs = stmt.executeQuery();

        int rowCount = 0;
        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            dataList.add(record);
            rowCount++;
            if (rowCount > 5000)
            {
                break;
            }
        }
        rs.close();
        stmt.close();
        return dataList;

    }

    static public Histogram2D getAlarmsVolumeBySystem(Connection connection, UserSessionBean userBean, String mode) throws SQLException
    {
        Histogram2D sourceHistogram = new Histogram2D();
        PreparedStatement stmt = AlarmsDB.getSelectAlarms(connection,userBean,false,null);    
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmClassification classification = new AlarmClassification(rs);
            
            if(IConstants.PARAM_SYSTEM.equals(mode))
            {
                classification.setSubSystem("");
                classification.setMessageType("");
                
            }
            else if (IConstants.PARAM_SUBSYSTEM.equals(mode))
            {
                classification.setMessageType("");
            }
            else if (IConstants.PARAM_MESSAGE_TYPE.equals(mode))
            {
               
            }
            String columnName = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            sourceHistogram.increment(columnName, classification, 1);
        }
        rs.close();
        stmt.close();

        return sourceHistogram;
    }

    static public Histogram2D getAlarmsVolumeByPriority(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D priorityRateHistogram = new Histogram2D();

        PreparedStatement stmt = AlarmsDB.getSelectAlarms(connection,userBean,false,null);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            String columnName = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            priorityRateHistogram.increment(columnName, record.getPriority(), 1);
        }
        rs.close();
        stmt.close();
        return priorityRateHistogram;
    }

    static public Histogram2D getUniqueAlarms(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D histogram = new Histogram2D();

        PreparedStatement stmt = AlarmsDB.getSelectAlarms(connection,userBean,false,null);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            String timeBin = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            histogram.increment(timeBin, type, 1);
        }
        rs.close();
        stmt.close();

        return histogram;
    }

    static public Histogram2D getRateHistogram(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D histogram = new Histogram2D();
        DateFormat rowFormat = new SimpleDateFormat("HH:00");
        DateFormat colFormat = new SimpleDateFormat("MM/dd");

        PreparedStatement stmt = AlarmsDB.getSelectAlarms(connection,userBean,false,null);
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean bean = new AlarmTypeBean(rs);

            String row = rowFormat.format(record.getAlarmTime());
            String column = colFormat.format(record.getAlarmTime());
            histogram.increment(column, row, 1);
        }

        rs.close();
        stmt.close();
        return histogram;
    }

    static public PreparedStatement getDrawworksAlarms(Connection connection,UserSessionBean userBean) throws SQLException
    {
        return getSelectAlarms(connection, userBean,false, " AND (a.SYSTEM =  'DW1' OR a.SYSTEM =  'DW2') ");
    }

    static public PreparedStatement getACSAlarms(Connection connection,UserSessionBean userBean) throws SQLException
    {
        return getSelectAlarms(connection,userBean,false, " AND a.SYSTEM = 'ACS' ");
    }

    static public PreparedStatement getInterlockOverrideAlarms(Connection connection,UserSessionBean userBean) throws SQLException
    {
        return getSelectAlarms(connection,userBean,false, " AND a.DESCRIPTION LIKE '%verrid%'  ");
    }

     static public Histogram2D getFNGTestingAlarms(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D histogram = new Histogram2D();

        String sql  = sql_alarms;
        
        sql += " AND a.SUBSYSTEM = 'F&G' ";
        sql += " AND ( "
                + " a.MSGTYPE = 'Fire Alarm' "
                + " OR a.MSGTYPE = 'Smoke Alarm' "
                + " OR a.MSGTYPE = 'Smoke Prewarning' "
                + " OR a.MSGTYPE = 'Heat Alarm' "
                + " OR a.MSGTYPE = 'Heat Prewarning' "
                + " OR a.MSGTYPE = 'Gas Alarm H' "
                + " OR a.MSGTYPE = 'Gas Alarm HH' "
                + " OR a.MSGTYPE = 'H2-gas Alarm H' "
                + " OR a.MSGTYPE = 'H2-gas Alarm HH' "
                + " OR a.MSGTYPE = 'H2S-gas Alarm H' "
                + " OR a.MSGTYPE = 'H2S-gas Alarm HH' "
                + " ) ";
    
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setDate(4, new java.sql.Date(userBean.getStart().getTime()));
        stmt.setDate(5, new java.sql.Date(userBean.getEnd().getTime()));
      
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            String timeBin = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            histogram.increment(timeBin, type, 1);
        }
        rs.close();
        stmt.close();

        return histogram;
    }
    public static List<AlarmRecordBean> getAlarmListbyTag(Connection connection, UserSessionBean userBean, String tagName) throws SQLException
    {
        List<AlarmRecordBean> history = new ArrayList<>();

        PreparedStatement stmt =  AlarmsDB.getSelectAlarms(connection,userBean,true," AND a.TAGNAME = '"+tagName+"' ");
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            history.add(0, record);
        }
        rs.close();
        stmt.close();

        return history;
    }

    public static Histogram2D getTimeOutAlarms(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D histogram = new Histogram2D();

        String sql  = sql_alarms;
        
        sql += " AND ( "
                + " a.MSGTYPE = 'Feedback Inconsistency' "
                + " OR a.MSGTYPE = 'Open or Close Timeout' "
               + " OR a.MSGTYPE = 'Start or Stop Timeout' "
                + " ) "
                + " order by a.MSGTYPE ";
    
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setDate(4, new java.sql.Date(userBean.getStart().getTime()));
        stmt.setDate(5, new java.sql.Date(userBean.getEnd().getTime()));
      
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            String timeBin = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            histogram.increment(timeBin, type, 1);
        }
        rs.close();
        stmt.close();

        return histogram;
    }

    public static Histogram2D getDeviationAlarms(Connection connection, UserSessionBean userBean) throws SQLException
    {
        Histogram2D histogram = new Histogram2D();

        String sql  = sql_alarms;
        
        sql += " AND a.DESCRIPTION like '%eviation%' ";
    
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userBean.getClientBean().getClientID());
        stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
        stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
        stmt.setDate(4, new java.sql.Date(userBean.getStart().getTime()));
        stmt.setDate(5, new java.sql.Date(userBean.getEnd().getTime()));
      
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            AlarmRecordBean record = new AlarmRecordBean(rs);
            AlarmTypeBean type = new AlarmTypeBean(rs);
            String timeBin = TimeBin.findBin(record.getAlarmTime(),  userBean.getTimeBinFilter().getSelectedKey());
            histogram.increment(timeBin, type, 1);
        }
        rs.close();
        stmt.close();

        return histogram;
    }

}
