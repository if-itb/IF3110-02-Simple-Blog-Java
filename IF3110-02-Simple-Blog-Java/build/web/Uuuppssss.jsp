<%-- 
    Document   : AdminOnly
    Created on : Nov 23, 2014, 5:10:08 PM
    Author     : Asep Saepudin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | Ups</title>
    </head>            
    <body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="PublishedPosts"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time">Uuuppsss</time>
            <h3 class="art-title"><%= (String)request.getSession().getAttribute("forbidden") %></h3>
            <p class="art-subtitle"><a href="PublishedPosts">Continue...</a></p>
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
