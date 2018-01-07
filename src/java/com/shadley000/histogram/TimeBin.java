package com.shadley000.histogram;

import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.UserSessionBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.utils.NameValuePair;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeBin
{
    public final static String MONTH = "Month";
    public final static String WEEK = "Week";
    public final static String DAY = "Day";
    public final static String HOUR6 = "6 Hours";
    public final static String HOUR = "Hour";
    public final static String MIN10 = "10 Minute";
   
    public final static DateFormat monthFormat = new SimpleDateFormat("MM");
    public final static DateFormat weekFormat = new SimpleDateFormat("ww");
    public final static DateFormat dayFormat = new SimpleDateFormat("MM/dd");
    public final static DateFormat hourFormat = new SimpleDateFormat("MM/dd HH:00");
    public final static DateFormat minuteFormat = new SimpleDateFormat("mm");

    public static List<NameValuePair> getBinList(LabelsBean labelsBean)
    {
        List<NameValuePair> binList = new ArrayList<NameValuePair>();

        binList.add(new NameValuePair(TimeBin.DAY,"1 " + labelsBean.getDays()));
        binList.add(new NameValuePair(TimeBin.MONTH,labelsBean.getMonthly() ));
        binList.add(new NameValuePair(TimeBin.WEEK,labelsBean.getWeekly() ));
        binList.add(new NameValuePair(TimeBin.HOUR,"1 " + labelsBean.getHours() ));
        binList.add(new NameValuePair( TimeBin.MIN10,"10 " + labelsBean.getMinutes()));
        return binList;
    }

    static public String autoTimeBin(UserSessionBean userBean)
    {

        long deltaTime = userBean.getEnd().getTime() - userBean.getStart().getTime();

        if (deltaTime > 3 * IConstants.ONE_MONTH)
        {
            return TimeBin.MONTH;
        }

        if (deltaTime > 2 * IConstants.ONE_DAY)
        {
            return TimeBin.DAY;
        }

        if (deltaTime > 2 * IConstants.ONE_HOUR)
        {
            return TimeBin.HOUR;
        }

        return TimeBin.MIN10;
    }

    public static final String findBin(Date date, String mode)
    {

        String text = null;

        if (MONTH.equals(mode))
        {
            text = monthFormat.format(date);

        } else if (WEEK.equals(mode))
        {
            text = weekFormat.format(date);

        } else if (DAY.equals(mode))
        {
            text = dayFormat.format(date);

        } else
        {
            text = hourFormat.format(date);
            String suffix = null;

            int minutes = Integer.parseInt(minuteFormat.format(date));

            if (HOUR.equals(mode))
            {
                suffix = "00";
            } else if (MIN10.equals(mode))
            {
                suffix = "" + (minutes / 10) + "0";
            } 
            
            text += ":" + suffix;
        }
        return text;
    }
}
