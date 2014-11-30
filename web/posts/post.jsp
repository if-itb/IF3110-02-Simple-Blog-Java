<%-- 
    Document   : post
    Created on : Nov 22, 2014, 12:56:24 PM
    Author     : Rakhmatullah Yoga S
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="source.CookieHelper"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="source.KoneksiDatabase"%>
<%@page import="source.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
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

        <link rel="stylesheet" type="text/css" href="../assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <%
            Post post = new Post();
            int ID = Integer.valueOf(request.getParameter("id"));            
            post.setAtribut(ID);
            CookieHelper cookie = new CookieHelper(request.getCookies());
            String nama = "";
            String email = "";
        %>
        <title>Not a Simple Blog | <%= post.getJudul() %> </title>
    </head>
    <body class="default" onload="loadComment(<%= ID %>)">
    <div class="wrapper">

    <nav class="nav">
        <a style="border:none;" id="logo" href="../index.jsp"><h1>Not<span>-</span>a<span>-</span>Simple<span>-</span>Blog</h1></a>
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
                <p><%= post.getKonten() %></p>
                <p></p>

                <hr />

                <h2>Komentar</h2>

                <div id="contact-area">
                    <form name="commentForm" method="post" id="commentForm" action="#" onsubmit="return comment()">
                        <%
                            if(cookie.thereIsCookie()) {
                                KoneksiDatabase.setUser("root2");
                                KoneksiDatabase.setPassword("akhfa");
                                KoneksiDatabase.setDatabase("localhost","blog");
                                try {
                                    Connection koneksi = KoneksiDatabase.getKoneksi();
                                    Statement statement = koneksi.createStatement();
                                    String username = cookie.getUsername();
                                    System.out.println(username);
                                    String emailQuery = "SELECT * FROM `user` WHERE `username` = '"+username+"'";
                                    ResultSet result = statement.executeQuery(emailQuery);
                                    while (result.next()) {
                                        nama = result.getString("nama");
                                        System.out.println("nama = "+nama);
                                        email = result.getString("email");
                                        System.out.println("email = "+email);
                                    }
                                } catch (SQLException ex) {
                                    
                                }
                            %>
                                <label for="Nama">Nama:</label>
                                <input type="text" name="Nama" id="Nama" value="<%= nama %>" aria-required="true" required="" readonly>

                                <label for="Email">Email:</label>
                                <input type="text" name="Email" id="Email" value="<%= email %>" aria-required="true" required="" readonly>                                
                            <%}
                            else {%>
                                <label for="Nama">Nama:</label>
                                <input type="text" name="Nama" id="Nama" aria-required="true" required="">

                                <label for="Email">Email:</label>
                                <input type="text" name="Email" id="Email" aria-required="true" required="">                                
                            <%}
                        %>

                        <label for="Komentar">Komentar:</label><br>
                        <textarea name="Komentar" rows="20" cols="20" id="Komentar" aria-required="true" required=""></textarea>

                        <input name="PostId" id="PostId" type="hidden" value="<%= ID %>">
                        <input type="submit" name="submit" value="Kirim" class="submit-button" onclick="return validateEmail()">
                    </form>
                </div>
                <ul class="art-list-body">
                    <div id="Comment">
                    </div>
                </ul>
            </div>
        </div>

    </article>

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
