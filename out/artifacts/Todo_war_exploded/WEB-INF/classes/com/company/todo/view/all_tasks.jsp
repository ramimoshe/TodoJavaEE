<%@ page import="com.company.todo.model.TaskEntity" %>
<%@ page import="java.util.Iterator" %>
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
    <script>

        function editTask(taskId, title, content, dueDate, createdDate, isDeleted) {
            document.getElementById("taskId").value = taskId;
            document.getElementById("title").value = title;
            document.getElementById("content").value = content;
            document.getElementById("dueDate").value = dueDate;
            document.getElementById("createdDate").value = createdDate;
            document.getElementById("isDeleted").value = isDeleted;
            document.getElementById("submit").value = "Save";
        }

        function sendTask(taskId, title, content, dueDate, createdDate, userId, isDeleted) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "/controller/tasks", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("&userId=" + userId + "&taskId=" + taskId + "&title=" + title + "&content=" + content + "&dueDate=" + dueDate + "&createdDate=" + createdDate + "&isDeleted=" + isDeleted);
        }

        function deleteTask(taskId, title, content, dueDate, createdDate, userId){
            sendTask(taskId, title, content, dueDate, createdDate, userId, true);
            console.log("delete");
            window.location.reload();
            //window.location = window.location.href;
        }

        function upsertTask(){
            sendTask(
                    document.getElementById("taskId").value,
                    document.getElementById("title").value,
                    document.getElementById("content").value,
                    document.getElementById("dueDate").value,
                    document.getElementById("createdDate").value,
                    document.getElementById("userId").value,
                    document.getElementById("isDeleted").value);
            console.log("test");
            window.location.reload();
            //window.location = window.location.href;
        }

    </script>

</head>
<body align="center">
<h1>Your Tasks</h1>


<%
    out.println("<table border=1 >" +
            "<col width=\"50\">" +
            "<col width=\"300\">" +
            "<col width=\"500\">" +
            "<col width=\"100\">" +
            "<col width=\"100\">" +
            "<col width=\"38\">" +
            "<col width=\"38\">");
    out.println(
            "<tr>" +
                    "<th>Id</th>" +
                    "<th>Title</th>" +
                    "<th>Content</th>" +
                    "<th>Created Date</th>" +
                    "<th>Due Date</th>" +
                    "</tr>");

    Iterator<TaskEntity> tasks = (Iterator<TaskEntity>) request.getAttribute("tasks");
    String userId = (String) request.getAttribute("userId");
    TaskEntity task;
    while (tasks.hasNext()) {
        task = tasks.next();
        out.println(
                "<tr>" +
                        "<td>" + task.getTaskId() + "</td>" +
                        "<td>" + task.getTitle() + "</td>" +
                        "<td>" + task.getContent() + "</td>" +
                        "<td>" + task.getDateCreated() + "</td>" +
                        "<td>" + task.getDuedate() + "</td>" +
                        "<td>" +
                            "<button onclick=\"editTask('" +
                            task.getTaskId() + "','" +
                            task.getTitle() + "','" +
                            task.getContent() + "','" +
                            task.getDuedate() + "','" +
                            task.getDateCreated() + "','" +
                            task.getIsDeleted() + "')\">Edit</button>" +
                        "</td>" +
                        "<td>" +
                            "<button onclick=\"deleteTask('" +
                            task.getTaskId() + "','" +
                            task.getTitle() + "','" +
                            task.getContent() + "','" +
                            task.getDuedate() + "','" +
                            task.getDateCreated() + "','" +
                            userId + "')\">Delete</button>" +
                        "</td>" +
                "</tr>");

    }
    out.println("</table>");

%>


</br></br></br>
<div align="left">
    <table style="width:20%">

        <form id="taskForm" onsubmit="upsertTask()">
            <tr>
                <td>Title</td>
                <td><input id="title" type="text" name="title"/></td>
            </tr>
            <tr>
                <td>Content</td>
                <td><input id="content" type="text" name="content"/></td>
            </tr>
            <tr>
                <td>Due Date</td>
                <td><input id="dueDate" type="date" name="dueDate"/></td>
            </tr>
            <tr>
                <td><input id="submit" type="submit" value="Create"></td>
            </tr>
            <tr>
                <td><input id="id" type="text" name="id" style="visibility: hidden"/></td>
                <td><input id="createdDate" type="datetime" name="createdDate" style="visibility: hidden"/></td>
                <td><input id="userId" type="text" name="userId" style="visibility: hidden" value="<%=userId%>"/></td>
                <td><input id="isDeleted" type="text" name="isDeleted" style="visibility: hidden" value="false"/></td>
                <td><input id="taskId" type="number" name="taskId" style="visibility: hidden"/></td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
