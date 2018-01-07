<%@page import="java.util.Enumeration"%>
<%
    {
         out.println("<!-- Request -->");
        for (Enumeration<String> values = request.getAttributeNames(); values.hasMoreElements();)
        {
            String name = values.nextElement();
            out.println("<!-- "+name+":"+request.getParameter(name)+" -->");
        }

        out.println("<!-- Session -->");
        for (Enumeration<String> values = request.getSession().getAttributeNames(); values.hasMoreElements();)
        {
            String name = values.nextElement();
            out.println("<!-- "+name+":"+request.getSession().getAttribute(name)+" -->");
        }
    }
%>
