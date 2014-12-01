<%-- 
    Document   : LoginPage
    Created on : Nov 20, 2014, 8:30:01 PM
    Author     : Asep Saepudin
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | Login</title>
    </head>        
    <% if (request.getSession().getAttribute("currentUser") != null) { 
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "PublishedPosts"); 
    }
    %>
    <body class="default">
        <div class="wrapper">

            <nav class="nav">
                <a style="border:none;" id="logo" href="/"><h1>Simple<span>-</span>Blog</h1></a>                
            </nav>

            <article class="art simple post">        
                <h2 class="art-title" style="margin-bottom:40px">-</h2>

                <div class="art-body">
                    <div class="art-body-inner">
                        <h2>Login</h2>

                        <div id="contact-area">
                            <form method="POST" action="Login">
                                <label for="email">Email:</label>
                                <input type="text" name="email" id="email">

                                <label for="password">Password:</label>
                                <input type="password" name="password" id="password" > 
                                
                                <input type="submit" name="submit" value="Save" class="submit-button">
                            </form>
                        </div>
                    </div>
                </div>
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
<script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
