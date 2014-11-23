<%-- 
    Document   : register
    Created on : Nov 20, 2014, 10:35:52 PM
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
        <title>Simple Blog | Add New User</title>
    </head>    
    <%        
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if (currentUser == null) {            
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "PublishedPosts"); 
        }        
        if (!currentUser.getRole().equals("Admin")) {
            request.getSession().setAttribute("adminOnly", "Psst! Halaman khusus Admin");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "AdminOnly.jsp"); 
        }
    %>

    <body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="UserManagement"><h1>User<span>-</span>Management</h1></a>
    <ul class="nav-primary">
        <li><a href="#">+ Add New User</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    
    <h2 class="art-title" style="margin-bottom:40px">-</h2>

    <div class="art-body">
        <div class="art-body-inner">
            <h2>Add New User</h2>

            <div id="contact-area">
                <form method="POST" action="Register">
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email">

                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password">

                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name">
                    
                    <label for="role">Role:</label>
                    <select name="role" id="role">
                        <option value="Admin">Admin</option>
                        <option value="Owner">Owner</option>
                        <option value="Editor">Editor</option>                
                    </select>                    
                    <br>
                    <input type="submit" name="submit" value="Save" class="submit-button">
                </form>
            </div>
        </div>
    </div>

</article>

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
</html>
