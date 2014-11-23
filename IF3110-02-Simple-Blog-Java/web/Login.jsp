<%-- 
    Document   : LoginPage
    Created on : Nov 20, 2014, 8:30:01 PM
    Author     : Asus
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>    
    <body>
        <% if (request.getSession().getAttribute("currentUser") == null) { %>
        <form action="Login" method="POST">
            Email
            <input type="text" name="email"/><br>		
            Password
            <input type="password" name="password"/>
            <input type="submit" value="submit">		
	</form>
        <% } else {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "UserLogged.jsp"); 
        }
        %>
    </body>
</html>
