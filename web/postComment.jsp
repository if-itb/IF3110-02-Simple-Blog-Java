<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="connection" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simple_blog_java"
     user="root"  password=""/>

<sql:update dataSource="${connection}" var="result">
INSERT INTO `simple_blog_java`.`komentar` (`id`, `id_post`, `nama`, `email`, `komentar`, `tanggal`) VALUES (NULL, '<%= request.getParameter("id") %>', '<%= request.getParameter("Nama") %>', '<%= request.getParameter("Email") %>', '<%= request.getParameter("Komentar") %>', '<%= request.getParameter("Date") %>')
</sql:update>
