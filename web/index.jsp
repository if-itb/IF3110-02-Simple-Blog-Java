<%-- 
    Document   : index
    Created on : Nov 25, 2014, 6:05:21 AM
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
			<% if(user.getRole() == 1 || user.getRole() == 3){ %>
			<li><a href="new_post.jsp"><img src="resources/img/add.png" style="width: auto; height: 80px; size: auto;"> </a></li>
			<% } %>
			<li><a href="logout.jsp"><img src="resources/img/logout.png" style="width: auto; height: 80px; size: auto;"></a></li>
		</ul>
	</nav>

	<div id="home">
		<div class="posts">
			<nav class="art-list">
				<% if(user.getRole() == 2){ %>
					<a href="unpublished.jsp" style="text-align: right; display: block; width: 100%;" class="art-list-title">Publish Posts</a>
				<% } else if(user.getRole() == 3){ %>
				<div style="text-align: right; display: block; width: 100%;">
					<a href="deleted.jsp" class="art-list-time">Restore Post</a>
					|
					<a href="unpublished.jsp" class="art-list-time">Publish Post</a>
					|
					<a href="manage_user.jsp" class="art-list-time">Manage User</a>
				</div>
				<% } %>
			  <ul class="art-list-body">
			<%	for(Post p : sql.getPosts(false)){	  %>
				<li class="art-list-item">
					<div class="art-list-item-title-and-time">
						<h2 class="art-list-title"><a href="post.jsp?post_id=<%= p.getPost_id() %>"><%= p.getTitle() %></a></h2>
						<div class="art-list-time"><%= p.getDate() %></div>
						<div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
					</div>
					<p><%= p.getContent() %></p>
					<p>
					
					<% if(user.getRole() == 1 || user.getRole() == 2 || user.getRole() == 3){ %>
					<a href="edit_post.jsp?post_id=<%= p.getPost_id() %>">Edit</a>
					   | 
					   <a onclick="return Konfirmasi()" href="delete_post.jsp?post_id=<%= p.getPost_id() %>">Hapus</a>
					<% } %>
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
<script>
function Konfirmasi(x) {
    if (confirm("Yakin mau delete?") == true) 
		return true;
    return false;
}
</script>
	</body>
    </html>
</f:view>
