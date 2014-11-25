<%-- 
    Document   : add
    Created on : Nov 25, 2014, 6:30:47 PM
    Author     : Teofebano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>User Details</h2> 
        <form action="add.jsp" method="post">
        <br/>Username   :<input type="text" name="username" value="username"> 
        <br/>Password   :<input type="text" name="password" value="password">
        <br/>Role   :<input type="text" name="role" value="role">
        <br/>Email   :<input type="text" name="email" value="email">
        <br/><br><input type="submit" value="Submit"> 
        </form>
    </body>
</html>
