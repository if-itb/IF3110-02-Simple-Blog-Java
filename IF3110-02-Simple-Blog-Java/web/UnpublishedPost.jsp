<%-- 
    Document   : UnpublishedPost
    Created on : Nov 23, 2014, 9:27:46 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.Post"%>
<%@page import="wbd.tubesII.PostDAO"%>
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
            if (currentUser.getRole().equals("Owner")) {
                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin Editor");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Uuuppssss.jsp"); 
            }
        %>
        
        <table>
            <tr>
                <td>Judul</td>
                <td>Tanggal</td>
                <td>Konten</td>
                <td>Edit</td>
                <td>Publish</td>
            </tr>
        <% ArrayList<Post> unpublishedPosts = (ArrayList<Post>)request.getSession().getAttribute("allUnpublishedPosts");
           if (unpublishedPosts == null) {
               unpublishedPosts = PostDAO.getAllUnpublishedPosts();
           }
           for (int i=0; i<unpublishedPosts.size(); i++) {
        %>
        <tr>
            <td><%= unpublishedPosts.get(i).getJudul()%></td>
            <td><%= unpublishedPosts.get(i).getTanggal().toString() %></td>
            <td><%= unpublishedPosts.get(i).getKonten() %></td>
            <td><a href="UpdatePost?id=<%= unpublishedPosts.get(i).getId() %>">v</a></td>
            <td><a href="PublishPost?id=<%= unpublishedPosts.get(i).getId() %>">x</a></td>
        </tr>
        <% } %>
        </table>
    </body>
</html>
