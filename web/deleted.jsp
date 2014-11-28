<%-- 
    Document   : deleted
    Created on : Nov 28, 2014, 5:45:01 PM
    Author     : Gilang
--%>

<%@page import="com.gilang.beans.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="sql" class="com.gilang.sql.DBAdapter" scope="session"/>
<jsp:useBean id="user" scope="session" class="com.gilang.beans.User" />

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
       <head>

	<link rel="stylesheet" type="text/css" href="resources/screen.css" />
	<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"/>

	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<title>Simple Blog</title>

	</head>
	<%	if(user.getUsername() == null){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "home.jsp");
		}
	%>
	Hello <%= user.getUsername() %>
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
			<li><a href="logout.jsp">Logout</a></li>
		</ul>
	</nav>

	<div id="home">
		<div class="posts">
			<nav class="art-list">
				<h5>Deleted Post</h5><br/>
			  <ul class="art-list-body">
			<%	for(Post p : sql.getDeletedPosts()){	  %>
				<li class="art-list-item">
					<div class="art-list-item-title-and-time">
						<h2 class="art-list-title"><a href="post.html"><%= p.getTitle() %></a></h2>
						<div class="art-list-time"><%= p.getDate() %></div>
						<div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
					</div>
					<p><%= p.getContent() %></p>
					<p>
					<a href="restore.jsp?post_id=<%= p.getPost_id() %>">Restore</a>
					</p>
				</li>
			<%	} %>
				  
			  </ul>
			</nav>
		</div>
	</div>

	<footer class="footer">
		<div class="back-to-top"><a href="">Back to top</a></div>
		<!-- <div class="footer-nav"><p></p></div> -->
		<div class="psi">&Psi;</div>
		<aside class="offsite-links">
			Gilang Julian Suherik | 13512045
			<br/>
			Daniar Heri Kurniawan | 13512064
			<br/>
			Muhammad Husain Jakfari | 13512067
		</aside>
	</footer>

	</div>
	</body>
    </html>
</f:view>
