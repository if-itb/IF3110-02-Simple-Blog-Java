<%-- 
    Document   : ViewPost
    Created on : Nov 23, 2014, 11:27:55 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.PostDAO"%>
<%@page import="wbd.tubesII.Post"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>Simple Blog | View Post</title>
    </head>    
    <%                        
        Post post = (Post)request.getSession().getAttribute("post");
        if (post == null) {            
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "PublishedPosts"); 
        }
    %>    
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");              
        %>
        
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
            <time class="art-time"><%= post.getTanggal() %></time>
            <h2 class="art-title"><%= post.getJudul() %></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
            <% if (PostDAO.hasImage(post)) {  %>
            <br>
            <center>
            <p><img src="upload/<%= post.getImage() %>" width="400" height="200"></p>            
            </center>
            <% } %>            
            <p><%= post.getKonten() %></p>            

            <hr />
            
            <% if (post.getStatus().equals("Published")) { %>            
            <h2>Komentar</h2>

            <div id="contact-area">
                <form method="POST" action="#">                    
                    <input type="hidden" name="id" id="id" value="<%= post.getId()  %>">
        
                    <label for="Nama">Nama:</label>
                    <% if (currentUser == null) { %>
                    <input type="text" name="nama" id="nama">
                    <% } else  {%>
                    <input type="text" name="nama" id="nama" disabled="true" value="<%= currentUser.getName() %>">
                    <% }%>
                    
                    <label for="Email">Email:</label>
                    <% if (currentUser == null) { %>
                    <input type="text" name="email" id="email" onkeyup="validateEmail()" onchange="validateEmail()">
                    <% } else  {%>
                    <input type="text" name="email" id="email" disabled="true" onkeyup="validateEmail()" value="<%= currentUser.getEmail() %>">
                    <% }%>
            
                    <label for="Komentar">Komentar:</label><br>            
                    <textarea name="komentar" rows="20" cols="20" id="komentar"></textarea>

                    <input type="submit" name="submitKomentar" id="submitKomentar" value="Kirim" class="submit-button" id="SubmitKomentar" onclick="sendComment(); return false	">
                </form>
            </div>

            <ul class="art-list-body" id="komentar-placeholder">
                
            </ul>                            
            <% } %>
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
