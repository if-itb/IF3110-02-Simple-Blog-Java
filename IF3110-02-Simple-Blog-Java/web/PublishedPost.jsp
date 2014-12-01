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
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | Published Posts</title>
    </head>
    <% User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
    <body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="#"><h1>Published<span>-</span>Posts</h1></a>
    <ul class="nav-primary">
        <% if (currentUser == null) { %>
            <li><a href="Login">+ Login </a></li> 
        <% } else { if (!currentUser.getRole().equals("Editor")) { %>
        <li><a href="AddNewPost">+ Add New Post</a></li>
        <% } if (currentUser.getRole().equals("Admin") || currentUser.getRole().equals("Editor")) { %>
        <li><a href="UnpublishedPosts">+ Unpublished Posts</a></li>
        <% } if (currentUser.getRole().equals("Admin")) { %>
        <li><a href="SoftDeletedPost">+ Deleted Posts</a></li>
        <% } if (currentUser.getRole().equals("Admin")) { %>
        <li><a href="UserManagement">+ User Management</a></li>
        <% } if (currentUser != null) { %>
        <li><a href="Logout">+ Logout</a></li>
        <% } } %>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          <% ArrayList<Post> publishedPosts = (ArrayList<Post>)request.getSession().getAttribute("allPublishedPosts");
           if (publishedPosts == null) {
               publishedPosts = PostDAO.getAllPublishedPosts();
           }                      
           for (int i=0; i<publishedPosts.size(); i++) {
          %>    
            <li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="ViewPost?id=<%= publishedPosts.get(i).getId() %>"><%= publishedPosts.get(i).getJudul()%></a></h2>
                    <div class="art-list-time"><%= publishedPosts.get(i).getTanggal() %></div>
                    <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
                </div>
                <% if (PostDAO.hasImage(publishedPosts.get(i))) {  %>
                <p><img src="upload/<%= publishedPosts.get(i).getImage() %>" width="200" height="100"></p>
                <% } %>
                <p><%= publishedPosts.get(i).getKonten() %> &hellip;</p>
                <p> <% if (currentUser != null) { %>
                    <a href="UpdatePost?id=<%= publishedPosts.get(i).getId() %>">Edit</a> | <a href="javascript:void(0);" onclick="confirmSoftDeletion(<%= publishedPosts.get(i).getId() %>)">Delete</a> | <% if (!PostDAO.hasImage(publishedPosts.get(i)) && currentUser != null) { %> <a href="AddImagePost?id=<%= publishedPosts.get(i).getId() %>">Add Image</a> <% } else if (PostDAO.hasImage(publishedPosts.get(i)) && currentUser != null) { %><a href="javascript:void(0);" onclick="confirmRemoveImagePost(<%= publishedPosts.get(i).getId() %>)">Remove Image</a><% } %>
                  <% } %>
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
