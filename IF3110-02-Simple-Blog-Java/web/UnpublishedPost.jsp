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
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | Unpublished Posts</title>
    </head>
    <body>
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }        
            if (currentUser.getRole().equals("Owner")) {
//                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin dan Editor");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }
        %>
                        
<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="#"><h1>Unpublished<span>-</span>Posts</h1></a>
    <ul class="nav-primary">
        <% if (currentUser.getRole().equals("Admin")) { %>
        <li><a href="AddNewPost">+ Add New Post</a></li>
        <% } %>        
        <% if (currentUser != null) { %>
        <li><a href="PublishedPosts">+ Published Posts</a></li>
        <% } %>
        <% if (currentUser.getRole().equals("Admin")) { %>
        <li><a href="SoftDeletedPost">+ Deleted Posts</a></li>
        <% } if (currentUser.getRole().equals("Admin")) { %>
        <li><a href="UserManagement">+ User Management</a></li>
        <% } %>
        <% if (currentUser != null) { %>
        <li><a href="Logout">+ Logout</a></li>
        <% } %>        
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          <% ArrayList<Post> unpublishedPosts = (ArrayList<Post>)request.getSession().getAttribute("allUnpublishedPosts");
           if (unpublishedPosts == null) {
               unpublishedPosts = PostDAO.getAllUnpublishedPosts();
           }
           for (int i=0; i<unpublishedPosts.size(); i++) {
          %>    
            <li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="ViewPost?id=<%= unpublishedPosts.get(i).getId() %>"><%= unpublishedPosts.get(i).getJudul()%></a></h2>
                    <div class="art-list-time"><%= unpublishedPosts.get(i).getTanggal() %></div>
                    <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
                </div>
                <% if (PostDAO.hasImage(unpublishedPosts.get(i))) {  %>
                <p><img src="upload/<%= unpublishedPosts.get(i).getImage() %>" width="200" height="100"></p>
                <% } %>
                <p><%= unpublishedPosts.get(i).getKonten() %> &hellip;</p>
                <p>
                <a href="UpdatePost?id=<%= unpublishedPosts.get(i).getId() %>">Edit</a> | 
                <a href="PublishPost?id=<%= unpublishedPosts.get(i).getId() %>">Publish</a> | 
                <a href="javascript:void(0);" onclick="confirmSoftDeletion(<%= unpublishedPosts.get(i).getId() %>)">Delete</a> | 
                <% if (!PostDAO.hasImage(unpublishedPosts.get(i)) && currentUser != null) { %> <a href="AddImagePost?id=<%= unpublishedPosts.get(i).getId() %>">Add Image</a>
                <% } else if (PostDAO.hasImage(unpublishedPosts.get(i)) && currentUser != null) { %><a href="javascript:void(0);" onclick="confirmRemoveImagePost(<%= unpublishedPosts.get(i).getId() %>)">Remove Image</a><% } %>
                </p>
            </li>
            <% } %>
          </ul>
        </nav>
    </div>
</div>

<footer class="footer">
    <div class="back-to-top"><a href="#">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&Psi;</div>
    <aside class="offsite-links">
        Asisten IF3110 /
        <a class="rss-link" href="#rss">RSS</a> /
        <br>
        <a class="twitter-link" href="http://twitter.com/YoGiiSinaga">Yogi</a> /
        <a class="twitter-link" href="http://twitter.com/sonnylazuardi">Sonny</a> /
        <a class="twitter-link" href="http://twitter.com/fathanpranaya">Fathan</a> /
        <br>
        <a class="twitter-link" href="#">Renusa</a> /
        <a class="twitter-link" href="#">Kelvin</a> /
        <a class="twitter-link" href="#">Yanuar</a> /
        
    </aside>
</footer>

</div>

</body>
<script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
