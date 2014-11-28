<%-- 
    Document   : home
    Created on : Nov 25, 2014, 9:23:28 AM
    Author     : Gilang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="com.gilang.beans.User" />

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<link rel="stylesheet" type="text/css" href="resources/screen.css" />
			<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"/>
            <title>Welcome to Our Blog</title>
			    <link href="css/bootstrap.css" rel="stylesheet">
				<link href="css/style.css" rel="stylesheet">
				<link href="css/profil.css" rel="stylesheet">
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
				</nav>
				<div id="home" class="posts">
					<nav class="art-list">
					<%	if(user.getUsername() == null){ %>
					<form action="login.jsp">
						<div class="input-group-lg">
						<input type="text" name="user_id" class="form-control" style="
									width: 30%;
									margin-left: 35%;
									border: 0px;
									margin-bottom: 20px;
									margin-top: 20px;
									text-align: center;
								" placeholder="Username kamu ;D">
						<input type="password" name="password" class="form-control" style="
									width: 30%;
									margin-left: 35%;
									border: 0px;
									margin-bottom: 20px;
									margin-top: 20px;
									text-align: center;
								" placeholder="Password kamu ;D">
						<input type="submit" class="btn btn-primary btn-lg btn-block" 
								style="
									width: 20%;
									margin-left: 40%;
									background-color: green;
									border: 0px;
									margin-bottom: 20px;
									margin-top: 20px;
								" 
								>
						</div>
					</form>
					<form action="login.jsp">
						<input type="hidden" name="user_id" value="guest">
						<input type="hidden" name="password" value="">
						<input type="submit" class="btn btn-primary btn-lg btn-block" 
								style="
									width: 20%;
									margin-left: 40%;
									background-color: green;
									border: 0px;
									margin-bottom: 20px;
									margin-top: 20px;
									" value="Login as Guest"
								>
					</form>
					<%	}
						else{
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", "index.jsp");
						}
					%>
					</nav>
				</div>
			</div>
        </body>
    </html>
</f:view>
