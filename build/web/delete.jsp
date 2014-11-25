<%-- 
    Document   : delete.jsp
    Created on : Nov 25, 2014, 11:22:55 PM
    Author     : M. Reza Irvanda
--%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver" user="root" password="" url="jdbc:mysql://localhost/simpleblog-java"/>
<sql:update var="results" dataSource="${db_source}">
            DELETE FROM `simpleblog-java`.`posts` WHERE id=<%= request.getParameter("post") %> ;
</sql:update>
            <jsp:forward page="view_post.jsp"/>
