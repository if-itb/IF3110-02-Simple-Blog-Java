<%-- 
    Document   : insert_comment
    Created on : Nov 25, 2014, 9:44:42 PM
    Author     : M. Reza Irvanda
--%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="com.mysql.jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

 <sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver" user="root" password="root" url="jdbc:mysql://localhost/simpleblog-java"/>
        <sql:update var="results" dataSource="${db_source}">
            INSERT INTO komentar(id_post,tanggal,nama,email, konten) VALUES(<%= request.getParameter("post")%>,NOW(),"<%= request.getParameter("Nama")%>","<%= request.getParameter("Email")%>","<%= request.getParameter("Komentar")%>");
        </sql:update>
            
            <html>
                <%= request.getParameter("nama")%>,<%= request.getParameter("email")%>,<%= request.getParameter("konten")%>
            </html>