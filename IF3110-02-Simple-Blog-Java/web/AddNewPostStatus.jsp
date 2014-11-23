<%-- 
    Document   : AddNewPostStatus
    Created on : Nov 23, 2014, 8:41:53 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | Add New Post</title>
    </head>    
        <%            
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }        
            if (currentUser.getRole().equals("Editor")) {
//                request.getSession().setAttribute("forbidden", "Psst! Halaman khusus Admin dan Owner");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "PublishedPosts"); 
            }
        %>                        

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="PublishedPosts"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <!-- <li><a href="new_post.html">+ Tambah Post</a></li> -->
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time">Status</time>
            <h3 class="art-title"><%= (String)request.getSession().getAttribute("AddNewPostStatus") %></h3>
            <p class="art-subtitle"><a href="UnpublishedPosts">Continue...</a></p>
        </div>
    </header>


</article>

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

