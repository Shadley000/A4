package com.shadley000.histogram;

import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import java.io.Serializable;

public class Histogram2D implements Serializable
{

    HashMap<Object, Histogram> histMap = new HashMap<Object, Histogram>();
    SortedSet<Object> orderedColumns = new TreeSet<Object>();

    public Histogram2D()
    {

    }

    public void put(Object row, Histogram hist)
    {
        histMap.put(row, hist);
        orderedColumns.addAll(hist.getRows());
    }

    public void increment(Object column, Object row, int count)
    {
        Histogram hist = histMap.get(row);
        if (hist == null)
        {
            hist = new Histogram();
            histMap.put(row, hist);
        }
        hist.increment(column, count);
        orderedColumns.add(column);

    }

    public SortedSet<Object> getColumns()
    {
        return orderedColumns;
    }

    public SortedSet<Object> getRows()
    {

        SortedSet<Object> orderedRows = new TreeSet<Object>();
        orderedRows.addAll(histMap.keySet());
        return orderedRows;
    }

    public Histogram getHistogram(Object row)
    {
        return histMap.get(row);
    }

    public Histogram removeRow(Object row)
    {
        return histMap.remove(row);
    }

    public int getCount(Object row, Object column)
    {
        Histogram hist = getHistogram(row);
        if (hist == null)
        {
            return 0;
        }
        return hist.getCount(column);
    }

    public int getRowTotalCount(Object row)
    {
        Histogram hist = getHistogram(row);
        if (hist == null)
        {
            return 0;
        }
        return hist.getTotalCount();
    }

    public int getColumnTotalCount(Object column)
    {
        int sum = 0;
        for (Iterator<Object> it = histMap.keySet().iterator(); it.hasNext();)
        {
            Object key = it.next();

            Histogram hist = getHistogram(key);
            if (hist != null)
            {
                sum += hist.getCount(column);
            }
        }

        return sum;
    }


}
