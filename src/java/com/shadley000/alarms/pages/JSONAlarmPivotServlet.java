/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.pages;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.AlarmTypeBean;
import com.shadley000.alarms.db.AlarmsDB;
import com.shadley000.histogram.Histogram;
import com.shadley000.histogram.Histogram2D;
import com.shadley000.utils.AlarmsSQLConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shadl
 */
public class JSONAlarmPivotServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        AlarmsSQLConnect mySQLConnect = new AlarmsSQLConnect();
        Connection connection = mySQLConnect.connect();
        HttpSession session = request.getSession();
        UserSessionBean userBean = (UserSessionBean) session.getAttribute(IConstants.PARAM_USERBEAN);
        Histogram2D hist2D;
        try
        {
            hist2D = AlarmsDB.getUniqueAlarms(connection, userBean);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new JSONObject(hist2D).toString());
        } catch (SQLException ex)
        {
            Logger.getLogger(JSONAlarmPivotServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"error\":\"" + ex.getMessage() + "\"}");
        }
        mySQLConnect.disconnect();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
