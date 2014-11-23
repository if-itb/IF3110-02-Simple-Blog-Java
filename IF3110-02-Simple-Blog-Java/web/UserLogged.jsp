<%-- 
    Document   : userLogged
    Created on : Nov 20, 2014, 8:31:36 PM
    Author     : Asus
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="wbd.tubesII.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>            
            <%                  
                if (request.getSession().getAttribute("currentUser") == null) {            
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", "Login.jsp"); 
                }
                
                User currentUser = (User)request.getSession().getAttribute("currentUser");
            %>
            Welcome <%= currentUser.getName() + " -- " + currentUser.getEmail() %>
            || You are an 
            <%= currentUser.getRole() %>
            
            <% if (currentUser.getRole().equals("Admin")) { %>
            <br><a href="UserManagement">User Management</a>
            <% } %>
            <br><a href="LogoutServlet">Logout</a>
        </center>
    </body>
</html>
