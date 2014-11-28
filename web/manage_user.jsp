<%-- 
    Document   : manage_user
    Created on : Nov 25, 2014, 8:47:54 PM
    Author     : Gilang
--%>

<%@page import="com.gilang.beans.UserData"%>
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
			<li><a href="new_user.jsp">+ Tambah User</a></li>
			<li><a href="logout.jsp">Logout</a></li>
		</ul>
	</nav>

	<div id="home">
		<div class="posts">
			<nav class="art-list">
			  <ul class="art-list-body">
			<%	for(UserData u : sql.getUsersData()){
					if(!u.getUser_id().equals("guest")){
			%>
				<li class="art-list-item">
					<div class="art-list-item-title-and-time">
						<h2 class="art-list-title"><%= u.getUser_id() %></h2>
					</div>
					<p><%	switch(u.getRole()){
							case 1: out.print("Owner");
									break;
							case 2: out.print("Editor");
									break;
							case 3: out.print("Admin");
									break;
							}
					%><br/>
					<%= u.getEmail() %></p>
					<p>
					
					<a href="edit_user.jsp?user_id=<%= u.getUser_id() %>">Edit</a>
					   | 
					<a onclick="return Konfirmasi()" href="delete_user.jsp?user_id=<%= u.getUser_id() %>">Hapus</a>
					</p>
				</li>
			<%		} 
				}
			%>
				  
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
function Konfirmasi() {
    if (confirm("Yakin mau delete?") == true) 
		return true;
	return false;
}
</script>
	</body>
    </html>
</f:view>
