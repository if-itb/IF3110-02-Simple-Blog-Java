<%-- 
    Document   : index
    Created on : Nov 23, 2014, 5:39:59 AM
    Author     : akhfa
--%>

<%@page import="source.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="source.User"%>
<%@page import="source.CookieHelper"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="description" content="Deskripsi Blog">
        <meta name="author" content="Judul Blog">

        <!-- Twitter Card -->
        <meta name="twitter:card" content="summary">
        <meta name="twitter:site" content="omfgitsasalmon">
        <meta name="twitter:title" content="Simple Blog">
        <meta name="twitter:description" content="Deskripsi Blog">
        <meta name="twitter:creator" content="Simple Blog">
        <meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">

        <meta property="og:type" content="article">
        <meta property="og:title" content="Simple Blog">
        <meta property="og:description" content="Deskripsi Blog">
        <meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
        <meta property="og:site_name" content="Simple Blog">

        <link rel="stylesheet" type="text/css" href="../assets/css/screen_backend.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
        <title>Dashboard - Not a Simple Blog</title>
    </head>
    <body class="default">    
        <%  
            Post listPost = new Post();
            CookieHelper cookie = new CookieHelper(request.getCookies());
            if(cookie.thereIsCookie()) {
                listPost.cookieHeaderCheck(cookie);
            }
            else {
                response.sendRedirect("../login/index.html");
            }  
        %>
        
        <br>
        <div class="wrapper">
            
            <nav class="nav">
                <a style="border:none;" id="logo" href="../index.jsp"><h1>Not<span>-</span>a<span>-</span>Simple<span>-</span>Blog</h1></a>
                <ul class="nav-primary">
                    <li>
                        <%= listPost.showManagementHeader() %>
                    </li>
                </ul>
            </nav>
            
            <div id="home">
                <div class="posts">
                    <nav class="art-list">
                      <ul class="art-list-body">
                        <form action="user_management.jsp" method="POST" id="tombol">
                            <input type="submit" value="User Management">
                        </form>
                        <form action="post_management.jsp" method="POST" id="tombol">
                            <input type="submit" value="Post Management">
                        </form>
                      </ul>
                    </nav>
                </div>
            </div>
            <br>
            <footer class="footer">
                <div class="back-to-top"><a href="">Back to top</a></div>
                <!-- <div class="footer-nav"><p></p></div> -->
                <div class="psi">&Psi;</div>
                <aside class="offsite-links">
                    <a class="twitter-link" href="http://github.com/IF3110-II-4/IF3110-02-Simple-Blog-Java" target="_blank">Tugas Besar 2 IF3110</a> /
                    <a class="rss-link" href="#rss">RSS</a> /
                    <br>
                    <a class="twitter-link" href="http://www.facebook.com/ajiballinst" target="_blank">Try Ajitiono</a> /
                    <a class="twitter-link" href="http://www.facebook.com/rakhmatullahyogasutrisna" target="_blank">Rakhmatullah Yoga Sutrisna</a> /
                    <a class="twitter-link" href="http://www.facebook.com/akhmadfakhoni" target="_blank">Akhmad Fakhoni Listiyan Dede</a>
                </aside>
            </footer>
        </div>

        <script type="text/javascript" src="../assets/js/function.js"></script>
        <script type="text/javascript" src="../assets/js/fittext.js"></script>
        <script type="text/javascript" src="../assets/js/app.js"></script>
        <script type="text/javascript" src="../assets/js/respond.min.js"></script>
        <script type="text/javascript">
          var ga_ua = '{{! TODO: ADD GOOGLE ANALYTICS UA HERE }}';

          (function(g,h,o,s,t,z){g.GoogleAnalyticsObject=s;g[s]||(g[s]=
              function(){(g[s].q=g[s].q||[]).push(arguments)});g[s].s=+new Date;
              t=h.createElement(o);z=h.getElementsByTagName(o)[0];
              t.src='//www.google-analytics.com/analytics.js';
              z.parentNode.insertBefore(t,z)}(window,document,'script','ga'));
              ga('create',ga_ua);ga('send','pageview');
        </script>
    </body>
</html>
