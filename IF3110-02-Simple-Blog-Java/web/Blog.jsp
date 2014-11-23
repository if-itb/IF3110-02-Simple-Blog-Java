<%-- 
    Document   : Blog.jsp
    Created on : Nov 23, 2014, 7:21:51 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            
            <% if (currentUser.getRole().equals("Admin")) { %>
            <br><a href="UserManagement">User Management</a>
            <% } %>
            <% if (currentUser.getRole().equals("Admin") || currentUser.getRole().equals("Owner")) { %>
            <br><a href="AddNewPost">Add New Post</a>
            <% } %>
            <br><a href="UserManagement">View Published Posts</a>
            <% if (currentUser.getRole().equals("Admin") || currentUser.getRole().equals("Editor")) { %>
            <br><a href="AddNewPost">View Unpublished Posts</a>
            <% } %>
        </center>
    </body>
</html>
