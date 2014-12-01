<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<f:view>
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

<title>Simple Blog | Apa itu Simple Blog?</title>

<script type="text/javascript">

function cekData(){
    if(new_comment.Nama.value == ""){
        alert("Nama harus diisi!");
        return false;
    }
    else if(new_comment.Email.value == ""){
        alert("E-mail harus diisi!");
        return false;
    }
    else if(new_comment.Komentar.value == ""){
        alert("Komentar post harus diisi!");
        return false;
    }
    else{
        checkEmail();
        showComment();
        return false;
    }
}

function checkEmail(){
    var validformat=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!validformat.test(new_comment.Email.value)){
        alert("Email anda salah!");
    }
}

function showComment() {
    var nama = document.getElementById('Nama').value;
    var email = document.getElementById('Email').value;
    var komentar = document.getElementById('Komentar').value;
    var post_id = document.getElementById('post_id').value;
    var comment_date = document.getElementById('comment_date').value;
    if (window.XMLHttpRequest) {
       // code for IE7+, Firefox, Chrome, Opera, Safari
       xmlhttp=new XMLHttpRequest();
    } 
    else { // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            document.getElementById("listKomentar").innerHTML=xmlhttp.responseText + document.getElementById("listKomentar").innerHTML;
        }
    }
    xmlhttp.open("POST","NewCommentServlet",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("&post_id="+post_id+"&Nama="+nama+"&Email="+email+"&Komentar="+komentar+"&comment_date="+comment_date+"&post_id="+post_id);
}

</script>


</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <h:form rendered="#{!loginHandler.login}">
        <h:outputText value="Username : "/>
        <h:inputText value="#{loginHandler.username}"></h:inputText>
        <h:outputText value="Password : "/>
        <h:inputSecret value="#{loginHandler.password}"></h:inputSecret>
        <h:commandButton value="login" action="#{loginHandler.login()}"></h:commandButton>
    </h:form>
    <h:form rendered="#{loginHandler.login}">
        <h:outputLabel value="Welcome #{loginHandler.username}" ></h:outputLabel>
        <h:commandButton value="logout" action="#{loginHandler.logout()}"></h:commandButton>
    </h:form>
    <ul class="nav-primary">
        <f:subview id="publish3" rendered="#{loginHandler.admin||loginHandler.owner}"><li><a href="newpost.jsp">+ Tambah Post</a></li></f:subview>
        <f:subview id="publish" rendered="#{loginHandler.editor||loginHandler.admin}"><li><a href="publish_post.jsp">Publish Post</a></li></f:subview>
        <f:subview id="publish2" rendered="#{loginHandler.admin}"><li><a href="man_user.jsp">Manage user</a></li></f:subview>
        <f:subview id="publish4" rendered="#{loginHandler.admin}"><li><a href="deleted_post.jsp">Manage Deleted Post</a></li></f:subview>
    </ul>
</nav>



<article class="art simple post">

	<%
				  InitialContext ctx;
				  DataSource ds;
				  Connection conn;
				  Statement state;
				  ResultSet rsPost;
				  ResultSet rsComment;
				  
				  int updateQuery = 0;
				
				  try {
				    ctx = new InitialContext();
					Class.forName("com.mysql.jdbc.Driver");
				    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
				    String query = "SELECT * FROM post WHERE post_id = '" + String.valueOf(request.getParameter("postId") + "'");
				    state = conn.createStatement();
				    rsPost = state.executeQuery(query);
				    rsPost.next();
			%>
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time"><% out.print(rsPost.getString("post_date")); %></time>
            <h2 class="art-title"><% out.print(rsPost.getString("tittle")); %></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
            <p><% out.print(rsPost.getString("konten")); %></p>
		<%
		}catch(Exception e){out.print(""+e);}
		%>
            <hr />
            
            <%try {
            	ctx = new InitialContext();
				Class.forName("com.mysql.jdbc.Driver");
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
			    String query = "SELECT * FROM comment WHERE post_id = '" + String.valueOf(request.getParameter("postId") + "' ORDER BY comment_id DESC");
			    state = conn.createStatement();
			    rsComment = state.executeQuery(query);
			     %>
            
            <h2>Komentar</h2>

            <div id="contact-area">
                    <f:subview id="komen" rendered="#{loginHandler.guest}"><label for="Nama">Nama:</label>
                        
                        <form name="new_comment" method="post" onsubmit="cekData(); return false;">
                        <input type="text" name="Nama" id="Nama">

                        <label for="Email">Email:</label>
                        <input type="text" name="Email" id="Email">
                    
                        <label for="Komentar">Komentar:</label><br>
                        <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
                    </f:subview>
                    <f:subview id="komen2" rendered="#{!loginHandler.guest}">
        
                        <form name="new_comment" method="post" onsubmit="showComment(); return false;">
                        <input type="hidden" name="Nama" id="Nama" value="${loginHandler.username}">
                        <input type="hidden" name="Email" id="Email" value="yanfa@yanfa.yanfa">
                        <input type="hidden" name="Email" id="Email">
                        <label for="Komentar">Komentar:</label><br>
                       <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
                    </f:subview>
                    <input type="submit" name="submit" value="Kirim" class="submit-button">
                    <input type="hidden" name="post_id" id="post_id" value="<%out.print(String.valueOf(request.getParameter("postId")));%>">
                    <% java.util.Date date = new java.util.Date();
                    	java.sql.Date dateSQL = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());%>
                    <input type="hidden" name="comment_date" id="comment_date" value="<%out.print(dateSQL);%>">
                </form>
            </div>

            <ul class="art-list-body" id="listKomentar">
            	<%while(rsComment.next()){ %>
                <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                        <h2 class="art-list-title"><a href="post.jsp"><% out.print(rsComment.getString("nama")); %></a></h2>
                        <div class="art-list-time"><% out.print(rsComment.getString("comment_date")); %></div>
                    </div>
                    <p><% out.print(rsComment.getString("komentar")); %></p>
                </li>
				<%} %>
            </ul>
        </div>
    </div>
	<%}catch(Exception e){out.print(""+e);} %>
</article>

<footer class="footer">
    <div class="back-to-top"><a href="">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&Psi;</div>
    <aside class="offsite-links">
        Asisten IF3110 /
        <a class="rss-link" href="#rss">RSS</a> 	/
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