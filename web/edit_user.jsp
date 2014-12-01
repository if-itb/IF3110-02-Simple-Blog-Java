<%-- 
    Document   : edit_user
    Created on : Dec 1, 2014, 9:11:24 PM
    Author     : Fahziar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="Deskripsi Blog" />
<meta name="author" content="Judul Blog" />

<!-- Twitter Card -->

<meta property="og:type" content="article" />
<meta property="og:title" content="Simple Blog" />
<meta property="og:description" content="Deskripsi Blog" />
<meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}" />
<meta property="og:site_name" content="Simple Blog" />

<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>New User - Simple Blog</title>
<sql:setDataSource var="db_post" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simpleblog-java"
     user="root"  password="root"/>

</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.xhtml"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

 <div style="position:relative;top:180px;left:300px;">
    <h2>Edit User</h2>
    
    
    <sql:query dataSource="${db_post}" var="userData">
        SELECT * FROM users WHERE username="<%= request.getParameter("user")%>";
    </sql:query>
<div id="contact-area">
    <% 
        if (request.getParameter("submit") != null)
        {
    %>
    <sql:update var="results" dataSource="${db_post}">
        UPDATE `simpleblog-java`.`users` SET `password`='<%= request.getParameter("password")%>', 
        `role`='<%= request.getParameter("role") %>', `email`='<%= request.getParameter("email")%>' WHERE `users`.`username`='<%= request.getParameter("username") %>' ;
    </sql:update>
    <% 
        }
    %>
    <form action="edit_user.jsp" method="post">
        <label for="Nama">Username:</label>
        <input name="username" type="text" value="${userData.rows[0].username}"/>
        <label for="Email">Password:</label>
        <input name="password" type="password" value="${userData.rows[0].password}"/>
        <label for="Email">Email</label>
        <input name ="email" type="text" value="${userData.rows[0].email}"/>
        <label for="Role">Role:</label>
        <select name="role" value="${userData.rows[0].role}">
            <option value="admin">Admin</option>
            <option value="editor">Editor</option>
            <option value="owner">Owner</option>
        </select>
        <br />
        <input type="submit" name="submit" value="submit"/>
    </form>
    
</div>
</div>

<footer class="footer" style="top:230px;">
    <div class="back-to-top"><a href="">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&#x3A8;</div>
    <aside class="offsite-links">
        Asisten IF3110 /
        <a class="rss-link" href="#rss">RSS</a> /
        <br />
        <a class="twitter-link" href="http://twitter.com/YoGiiSinaga">Yogi</a> /
        <a class="twitter-link" href="http://twitter.com/sonnylazuardi">Sonny</a> /
        <a class="twitter-link" href="http://twitter.com/fathanpranaya">Fathan</a> /
        <br />
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
