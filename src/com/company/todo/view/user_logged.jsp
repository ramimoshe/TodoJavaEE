<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@ page import="com.company.todo.model.UserEntity" %>
<%@ page import="com.company.todo.model.TaskEntity" %>
<%@ page import="java.util.Iterator" %>
<jsp:useBean id="currentSessionUser" scope="session" type="com.company.todo.model.UserEntity">
<jsp:setProperty name="currentSessionUser" property="*"></jsp:setProperty>
</jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=windows-1256">
    <title>User Logged Successfully</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
        }
    </style>

</head>

<body>

<% UserEntity currentUser = (UserEntity) (session.getAttribute("currentSessionUser"));%>

Welcome <%= currentUser.getUsername() %>

<h1>Your Todo's</h1>
<table style="width:100%">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Duedate</th>
    </tr>
    <%
        Iterator<TaskEntity> tasks = (Iterator<TaskEntity>) (session.getAttribute("notes"));
        while (tasks.hasNext()) {
            TaskEntity task = tasks.next();
            out.println("<tr>"+
                    "<td>" + task.getTitle() + "</td>\n" +
                            "<td>" + task.getContent() + "</td>\n" +
                            "<td>" + task.getDuedate() + "</td>"
                            + "<a>" +"</a>"
                    + "</tr>"
            );
        }
    %>
</table>


</body>

</html>