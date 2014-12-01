<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="connection" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simple_blog_java"
     user="root"  password=""/>

<sql:query dataSource="${connection}" var="result">
SELECT * from post where status = 'deleted';
</sql:query>

<c:forEach var="row"  items="${result.rows}">
    <div class="post">
        <a href="#" class="title">
            <c:out value="${row.judul}"/>
        </a>
        <div class="date-unpublished">
            <c:out value="${row.tanggal}"/>
        </div>
        <div class="unpublished">
            [deleted]
        </div>
        <div class="content">
            <c:out value="${row.konten}"/>
        </div>
        <a onclick="updatePost(<c:out value="${row.id}"/>)">
            [Permanent Delete]
        </a>
    </div>
</c:forEach>