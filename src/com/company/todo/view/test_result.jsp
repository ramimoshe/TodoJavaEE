<%--
  Created by IntelliJ IDEA.
  User: rami
  Date: 28/04/2015
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <%
      String usersResult = ((Boolean) request.getAttribute("usersResult")) == true ? "Passed" : "Failed";
      String tasksResult = ((Boolean) request.getAttribute("tasksResult")) == true ? "Passed" : "Failed";
    %>

  <h1>Test Result</h1>

    <table style="width:200px" border="1">
      <tr>
        <td>Test Name</td>
        <td>Result</td>
      </tr>
      <tr>
        <td>Users Controller</td>
        <td><%=usersResult%></td>
      </tr>
      <tr>
        <td>Tasks Controller</td>
        <td><%=tasksResult%></td>
      </tr>
    </table>

  </body>
</html>
