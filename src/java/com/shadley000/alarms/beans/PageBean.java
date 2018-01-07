/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.alarms.beans;

import com.shadley000.utils.ISelectable;
import java.io.Serializable;

/**
 *
 * @author shadl
 */
public class PageBean implements Serializable, ColumnHeaders, ISelectable
{

    String pageName = "";
    Class pageClass = null;
    boolean isInvisible = false;

    public PageBean()
    {

    }

    public PageBean(String pageName, Class pageClass)
    {
        this.pageName = pageName;
        this.pageClass = pageClass;
    }

    public PageBean(String pageName, Class pageClass, boolean isInvisible)
    {
        this.pageName = pageName;
        this.pageClass = pageClass;
        this.isInvisible = isInvisible;
    }

    public String getPageName()
    {
        return pageName;
    }

    public Class getPageClass()
    {
        return pageClass;
    }

    public boolean isInvisible()
    {
        return isInvisible;
    }

    public String getSelectableKey()
    {
        if (pageClass != null)
        {
            return getPageClass().getName();
        } else
        {
            return null;
        }
    }

    public String getSelectableLabel()
    {
        return getPageName();
    }
}
