package com.shadley000.histogram;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Histogram implements Serializable
{

    HashMap<Object, Integer> binMap = new HashMap<>();

    public Histogram()
    {

    }

    public SortedSet<Object> getRows()
    {

        SortedSet<Object> orderedRows = new TreeSet<Object>();
        orderedRows.addAll(binMap.keySet());
        return orderedRows;
    }

    public void addBin(Object name)
    {
        binMap.put(name, 0);
    }

    public void increment(Object name, int count)
    {
        Integer bin = binMap.get(name);
        if (bin == null)
        {
            bin = 0;
            
        }
        bin += count;
        binMap.put(name, bin);
    }

    public int getCount(Object name)
    {
        Integer bin = binMap.get(name);
        if (bin == null)
        {
            return 0;
        }
        return bin;
    }

    public int getTotalCount()
    {
        int sum = 0;
        for (Iterator<Object> it = binMap.keySet().iterator(); it.hasNext();)
        {
            Object key = it.next();
            sum += getCount(key);
        }
        return sum;
    }

    public void compressBins(String binLabel, int maxBins)
    {
        int otherSum = 0;
        while (binMap.size() > maxBins - 1)
        {
            Object smallestKey = null;
            for (Iterator<Object> it = binMap.keySet().iterator(); it.hasNext();)
            {
                Object key = it.next();
                int value = getCount(key);
                if (smallestKey == null)
                {
                    smallestKey = key;
                } else if (value < getCount(smallestKey))
                {
                    smallestKey = key;
                }

            }
            int value = getCount(smallestKey);
            binMap.remove(smallestKey);
            otherSum += value;

        }
        increment(binLabel, otherSum);
    }

}
