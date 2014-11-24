<%-- 
    Document   : new_post
    Created on : Nov 24, 2014, 10:24:11 PM
    Author     : Imburden
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@page import="source.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="0; url=../admin/index.jsp" />
        
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

        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
        <title>New Post - Not a Simple Blog</title>
    </head>
    <body class = "default">
        <div class="wrapper">

        <nav class="nav">
            <a style="border:none;" id="logo" href="../admin/index.jsp"><h1>Not<span>-</span>a<span>-</span>Simple<span>-</span>Blog</h1></a>
            <ul class="nav-primary">
                
            </ul>
        </nav>

        <article class="art simple post">


            <h2 class="art-title" style="margin-bottom:40px">-</h2>

            <div class="art-body">
                <div class="art-body-inner">
                    <h2>Tambah Post</h2>

                    <div id="contact-area">
                        <form method="post" action="new_post_red.jsp">
                            <label for="Judul">Judul:</label>
                            <input type="text" name="Judul" id="Judul">

                            <label for="Tanggal">Tanggal:</label>
                            <input type="text" name="Tanggal" id="Tanggal">

                            <label for="Konten">Konten:</label><br>
                            <textarea name="Konten" rows="20" cols="20" id="Konten"></textarea>

                            <input type="submit" name="submit" value="Simpan" class="submit-button">
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
                Tugas Besar 2 IF3110 /
                <a class="rss-link" href="#rss">RSS</a> /
                <br>
                <a class="twitter-link" href="http://www.facebook.com/ajiballinst" target="_blank">Try Ajitiono</a> /
                <a class="twitter-link" href="http://www.facebook.com/rakhmatullahyogasutrisna" target="_blank">Rakhmatullah Yoga Sutrisna</a> /
                <a class="twitter-link" href="http://www.facebook.com/akhmadfakhoni" target="_blank">Akhmad Fakhoni Listiyan Dede</a>
            </aside>
        </footer>

        </div>

        <script type="text/javascript" src="assets/js/function.js"></script>
        <script type="text/javascript" src="assets/js/fittext.js"></script>
        <script type="text/javascript" src="assets/js/app.js"></script>
        <script type="text/javascript" src="assets/js/respond.min.js"></script>
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
