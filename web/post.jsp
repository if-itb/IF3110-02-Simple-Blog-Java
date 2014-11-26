<%-- 
    Document   : post
    Created on : Nov 25, 2014, 10:36:34 PM
    Author     : daniar heri
--%>

<%@page import="com.gilang.beans.Komentar"%>
<%@page import="com.gilang.beans.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="user" scope="session" class="com.gilang.beans.User" />
<jsp:useBean id="sql" class="com.gilang.sql.DBAdapter" scope="session"/>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <% Post post = sql.getPost(Integer.valueOf(request.getParameter("post_id"))); %>
            
            <link rel="stylesheet" type="text/css" href="resources/screen.css" />
            <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"/>
            
            <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->

            <title>Simple Blog | Apa itu Simple Blog?</title>
        </head>

        <body class="default">
			<img src="resources/img/bg.png" class="background">
			<img src="resources/img/navbackground.png" class="navbackground">
            <div class="wrapper">

            <nav class="nav">
				<div class="logologo">
					<img src="resources/img/navicon.png" class="navicon">
					<a id="logo" href="home.jsp"><img src="resources/img/icontext.png" class="icontext"></a>
				</div>
                <ul class="nav-primary">
                    <li><a href="new_post.jsp">+ Tambah Post</a></li>
                </ul>
            </nav>

            <article class="art simple post">

                <header class="art-header">
                    <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
                        <time class="art-time"><%= post.getDate() %></time>
                        <h2 class="art-title"><%= post.getTitle()%></h2>
                        <p class="art-subtitle"></p>
                    </div>
                </header>

                <div class="art-body">
                    <div class="art-body-inner">
                        <hr class="featured-article" />
                        <%= post.getContent() %>
                        <hr />

                        <h2>Komentar</h2>

                        <div id="contact-area">
                            <form method="post" action="add.jsp?post_id=<%= post.getPost_id() %>">
                                <label for="Nama">Nama:</label>
                                <input type="text" name="Nama" id="Nama">

                                <label for="Email">Email:</label>
                                <input type="text" name="Email" id="Email">

                                <label for="Komentar">Komentar:</label><br>
                                <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
                                <input type="submit" name="submit" value="Kirim" class="submit-button">
                            </form>
                        </div>

                        <ul class="art-list-body">
                        <%	for(Komentar k : sql.getKomentar(post.getPost_id())){	  %>
                            <li class="art-list-item">
                                <div class="art-list-item-title-and-time">
                                    <h2 class="art-list-title"><a href="post.html"><%= k.getUser_id() %></a></h2>
                                    <div class="art-list-time"><%= k.getDate()%></div>
                                </div>
                                <p> <%= k.getContent()%></p>
                            </li>
                        <%	} %>
                        </ul>
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
</f:view>
