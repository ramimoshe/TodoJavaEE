<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Login Page</title>
</head>

<body>
<form action="/controller/users/login" method="get">

    Please enter your username
    <input type="text" name="un" value="rami"/><br>

    Please enter your password
    <input type="text" name="pw" type="password" value="123"/>

    <input type="submit" value="submit">

</form>



</body>
</html>
