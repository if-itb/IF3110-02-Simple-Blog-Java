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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <link rel="stylesheet" type="text/css" href="assets/style.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <title>The Simplest Blog | Post</title>
        
        <script type='text/javascript'>
        function Validatemail() {
            var x = document.forms["myForm"]["Email"].value;
            var atpos = x.indexOf("@");
            var dotpos = x.lastIndexOf(".");
            if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {
                alert("Email address is invalid");
                return false;
            } else {
                return true;
                //addcomment();
                
            }
        }

        function addcomment() {
            var nama = document.getElementById('Nama').value;
            var email = document.getElementById('Email').value;
            var komentar = document.getElementById('Komentar').value;
            var waktu = document.getElementById('waktu').value;
            var id_post = document.getElementById('id_post').value;
            var xmlhttp;
            if (window.XMLHttpRequest) {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
             } 
             else { // code for IE6, IE5
                 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
             }
             xmlhttp.onreadystatechange=function() {
                 if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    document.getElementById("myDiv").innerHTML+= xmlhttp.responseText;
                    window.scrollTo(0, document.body.scrollHeight);
                    document.getElementById("Nama").value = "";
                document.getElementById("Email").value = "";
                document.getElementById("Komentar").value = "";
                }
             };
             xmlhttp.open("POST","AddCommentServlet",true);
             xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
             xmlhttp.send("&id_post="+id_post+"&Nama="+nama+"&Email="+email+"&Komentar="+komentar+"&waktu="+waktu+"&id_post="+id_post);
        } 
        </script>
    </head>

    <body class="default">

    <% 
        String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        InitialContext itc;
        Connection con;
        Statement st;
        ResultSet rsPost;
        ResultSet rsComment;
        
        int updateQuery = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(host, user, pwd);
            String sql = "SELECT * FROM post WHERE id_post = " + String.valueOf(request.getParameter("id_post") + "");
            st = con.createStatement();
            rsPost = st.executeQuery(sql);
            rsPost.next();
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
            <f:subview id="tambah-post" rendered="#{Login.isOwner() or Login.isAdmin()}"><li><a href="faces/new.jsp">Create Post</a></li></f:subview>
            </ul>
        </div>
    </nav>

    <article class="art simple post">

        <header class="art-header">
            <div class="art-header-inner">
                <time class="art-time"><% out.print(rsPost.getString("tanggal")); %></time>
                <h2 class="art-title"><% out.print(rsPost.getString("judul")); %></h2>
                <p class="art-subtitle"></p>
            </div>
        </header>

        <div class="art-body">
            <div class="art-body-inner">
                <hr class="featured-article" />
                <p><% out.print(rsPost.getString("content")); %></p>
                <%
                    } catch (Exception e) {
                            out.print(""+ e );
                    }
                %>
                <hr />
                
                <%
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection(host, user, pwd);
                        String sql = "SELECT * FROM comment WHERE id_post = " + String.valueOf(request.getParameter("id_post") + "");
                        st = con.createStatement();
                        rsComment = st.executeQuery(sql);
                %>
                
                <h2>Comment</h2>

                <div id="contact-area">
                    <form method="post" action="AddCommentServlet" id="myForm" onsubmit="addcomment(); return false;">
                        <h:outputLabel value="Name :"></h:outputLabel>
                        <h:inputText id="Nama" value="#{Login.name}"></h:inputText>

                        <h:outputLabel value="Email :"></h:outputLabel>
                        <h:inputText id="Email" value="#{Login.email}"></h:inputText>

                        <label for="Komentar">Comment:</label><br>
                        <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>

                        <input type="submit" name="submit" value="Send" class="submit-button" onclick="return Validatemail()">
                        <input type="hidden" name="id_post" id="id_post" value="<%out.print(String.valueOf(request.getParameter("id_post")));%>">
                        <% java.util.Date date = new java.util.Date();
                    	java.sql.Date dateSQL = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());%>
                        <input type="hidden" name="waktu" id="waktu" value="<%out.print(dateSQL);%>">
                    </form>
                </div>

                <ul id="myDiv" class="art-list-body">
                <%
                while(rsComment.next()) { %>
                    <li class="art-list-item">
                        <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp"><% out.print(rsComment.getString("nama")); %></a></h2>
                            <div class="art-list-time"><% out.print(rsComment.getString("waktu"));%>
                            </div>
                        </div>
                            <p><% out.print(rsComment.getString("komentar"));%></p>
                    </li>

                <%
                } %>   
                </ul>
            </div>
        </div>
        <% } catch (Exception e) {
                out.print("" + e);
            }
        %>
        <footer id="footerSlideContainer">
            <div id="footerSlideLink">
                <div id="footerSlideContent">
                    <div id="footerSlideText"><a href="">Back to top</a></div>
                </div>
                </div>
            </div>   
        </footer>
    </body>
</html>
</f:view>
