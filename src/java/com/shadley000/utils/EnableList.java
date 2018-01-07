/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadley000.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author shadl
 */
public class EnableList
{

    private Map<String, Boolean> myMap = new HashMap<>();

    public SortedSet<String> getSystems()
    {

        SortedSet<String> orderedRows = new TreeSet<String>();
        orderedRows.addAll(myMap.keySet());
        return orderedRows;
    }

    public boolean isEnabled(String system)
    {
        Boolean val = myMap.get(system);
        return (val != null && val);
    }

    public void setEnabled(String system, boolean enabled)
    {
        myMap.put(system, enabled);
    }
    
    public void setAll(boolean b)
    {
        for(Iterator<String> it = myMap.keySet().iterator();it.hasNext();)
        {
            String key = it.next();
            myMap.put(key,b);
        }
    }
}
