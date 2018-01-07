package com.shadley000.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SelectableFilter
{

    private ISelectable current = null;
    private Map<String, ISelectable> map = new HashMap<>();
    private List<String> orderedKeys = new ArrayList();

   public static final String DISABLE_SELECT_KEY = "DISABLE";

    public SelectableFilter(List data)
    {
        if (data.size() > 0)
        {
            current = (ISelectable) data.get(0);
            
            if(DISABLE_SELECT_KEY.equals(current.getSelectableKey()))
            {
                current = null;
            }
            for (Object item : data)
            {
                ISelectable selectable = (ISelectable) item;
                if (selectable != null)
                {
                    orderedKeys.add(selectable.getSelectableKey());
                    map.put(selectable.getSelectableKey(), selectable);
                }
            }
        } else
        {
            data = new ArrayList<>();
            current = null;
        }
    }

    public String getSelectedKey()
    {
        if (current != null)
        {
            return current.getSelectableKey();
        } else
        {
            return null;
        }
    }

    public ISelectable getSelectedItem()
    {
        return current;

    }

    public boolean setSelectedItem(String key)
    {

        if (key == null)
        {
            return false;
        }

        ISelectable oldSelected = current;
        ISelectable item = map.get(key);

        if (DISABLE_SELECT_KEY.equals(key))
        {
            current = null;
        } else
        {
            current = item;
        }

        return ! ((current==null &&oldSelected==null) || current!=null && current.equals(oldSelected));

    }

    private String getItemLabel(String key)
    {
        ISelectable item = map.get(key);
        if (item != null)
        {
            return item.getSelectableLabel();
        }
        return "";
    }

    public String getSelectHTML(String tag, boolean autoSubmit)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("<select name=\"" + tag + "\" ");
        if (autoSubmit)
        {
            sb.append(" onchange=\"this.form.submit()\"");
        }
        sb.append(">\n");

        for (String key : orderedKeys)
        {
            sb.append("<option value=\"" + key + "\"  ");
            if (key == null || getItemLabel(key) == null)
            {
                sb.append(" disabled ");
            } else if (key.equals(getSelectedKey()))
            {
                sb.append("selected=\"TRUE\"");
            }
            sb.append(">" + getItemLabel(key) + "</option>\n");
        }
        sb.append("</SELECT>\n");
        return sb.toString();
    }

    public String getFlagRadioHTML(String tag, int max, boolean autoSubmit)
    {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (String key : orderedKeys)
        {
            sb.append("<input type=\"radio\" name=\"" + tag + "\" value=\"" + key + "\"");
            if (autoSubmit)
            {
                sb.append(" onchange=\"this.form.submit()\" ");
            }

            if (key != null && key.equals(getSelectedKey()))
            {
                sb.append(" checked ");
            }
            sb.append(" ><img src=\"images\\flags\\" + key + ".png\"  style=\"width:24px;height:24px;\"></input>");
            count++;
            if (count > max)
            {
                break;
            }
        }

        return sb.toString();
    }
}
