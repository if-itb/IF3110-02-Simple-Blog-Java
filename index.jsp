<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
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

<title>Simple Blog</title>


</head>

<body class="default">

<%
    String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String pwd = "asdasd123";
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(host, user, pwd);
        String sql = "SELECT * FROM post WHERE status='published' ORDER BY tanggal DESC";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
%>

<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.xhtml">+ Tambah Post</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
            <%
                while (rs.next()) {
            %>
            <li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="post.jsp?id_post=<% out.print(rs.getInt("id_post")); %>"><% out.print(rs.getString("judul"));%></a></h2>
                    <div class="art-list-time"><% out.print(rs.getDate("tanggal")); %></div>
                </div>
                    <p><% out.print(rs.getString("content")); %></p>
                <p>
                    <a href="#">Edit</a> | <a onclick="return confirm('Apakah Anda yakin postingan dihapus?')"  href="delete.jsp?id_post=<% out.print(rs.getInt("id_post")); %>">Hapus</a>
                </p>
            </li>
            <% }}
            catch (Exception e) {
                out.println("Error" + e); } %>
            }
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
</body>

</html>
