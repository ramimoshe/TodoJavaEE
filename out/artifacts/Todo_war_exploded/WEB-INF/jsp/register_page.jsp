<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Register Page</title>
    <script type="application/javascript">
        function validateInputs(){
            username = document.getElementById("username");
            password = document.getElementById("password");

            if (username == null || username.length < 3 || password == null || password.length < 3){
                alert("username and password must contain at least 3 characters");
                return false;
            }

            return true;
        }
    </script>
</head>

<body>
<form action="/controller/users/register" method="get" onsubmit="return validateInputs();">

    Please choose a username (at least 3 digits)
    <input id="username" type="text" name="un"/><br>

    Please choose a password (at least 3 digits)
    <input id="password" type="text" name="pw"/>

    <input type="submit" value="submit">

</form>



</body>
</html>
