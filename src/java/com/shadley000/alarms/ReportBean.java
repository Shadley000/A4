/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shadl
 */
public class ReportBean implements Serializable, IConstants
{

    public static final String DO_NAME = "NAME";
    public static final String DO_EXCEPTION_TRACE = "EXCEPTION_TRACE";

    public static final String DO_ALARM_TYPE_HISTOGRAM = "ALARM_TYPE_HISTOGRAM";
    public static final String DO_RATE_HISTOGRAM = "RATE_HISTOGRAM";
    public static final String DO_SOURCE_HISTOGRAM = "SOURCE_HISTOGRAM";
    public static final String DO_PRIORITY_RATE_HISTOGRAM = "PRIORITY_RATE_HISTOGRAM";
    public static final String DO_MULTI_HISTORY_LIST = "MULTI_HISTORY_LIST";
    public static final String DO_ALARM_RECORD_LIST = "ALARM_RECORD_LIST";
    public static final String DO_ALARM_TYPE_LIST = "ALARM_TYPE_LIST";
    public static final String DO_FILE_ERRORBEAN_LIST = "FILE_ERRORBEAN_LIST";
    public static final String DO_FILEBEAN_LIST = "FILEBEAN_LIST";
    public static final String DO_NOTES_LIST = "NOTES_LIST";
    public static final String DO_FILEBEAN = "FILEBEAN";

    private String jspurl;
    private String title = "";
    private Map<String, Object> dataMap = new HashMap<>();

    public ReportBean(String jspurl, String title)
    {
        this.jspurl = jspurl;
        this.title = title;
    }

    public Object getDataObject(String name)
    {
        return dataMap.get(name);
    }

    public void setDataObject(String name, Object obj)
    {
        dataMap.put(name, obj);
    }

    public String getTitle()
    {
        return title;
    }

    public String getJSPURL()
    {
        return jspurl;
    }

}
