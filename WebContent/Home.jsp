<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Blog | Unpublished Post</title>
<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
</head>
<body class="default">
<f:view>
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="Home.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          <%
          	String id = null;
			String judul = null;
			String isi = null;
			String tanggal = null;
			ResultSet rset = null;
			try {
	            String connectionURL = "jdbc:mysql://localhost/db_simpleblog";
	            Connection conn = null; 
	            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	            conn = DriverManager.getConnection(connectionURL, "root", "");
	            if(!conn.isClosed()) {
	            	Statement stmt = conn.createStatement ();
	                rset = stmt.executeQuery ("SELECT * FROM post WHERE status='unpublished'");

				   	while(rset.next()) {
				   		id = rset.getString("id");
						judul = rset.getString("judul");
						isi = rset.getString("konten");
						Date date = rset.getDate("tanggal");
						DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
						tanggal = df.format(date);	
			%>
			<li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="Post.jsp?id=1"><% out.println(judul); %></a></h2>
                    <div class="art-list-time"><% out.println(tanggal); %></div>
                    <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div>
                </div>
                <% out.println(isi.substring(0, 210)+"..."); %>
                <p>
                  <a href="Publish?id=<% out.println(id); %>">Publish</a> | <a href="Edit.jsp?id=<% out.println(id); %>">Edit</a> | <a href="Delete?id=<% out.println(id); %>" onclick="return confirm('Anda yakin ingin menghapus post ini?')">Delete</a>
                 </p>
            </li>
           <%
					}

			  		stmt.close();
			  		rset.close();
	            }
	            conn.close();
	        }catch(Exception ex){
	            out.println("Unable to connect to database"+ex);
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
</f:view>
</body>
</html>