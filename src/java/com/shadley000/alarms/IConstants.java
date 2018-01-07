package com.shadley000.alarms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface IConstants
{

    public static final String PARAM_USERBEAN = "USERBEAN";
    public static final String PARAM_REPORTBEAN = "REPORTBEAN";

    public static final String PARAM_CLIENT_ID = "CLIENT_ID";
    public static final String PARAM_USER_NAME = "USER_NAME";
    public static final String PARAM_USER_ID = "USER_ID";
    public static final String PARAM_PASSWORD = "PASSWORD";
    public static final String PARAM_INSTALLATION_ID = "INSTALLATION_ID";
    public static final String PARAM_VENDOR_ID = "VENDOR_ID";

    public static final String PARAM_REPORT_NAME = "REPORT_NAME";
    public static final String PARAM_START = "START";
    public static final String PARAM_END = "END";

    public static final String PARAM_START_DATE = "START_DATE";
    public static final String PARAM_END_DATE = "END_DATE";
    public static final String PARAM_START_TIME = "START_TIME";
    public static final String PARAM_END_TIME = "END_TIME";
public static final String PARAM_TIMEBIN = "TIMEBIN";
    
    public static final String PARAM_SYSTEM = "SYSTEM";
    public static final String PARAM_SUBSYSTEM = "SUBSYSTEM";
    public static final String PARAM_MESSAGE_TYPE = "MESSAGE_TYPE";
    public static final String PARAM_STATUS = "STATUS";
    public static final String PARAM_PRIORITY_FLAG_FILTER = "PRIORITY_FLAG_FILTER";
    public static final String PARAM_MAINTENANCE_FLAG_FILTER = "MAINTENANCE_FLAG_FILTER";

    public static final String PARAM_CLIENTBEAN_LIST = "CLIENTBEAN_LIST";

    public static final String PARAM_FILE_ID = "FILE_ID";
    public static final String PARAM_MODE = "MODE";
    public static final String PARAM_NOTE_MODE = "NOTE_MODE";
    public static final String PARAM_TAGNAME = "TAGNAME";
    public static final String PARAM_FLAG_ID = "FLAG_ID";
    public static final String PARAM_INPUT1 = "INPUT1";
    public static final String PARAM_NOTE_ID = "NOTE_ID";
    public static final String PARAM_NOTE_TEXT = "NOTE_TEXT";

    public static final String MODE_DELETE = "DELETE";
    public static final String MODE_ADD = "ADD";
    public static final String MODE_UPDATE = "UPDATE";
    public static final String MODE_FLAG = "FLAG";
    public static final String MODE_ACKNOWLEDGE = "ACKNOWLEDGE";

    public static final Integer PERMISSION_VIEW = 0;
    public static final Integer PERMISSION_COMMENT = 100;
    public static final Integer PERMISSION_DELETE_COMMENT = 101;
    public static final Integer PERMISSION_UPLOAD = 200;
    public static final Integer PERMISSION_DELETE_FILE = 201;
    public static final Integer PERMISSION_ADMIN = 300;

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;
    public static final long ONE_WEEK = ONE_DAY * 7;
    public static final long ONE_MONTH = ONE_DAY * 31;
    public static final long ONE_YEAR = ONE_DAY * 365;

    public static final String servlet_url = "";

    public final static DateFormat timeFormatlong = new SimpleDateFormat("HH:mm:ss.SSS");

    public final static DateFormat htmlDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public final static DateFormat htmlTimeFormat = new SimpleDateFormat("HH:mm:ss");

    public final static DateFormat htmlDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

}
