<%-- 
    Document   : edit_post
    Created on : Nov 25, 2014, 10:23:38 AM
    Author     : Gilang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="sql" scope="session" class="com.gilang.sql.DBAdapter" />
<jsp:useBean id="user" scope="session" class="com.gilang.beans.User" />
<%@page import="com.gilang.beans.Post" %>

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

		<title>Simple Blog | Tambah Post</title>
		<%	if(user.getUsername() == null){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "home.jsp");
				}
			%>

		</head>

		<body class="default">
		
		<% Post post = sql.getPost(Integer.valueOf(request.getParameter("post_id"))); %>
		
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
				<li><a href="logout.jsp"><img src="resources/img/logout.png" style="width: auto; height: 80px; size: auto;"> </a></li>
			</ul>
		</nav>

		<article class="art simple post">


			<h2 class="art-title" style="margin-bottom:40px">-</h2>

			<div class="art-body">
				<div class="art-body-inner">
					<h2>Tambah Post</h2>

					<div id="contact-area">
						<form method="post" name="form1" action="update.jsp">
							<label for="note"></label>
							<p id="note" style="visibility: hidden">Tanggal Salah!</p>
							<input type="hidden" name="post_id" value="<%= post.getPost_id() %>"/>
							<label for="Judul">Judul:</label>
							<input type="text" name="title" id="Judul" value="<%= post.getTitle() %>"/>
							<div id="Tgl" style="display: inline-block; width: 100%">
								<label for="Tanggal">Tanggal:</label>
								<input type="text" name="date" id="Tanggal" value="<%= post.getDate() %>" onchange="return cektanggal()" placeholder="YYYY-MM-DD" maxlength="10"/>
							</div>
							<label for="Konten">Konten:</label><br/>
							<textarea name="content" rows="20" cols="20" id="Konten"><%= post.getContent() %></textarea>

							<input type="submit" name="submit" value="Simpan" onclick="return cektanggal()" class="submit-button"/>
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
				Gilang Julian Suherik | 13512045
				<br/>
				Daniar Heri Kurniawan | 13512064
				<br/>
				Muhammad Husain Jakfari | 13512067
			</aside>
		</footer>

		</div>
<script type="text/javascript">

function cektanggal()
{
	var tanggal = document.forms["form1"]["date"].value;
	var n = tanggal.search("[0-9]{4}-[0-9]{2}-[0-9]{2}");
	if (n === 0){
		var Dat = new Date(tanggal);
		var Now = new Date();
		if (Dat>Now){
			document.getElementById("Tanggal").style.background = "lime";
			document.getElementById("Tanggal").style.color = "white";
		}
		else if((Dat.getDate() === Now.getDate()) && (Dat.getMonth() === Now.getMonth()) && (Dat.getFullYear()==Now.getFullYear())){
			document.getElementById("Tanggal").style.background = "lime";
			document.getElementById("Tanggal").style.color = "white";
		}
		else{
			document.getElementById("Tanggal").style.background = "red";
			document.getElementById("Tanggal").style.color = "white";
			document.getElementById("Tanggal").focus();
			return false;
		}
	}
	else{
		document.getElementById("Tanggal").style.background = "red";
		document.getElementById("Tanggal").style.color = "white";
		document.getElementById("Tanggal").focus();
        document.getElementById("note").style.visibility = "visible";
        document.getElementById("note").style.height = 31;
        return false;
        
	}
}
</script> 
</body>
    </html>
</f:view>
