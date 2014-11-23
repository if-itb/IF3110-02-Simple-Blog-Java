<%-- 
    Document   : registerfailed
    Created on : Nov 20, 2014, 10:52:29 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("currentUser") == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Login.jsp"); 
            }
        %>
        
        <h1>Register failed</h1>
        Error: <%= (String)request.getSession().getAttribute("registerUser") %>        
        <br>
        <a href="UserManagement">view</a>
    </body>
</html>
