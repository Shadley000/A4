/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.IConstants;
import static com.shadley000.alarms.IConstants.PARAM_USERBEAN;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.NoteBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shadl
 */
public class NotesDB
{

    public static String sql_flagList = "select FLAG_ID, DESCRIPTION from flags order by f.FLAG_ID;";

    public static List<NoteBean> getNotesByTagName(Connection connection, UserSessionBean userBean, String tagName) throws SQLException
    {

        String sql = "select  distinct n.CLIENT_ID, n.INSTALLATION_ID, n.VENDOR_ID, "
                + " n.USER_ID, n.FLAG_ID,n.TAGNAME, n.notes_id,"
                + " a.DESCRIPTION, n.CREATED,n.USERCOMMENT, u.login "
                + " from NOTES n, USERS u, ALARM_DATA a "
                + " where "
                + " n.client_id = u.client_id "
                + " and n.client_id = a.client_id "
                + " and n.client_id = ? "
                + " and n.user_ID = u.user_id "
                + " and n.INSTALLATION_ID = ? "
                + " and n.VENDOR_ID = ? "
                + " and n.TAGNAME = ? "
                + " and n.INSTALLATION_ID = a.INSTALLATION_ID "
                + " and n.VENDOR_ID = a.VENDOR_ID "
                + " and n.tagname = a.tagname "
                + " order by n.CREATED DESC; ";

        List<NoteBean> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            stmt.setString(4, tagName);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    NoteBean noteBean = new NoteBean(rs);
                    list.add(noteBean);
                }
            }
        }

        return list;
    }

    public static List<NoteBean> getNotesByVendor(Connection connection, UserSessionBean userBean) throws SQLException
    {
        String sql = "select  distinct   n.CLIENT_ID, n.INSTALLATION_ID, n.VENDOR_ID, "
                + " n.USER_ID, n.FLAG_ID,n.TAGNAME, n.notes_id,"
                + " a.DESCRIPTION, n.CREATED,n.USERCOMMENT, u.login "
                + " from NOTES n, USERS u, ALARM_DATA a "
                + " where "
                + " n.client_id = u.client_id "
                + " and n.client_id = a.client_id "
                + " and n.client_id = ? "
                + " and n.user_ID = u.user_id "
                + " and n.INSTALLATION_ID = ? "
                + " and n.VENDOR_ID = ? "
                + " and n.INSTALLATION_ID = a.INSTALLATION_ID "
                + " and n.VENDOR_ID = a.VENDOR_ID "
                + " and n.tagname = a.tagname "
                + " order by n.CREATED DESC; ";

        List<NoteBean> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    NoteBean noteBean = new NoteBean(rs);
                    list.add(noteBean);
                }
            }
        }

        return list;
    }

    public static void insertNote(Connection connection, UserSessionBean userBean, String tagName, int flagID, String note) throws SQLException
    {

        if (note.length() > 512)
        {
            note = note.substring(0, 511);
        }
        String sql = " insert into NOTES (CLIENT_ID, INSTALLATION_ID, VENDOR_ID, USER_ID,FLAG_ID,"
                + "TAGNAME, CREATED, USERCOMMENT)"
                + " values (?,?,?,?,?,?,now(),?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            stmt.setInt(4, userBean.getUserBean().getUserID());
            stmt.setInt(5, flagID);
            stmt.setString(6, tagName);
            stmt.setString(7, note);
            stmt.execute();
        }
    }

    public static void deleteNote(Connection connection, UserSessionBean userBean, int noteID) throws SQLException
    {
        String sql = "delete from NOTES where client_ID = ? AND INSTALLATION_ID = ? AND VENDOR_ID = ? and notes_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            stmt.setInt(4, noteID);
            stmt.execute();
        }
    }

    public static void updateNote(Connection connection, UserSessionBean userBean, int noteID, int flagID, String note) throws SQLException
    {
        String sql = "UPDATE NOTES SET user_ID =?, FLAG_ID=?, USERCOMMENT = ? "
                + "where client_id = ?  "
                + "and INSTALLATION_ID = ?  "
                + "and VENDOR_ID = ? "
                + "and note_ID = note_id  ";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, userBean.getUserBean().getUserID());
            stmt.setInt(2, flagID);
            stmt.setString(3, note);
            stmt.setInt(4, userBean.getClientBean().getClientID());
            stmt.setString(2, userBean.getInstallationFilter().getSelectedKey());
            stmt.setString(3, userBean.getVendorFilter().getSelectedKey());
            stmt.setInt(7, noteID);
            stmt.execute();
        }
    }
}
