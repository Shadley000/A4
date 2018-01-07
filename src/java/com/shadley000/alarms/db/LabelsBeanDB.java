
package com.shadley000.alarms.db;

import com.shadley000.alarms.BasicContext;
import com.shadley000.alarms.beans.ClientBean;
import com.shadley000.alarms.beans.InstallationBean;
import com.shadley000.alarms.beans.LabelsBean;
import com.shadley000.alarms.beans.UserBean;
import com.shadley000.alarms.beans.VendorBean;
import java.sql.Connection;

public class LabelsBeanDB
{
    
    
    public static LabelsBean getLabelsBean(Connection connection,BasicContext context)
    {
        return new LabelsBean();
    }
}
