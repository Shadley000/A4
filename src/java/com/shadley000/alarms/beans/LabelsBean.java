/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import java.io.Serializable;

/**
 *
 * @author shadl
 */
public class LabelsBean implements Serializable, ColumnHeaders
{
    
    public LabelsBean()
    {
        
    }
    public String getUsername() {return "User Name";}
    public String getShipname () {return "Ship Name";}
    public  String getPassword() {return "Password";}
    public  String getLogin() {return "Login";}
    public  String getLogout() {return "Logout";}
    public  String getDate() {return "Date";}
    public  String getTime() {return "Time";}
    public  String getTag() {return "Tag";}
    public  String getGo() {return "Go";}
    public  String getDescription() {return  "Description";}
    public  String getReport() {return "Report";}
    public  String getStart() {return "Start";}
    public  String getEnd() {return "End";}
    public  String getAlarmFlagFilter() {return  "Alarm Priority Filter";}
    public  String getMaintFlagFilter() {return  "Maint Priority Filter";}
  
    public String getApplicationName() {return  "A4 Alarm Manager";}
    
    public String getUnique_alarms() {return  "Unique Alarms";}
    public String getUniqueSystemAlarms()  {return  "Unique Alarms for ";}
    public  String getAlarmDetailReport() {return " Alarm Detail";}
    public  String getAlarms_by_source() {return  "Alarms by System";}
     public  String getAlarms_by_Priority() {return  "Alarms by Priority";}
    public String getAlarm_rate() {return  "Alarm Rate";}
    public String getAlarmDistribution() {return  "Alarm Distribution";}
    public  String getHistory() {return "History";}
     public  String getHistogram() {return "Histogram";}

     public  String  getOverview() {return "Overview";}
    public  String getErrorLog() {return "Error Log";}
    public  String getDrawworks() {return "Drawworks";}
    public  String getLimit_checks () {return "Limit Checks";}
    public  String getAnticollision () {return "Anti Collision System";}
    public  String getInterlock_override() {return  "Interlock Override";}
    public  String getNotesReview() {return  "Notes Review";}
    public  String getNotes() {return  "Notes";}
    public String getBlank()   {     return "Blank";    }
    public String getGroupAnalysis()   {     return "Group Analysis";    }
    public String getTagSearch()    {        return "Tag Search";    }
    public String getAdmin()    {        return "Admin";    }
    public String getFNGalarms() {return "F&G Alarms";}
    
    public  String getRecords() {return  "Records";}
    public  String getMode () {return  "Mode";}
    public  String getMain() {return "Main";}
    public  String getAux() {return "Aux";}

   public String getTimeResolution() {return "Time Resolution";}
    public  String getSeconds () {return  "Second(s)";}
    public  String getMinutes() {return  "Minute(s)";}
    public  String getHours() {return  "Hours(s)";}
    public String getDays() {return "Day(s)";}
    public String getWeekly() {return "Weekly";}
    public String getMonthly() {return "Monthly";}
    public  String getSystem() {return "System";}
    public  String getSubsystem() {return "SubSystem";}
    public  String getPriority(){return "Priority";}
    public  String getAction() {return  "Action";}
    public  String getStatus() {return  "Status";}
    public  String getType() {return  "Type";}
    public  String getOther() {return  "Other";}
    public  String getTotal() {return  "Total";}
   public  String  getCount() {return  "Count";}
   public  String  getCreateNewNote() {return  "Create New Note";}
   public  String  getFlag(){return "Flag";}

    public  String getFileName() {return  "Filename";}
    public  String getLineNum () {return "Line #";}
    public  String getError() {return "Error";}
    public  String getText () {return "Text";}
    public  String getFileList() {return  "File List";}
    public  String getMaxRows() {return  "Maximum rows exceeded";}
    public  String getNoTagSelected() {return "No Tag Selected";}
    public  String  getAllTags() {return "All Tags Selected";}
    public  String getNoRows() {return  "No Rows Returned";}
    public  String getLines () {return  "Lines";}
    public  String getInserted () {return  "Inserted";}
    public  String getSkipped() {return  "Skipped";}
    public  String getErrors() {return  "Errors";}

   
    public String getHourly_alarm_volume () {return  "Hourly Alarm Volume";}

    public String getTimeOutAlarms() {return "Time Out Alarms";}
    public String getDeviationAlarms() {return "Deviation Alarms";}

   public String getCommunicationAlarms() {return "Communication Alarms";}
   public String getOtherAlarms() {return "Other Alarms";}
   
}
