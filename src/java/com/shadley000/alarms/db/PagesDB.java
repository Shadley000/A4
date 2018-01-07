/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.db;

import com.shadley000.alarms.BasicContext;
import com.shadley000.alarms.IConstants;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.beans.PageBean;
import com.shadley000.alarms.beans.PermissionsBean;
import com.shadley000.alarms.pages.*;
import com.shadley000.alarms.pages.admin.PageAdmin;
import com.shadley000.alarms.pages.km.PageCommunicationAlarms;
import com.shadley000.alarms.pages.km.PageFNGTesting;
import com.shadley000.alarms.pages.km.PageOtherAlarms;
import com.shadley000.alarms.pages.km.PageTimeOut;
import com.shadley000.alarms.pages.nov.PageAntiCollision;
import com.shadley000.alarms.pages.nov.PageDeviation;
import com.shadley000.alarms.pages.nov.PageDrawworksLimitChecks;
import com.shadley000.alarms.pages.nov.PageInterlockOverride;
import com.shadley000.utils.SelectableFilter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author shadl
 */
public class PagesDB
{

    public static ArrayList<PageBean> loadPageList(Connection connection, BasicContext context, LabelsBean labelsBean, Map<Integer, PermissionsBean> permissionsMap)
    {
        ArrayList<PageBean> pageList = new ArrayList<>();

        if (context == null || permissionsMap == null)
        {

        } else
        {
            pageList.add(new PageBean(new PageOverview().getName(labelsBean), PageOverview.class));
            
            //pageList.add(new PageBean(new PageTest().getName(labelsBean), PageTest.class));

            pageList.add(new PageBean(new PageAlarmsBySystem().getName(labelsBean), PageAlarmsBySystem.class));
            pageList.add(new PageBean(new PageAlarmsVolumeByPriority().getName(labelsBean), PageAlarmsVolumeByPriority.class));
            pageList.add(new PageBean(new PageAlarmRateDetail().getName(labelsBean), PageAlarmRateDetail.class));
            pageList.add(new PageBean(new PageUniqueAlarms().getName(labelsBean), PageUniqueAlarms.class));

            pageList.add(new PageBean(new PageTagSearch().getName(labelsBean), PageTagSearch.class));
            pageList.add(new PageBean(new PageHistory().getName(labelsBean), PageHistory.class));
            pageList.add(new PageBean(new PageAlarmDetail().getName(labelsBean), PageAlarmDetail.class));

            if (0 == context.getVendorID())
            {
                pageList.add(new PageBean(SelectableFilter.DISABLE_SELECT_KEY, null));
                pageList.add(new PageBean(new PageAntiCollision().getName(labelsBean), PageAntiCollision.class));
                pageList.add(new PageBean(new PageDrawworksLimitChecks().getName(labelsBean), PageDrawworksLimitChecks.class));
                pageList.add(new PageBean(new PageInterlockOverride().getName(labelsBean), PageInterlockOverride.class));
                 pageList.add(new PageBean(new PageDeviation().getName(labelsBean), PageDeviation.class));
            } else if (1 == context.getVendorID())
            {
                pageList.add(new PageBean(SelectableFilter.DISABLE_SELECT_KEY, null));
               pageList.add(new PageBean(new PageTimeOut().getName(labelsBean), PageTimeOut.class));
               pageList.add(new PageBean(new PageCommunicationAlarms().getName(labelsBean), PageCommunicationAlarms.class));
                pageList.add(new PageBean(new PageOtherAlarms().getName(labelsBean), PageOtherAlarms.class));
               pageList.add(new PageBean(new PageFNGTesting().getName(labelsBean), PageFNGTesting.class));

            }
            if (permissionsMap.containsKey(IConstants.PERMISSION_UPLOAD))
            {
                pageList.add(new PageBean(SelectableFilter.DISABLE_SELECT_KEY, null));
                pageList.add(new PageBean(new PageErrorLog().getName(labelsBean), PageErrorLog.class, true));
                pageList.add(new PageBean(new PageFileList().getName(labelsBean), PageFileList.class));
            }
            if (permissionsMap.containsKey(IConstants.PERMISSION_ADMIN))
            {
                pageList.add(new PageBean(SelectableFilter.DISABLE_SELECT_KEY, null));
                pageList.add(new PageBean(new PageAdmin().getName(labelsBean), PageAdmin.class));
            }

            pageList.add(new PageBean(SelectableFilter.DISABLE_SELECT_KEY, null));
        }
        pageList.add(new PageBean(new PageLogout().getName(labelsBean), PageLogout.class));

        return pageList;
    }
}
