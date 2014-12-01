<%-- 
    Document   : login
    Created on : Nov 24, 2014, 2:07:37 PM
    Author     : Teofebano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <center> 
            <h2>Login Details</h2> 
            <form action="LoginCheck.jsp" method="post">
            <br/>Username:<input type="text" name="username"> 
            <br/>Password:<input type="password" name="password">
            <br/><input type="submit" value="Submit"> 
            </form> 
        </center>
    </body>
</html>
