<%-- 
    Document   : PublishedPost
    Created on : Nov 23, 2014, 9:38:08 PM
    Author     : Asep Saepudin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="wbd.tubesII.PostDAO"%>
<%@page import="wbd.tubesII.Post"%>
<%@page import="wbd.tubesII.Post"%>
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
        %>
        
        <table>
            <tr>
                <td>Judul</td>
                <td>Tanggal</td>
                <td>Konten</td>
                <td>Edit</td>
                <td>Hapus</td>
            </tr>
        <% ArrayList<Post> publishedPosts = (ArrayList<Post>)request.getSession().getAttribute("allPublishedPosts");
           if (publishedPosts == null) {
               publishedPosts = PostDAO.getAllPublishedPosts();
           }
           for (int i=0; i<publishedPosts.size(); i++) {
        %>
        <tr>
            <td><%= publishedPosts.get(i).getJudul()%></td>
            <td><%= publishedPosts.get(i).getTanggal().toString() %></td>
            <td><%= publishedPosts.get(i).getKonten() %></td>
            <td><a href="UpdatePost?id=<%= publishedPosts.get(i).getId() %>">v</a></td>
<!--            <td><a href="DeletePost?id=<%= publishedPosts.get(i).getId() %>">x</a></td>-->
            <td><a href="javascript:void(0);" onclick="confirmDeletion(<%= publishedPosts.get(i).getId() %>)">x</a></td>
        </tr>
        <% } %>
        </table>
    </body>
    <script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
