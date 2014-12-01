<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>

<f:view>
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

<link rel="stylesheet" type="text/css" href="assets/style.css" /></link>
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"></link>
<link href='http://fonts.googleapis.com/css?family=Dawning+of+a+New+Day' rel='stylesheet' type='text/css'></link>
<link href='http://fonts.googleapis.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'></link>
<title>The Simplest Blog</title>


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
        String sql = "SELECT * FROM post WHERE status='published' and status_delete='undeleted' ORDER BY tanggal DESC";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
%>

<div class="wrapper">

<nav class="nav">
    <div id="login">
        <h:form rendered="#{!Login.isLogin()}">
            Username: <h:inputText value="#{Login.username}" style="outline: none; border: none; border-style: none; background-color: #97cdb6; font-family: 'Quicksand', sans-serif; height: 16px;"></h:inputText>
            Password: <h:inputSecret value="#{Login.password}" style="outline: none; border: none; border-style: none; background-color: #97cdb6; font-family: 'Quicksand', sans-serif; height: 16px;"></h:inputSecret> 
            <h:commandButton action="#{Login.Login()}" value="submit" style="margin-left: 6px; border: none; border-style: none; outline: none; background-color: #acaba9; font-family: 'Quicksand', sans-serif; height: 16px; font-weight: 700; font-color: #fff; cursor: pointer; color:#fff;"></h:commandButton>
        </h:form>
            
        <h:outputLabel rendered="#{Login.isLoginFailed()}">Invalid username or password</h:outputLabel>
    </div>
    
    <div id="loginsc">
        <h:form rendered="#{Login.isLogin()}">
            <h:outputLabel value="Halo #{Login.username}"></h:outputLabel>
            <h:commandButton action="#{Login.logout()}" value="logout" style="margin-left: 6px; border: none; border-style: none; outline: none; background-color: #acaba9; font-family: 'Quicksand', sans-serif; height: 16px; font-weight: 700; font-color: #fff; cursor: pointer; color:#ffffff;"></h:commandButton>
        </h:form>
    </div>
        
    <div id="nav-logo">
        <a id="logo" href="index.jsp"><h1>The Simplest Blog</h1></a>
    </div>
    <div id="menu">
        <ul class="nav-primary">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="about.jsp">About</a></li>
            <li><a href="contact.jsp">Contact</a></li>
        <f:subview id="manajemen-user" rendered="#{Login.isAdmin()}"><li><a href="page2.xhtml">User-Management</a></li></f:subview>
        <f:subview id="draft" rendered="#{Login.isEditor() or Login.isAdmin()}"><li><a href="unpublished.jsp">Draft</a></li></f:subview>
        <f:subview id="tambah-post" rendered="#{Login.isOwner() or Login.isAdmin()}"><li><a href="new.jsp">Create Post</a></li></f:subview>
        </ul>
    </div>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
            <div class="postcontainer">
                <ul class="art-list-body">
                    <%
                      while (rs.next()) {
                    %>
                    <li class="art-list-item">
                        <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id_post=<% out.print(rs.getInt("id_post")); %>"><% out.print(rs.getString("judul"));%></a></h2>
                            <div class="art-list-time"><% out.print(rs.getDate("tanggal")); %></div>
                         </div>
                            <p><% if ((rs.getString("content")).length() > 200) {
                                out.print(rs.getString("content").substring(0,199));
                                out.print("...</br>"); %>
                                <a href="post.jsp?id_post=<% out.print(rs.getInt("id_post")); %>">Continue Reading</a>
                            <% } else {
                            out.print(rs.getString("content"));
                            } %></p>
                        <p>
                            <f:subview id="edit" rendered="#{Login.isLogin()}"><a href="editpost.jsp?id_post=<% out.print(rs.getInt("id_post")); %>">Edit</a> | <a onclick="return confirm('Apakah Anda yakin postingan dihapus?')"  href="softdelete.jsp?id_post=<% out.print(rs.getInt("id_post")); %>">Hapus</a></f:subview>
                        </p>
                    </li>
                    <% }
                    }
                    catch (Exception e) {
                      out.println("Error" + e); } %>
                </ul>
            </div>
            <div class="postcontainer" id="rightside">
                <a id="text-contact">Kindly visit our projects</a><br></br>
                <a id="contact-logo" href="#"><img src="img/mail.png"></img></a>
                <a id="contact-logo" href="#"><img src="img/fb.png"></img></a><br></br>
                <a id="contact-logo" href="#"><img src="img/twitter.png"></img></a> 
                <a id="contact-logo" href="#"><img src="img/youtube.png"></img></a><br></br>
                <a id="contact-logo" href="#"><img src="img/pinterest.png"></img></a> 
                <a id="contact-logo" href="#"><img src="img/instagram.png"></img></a> 
            </div>
        </nav>
    </div>
</div>

<footer id="footerSlideContainer">
        <div id="footerSlideLink">
            <div id="footerSlideContent">
                <div id="footerSlideText"><a href="">Back to top</a></div>
            </div>
            </div>
        </div>   
</footer>

</div>
</body>

</html>
</f:view>
