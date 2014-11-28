<%-- 
    Document   : post
    Created on : Nov 25, 2014, 10:36:34 PM
    Author     : daniar heri
--%>

<%@page import="com.gilang.beans.Komentar"%>
<%@page import="com.gilang.beans.Post"%>
<%@page import="com.gilang.beans.UserData"%>
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
			<%	if(user.getUsername() == null){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "home.jsp");
				}
			%>
            <%	Post post = sql.getPost(Integer.valueOf(request.getParameter("post_id"))); 
				UserData userData = sql.getUser(user.getUsername());
				System.out.println(user.getUsername());
			%>
            
            <link rel="stylesheet" type="text/css" href="resources/screen.css" />
            <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"/>
            
            <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->

            <title>Simple Blog | <%= post.getTitle() %></title>
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
					<% if(user.getRole() == 1 || user.getRole() == 3){ %>
						<li><a href="new_post.jsp"><img src="resources/img/add.png" style="width: auto; height: 80px; size: auto;"> </a></li>
					<% } %>
					<li><a href="logout.jsp"><img src="resources/img/logout.png" style="width: auto; height: 80px; size: auto;"> </a></li>
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
								<form method="post" onsubmit=" addComment()" name="commentForm">
									<input type="hidden" name="post_id" value="<%= post.getPost_id() %>"/>
									<% if(!user.getUsername().equals("guest")){ %>
										<input type="hidden" name="user_id" id="Nama" value="<%=  userData.getUser_id() %>"/>
										<input type="hidden" name="email" value="<%=  userData.getEmail() %>"/>
									<% } else if(user.getRole() != 2){%>
										<label for="Nama">Nama:</label>
										<input type="text" name="user_id" id="Nama" value="anonymous"/>
										<label for="Email">Email:</label>
										<input type="text" name="email" id="Email" onchange="return validateEmail()"/>
									<% } 
										if(user.getRole() != 2){
									%>
									<label for="Komentar">Komentar:</label>
									<textarea name="content" rows="20" cols="20" id="Komentar"></textarea><br/>
									<input type="button" name="submit" value="Kirim" class="submit-button" onclick="addComment()">
									<% } %>
								</form>
							</div>
						<div id="comment">
						
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
			<script type="text/javascript" src="resources/comment.js"></script>
			<script type="text/javascript">loadComment()</script>

        </body>
    </html>
</f:view>
