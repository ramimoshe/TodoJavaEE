<%@ page import="java.util.logging.Logger" %>
<%@ page errorPage="main_exception_page.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/20/2015
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<% Logger logger = Logger.getLogger(this.getClass().getName()); %>

<form action=testjps.jsp method=get>
    <br>width: <input type=text name=width>
    <br>height: <input type=text name=height>
    <br><input type=submit>
</form>


<%
    String widthStr = request.getParameter("width");
    String heightStr = request.getParameter("height");
    if (widthStr != null && heightStr != null) {
        widthStr = widthStr.trim();
        heightStr = heightStr.trim();
        double width = Double.parseDouble(widthStr);
        double height = Double.parseDouble(heightStr);
        double area = width * height;
        double perimeter = (width + height) * 2;
        out.println("<br>width=" + width);
        out.println("<br>height=" + height);
        out.println("<br>area=" + area);
        out.println("<br>perimeter=" + perimeter);
    }
%>

</body>
</html>
