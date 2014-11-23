<%-- 
    Document   : UserManagement
    Created on : Nov 23, 2014, 5:12:43 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.UserDAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Login.jsp"); 
            }        
            if (!currentUser.getRole().equals("Admin")) {
                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Uuuppssss.jsp"); 
            }
        %>
        
        <table>
            <tr>
                <td>Name</td>
                <td>Email</td>
                <td>Role</td>
                <td>Edit</td>
                <td>Hapus</td>
            </tr>
        <% ArrayList<User> users = (ArrayList<User>)request.getSession().getAttribute("allUsers");
           if (users == null) {
               users = UserDAO.getAllUsers();
           }
           for (int i=0; i<users.size(); i++) {
        %>
        <tr>
            <td><%= users.get(i).getName() %></td>
            <td><%= users.get(i).getEmail() %></td>
            <td><%= users.get(i).getRole() %></td>
            <td><a href="UpdateUser?email=<%= users.get(i).getEmail() %>">v</a></td>
            <td><a href="DeleteUser?email=<%= users.get(i).getEmail() %>">x</a></td>
        </tr>
        <% } %>
        </table>
        <a href="Register">Add New User</a>
    </body>
</html>
