<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>

<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver" %>

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

<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>Simple Blog | Tambah Post</title>

</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
    </ul>
</nav>

<article class="art simple post">
    
    
    <h2 class="art-title" style="margin-bottom:40px">-</h2>

    <div class="art-body">
        <div class="art-body-inner">
            <h2>Update User</h2>
            
            <%
				  InitialContext ctx;
				  DataSource ds;
				  Connection conn;
				  Statement state;
				  
				  ResultSet rs;
				  int updateQuery = 0;
				
				  try {
				    ctx = new InitialContext();
					Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
				    String query = "SELECT * FROM user WHERE username = '" + String.valueOf(request.getParameter("userName") + "'");
				    state = conn.createStatement();
				    rs = state.executeQuery(query);
				    rs.next();
			%>
            
            
            
            <div id="contact-area">
                <form name="new_post" action="UpdateUserServlet" method="post">
                    <label for="Judul">Username:</label>
                    <input type="text" name="Username" id="Username" value="<% out.print(rs.getString("username"));%>">
                    <label for="Tanggal">Password:</label>
                    <input type="text" name="Password" id="Password" value="<% out.print(rs.getString("password"));%>">
                    <label for="Konten">Nama:</label><br>
                    <input type="text" name="Nama" id="Nama" value="<% out.print(rs.getString("nama"));%>">
                    <label for="Konten">E-mail:</label><br>
                    <input type="text" name="Email" id="Email" value="<% out.print(rs.getString("email"));%>">
                    <label for="Konten">Role:</label><br>
                    <input type="text" name="Role" id="Role" value="<% out.print(rs.getString("role"));%>">
                    <p></p>
                    <input type="submit" name="submit" value="Simpan" class="submit-button">
                </form>
                <%}catch(Exception e){out.println(""+e);} %>
            </div>
        </div>
    </div>

</article>

<footer class="footer">
    <div class="back-to-top"><a href="">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&Psi;</div>
    <aside class="offsite-links">
        BagusBagus
    </aside>
</footer>

</div>

<script type="text/javascript" src="assets/js/jquery.min.js"></script>
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