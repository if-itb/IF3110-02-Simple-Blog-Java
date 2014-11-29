<%-- 
    Document   : view_post
    Created on : Nov 25, 2014, 1:03:30 PM
    Author     : M. Reza Irvanda
--%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<html>
    <link rel="stylesheet" type="text/css" href="assets/css/screen.css">
<head>
<title>View Post</title>
</head>
<body>
 
<sql:setDataSource var="db_post" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simpleblog-java"
     user="root"  password="root"/>
 
<sql:query dataSource="${db_post}" var="result">
SELECT id,judul,konten,tanggal FROM posts;
</sql:query>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
<c:forEach var="row" items="${result.rows}">
    <li class="art-list-item">
        <div class="art-list-item-title-and-time">
            <h2 class="art-list-title"><a href="view_singular_post.jsp?post=<c:out value='${row.id}' />"><c:out value="${row.judul}" /></a></h2>
            <div class="art-list-time"> <c:out value="${row.tanggal}" /> </div>
        </div>
            <p style="max-height: 150pt"><c:out value="${row.konten}" /></p>
            <p>
                  <a href="#">Edit</a> | <a href="delete.jsp?posts="<c:out value="${row.id}" />">Hapus | </a>
            </p>
        
    </li>
</c:forEach>
          </ul>
        </nav>
    </div>
</div>

 
</body>
</html>