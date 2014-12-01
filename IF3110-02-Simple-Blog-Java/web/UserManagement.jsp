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
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | User Management</title>
    </head>    
    <body>
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPostsLogin.jsp"); 
            }        
            if (!currentUser.getRole().equals("Admin")) {
//                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts.jsp"); 
            }
        %>
            
    <body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="UserManagement"><h1>User<span>-</span>Management</h1></a>
    <ul class="nav-primary">
        <li><a href="Register">+ Add New User</a></li>
        <li><a href="PublishedPosts">+ Published Posts</a></li>        
        <li><a href="UnpublishedPosts">+ Unpublished Posts</a></li>       
        <li><a href="SoftDeletedPost">+ Deleted Posts</a></li>        
        <li><a href="Logout">+ Logout</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">              
           <% ArrayList<User> users = (ArrayList<User>)request.getSession().getAttribute("allUsers");
           if (users == null) {
               users = UserDAO.getAllUsers();
           }
           for (int i=0; i<users.size(); i++) {
           %>
           <li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><%= users.get(i).getName() %></h2>
                </div>
                <p>Email: <%= users.get(i).getEmail() %></p>
                <p>Role: <%= users.get(i).getRole() %></p>
                <p>
                  <a href="UpdateUser?email=<%= users.get(i).getEmail() %>">Edit</a> | <a href="DeleteUser?email=<%= users.get(i).getEmail() %>">Hapus</a>
                </p>
            </li>            
            <% } %>
          </ul>
        </nav>
    </div>
</div>

<footer class="footer">
    <div class="back-to-top"><a href="">Back to top</a></div>
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
</html>
