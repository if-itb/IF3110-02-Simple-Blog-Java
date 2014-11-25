<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="connection" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simple_blog_java"
     user="root"  password=""/>

<sql:query dataSource="${connection}" var="result">
SELECT * from `komentar` where id_post = <%= request.getParameter("id") %>
</sql:query>

<c:forEach var="row"  items="${result.rows}">
    <li class="art-list-item">
        <div class="art-list-item-title-and-time">
            <h2 class="art-list-title"> <c:out value="${row.nama}"/> </h2>
            <div class="art-list-time"> <c:out value="${row.tanggal}"/> </div>
        </div>
        <p> <c:out value="${row.komentar}"/></p>
    </li>
</c:forEach>