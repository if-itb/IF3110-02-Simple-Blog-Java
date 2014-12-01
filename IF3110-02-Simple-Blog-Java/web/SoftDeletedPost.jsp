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
        <title>Simple Blog | Deleted Posts</title>
    </head>
    <body>
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }        
            if (!currentUser.getRole().equals("Admin")) {
//                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin dan Editor");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }
        %>
                        
<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="#"><h1>Deleted<span>-</span>Posts</h1></a>
    <ul class="nav-primary">

        <li><a href="AddNewPost">+ Add New Post</a></li>
        <li><a href="PublishedPosts">+ Published Posts</a></li>
        <li><a href="UnpublishedPosts">+ Unpublished Posts</a></li>
        <li><a href="UserManagement">+ User Management</a></li>
        <li><a href="Logout">+ Logout</a></li>
        
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          <% ArrayList<Post> deletedPosts = (ArrayList<Post>)request.getSession().getAttribute("allDeletedPosts");
           if (deletedPosts == null) {
               deletedPosts = PostDAO.getAllUnpublishedPosts();
           }
           for (int i=0; i<deletedPosts.size(); i++) {
          %>    
            <li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="ViewPost?id=<%= deletedPosts.get(i).getId() %>"><%= deletedPosts.get(i).getJudul()%></a></h2>
                    <div class="art-list-time"><%= deletedPosts.get(i).getTanggal() %></div>
                    <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
                </div>
                <% if (PostDAO.hasImage(deletedPosts.get(i))) {  %>
                <p><img src="upload/<%= deletedPosts.get(i).getImage() %>" width="200" height="100"></p>
                <% } %>
                <p><%= deletedPosts.get(i).getKonten() %> &hellip;</p>
                <p>
                  <a href="UndeletePost?id=<%= deletedPosts.get(i).getId() %>">Undelete</a> | <a href="javascript:void(0);" onclick="confirmDeletion(<%= deletedPosts.get(i).getId() %>)">Delete</a>
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
