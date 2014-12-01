<%-- 
    Document   : deleteNonAdmin
    Created on : Nov 25, 2014, 11:29:57 PM
    Author     : M. Reza Irvanda
--%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver" user="root" password="root" url="jdbc:mysql://localhost/simpleblog-java"/>
<sql:update var="results" dataSource="${db_source}">
            UPDATE `simpleblog-java`.`posts` SET `deleted` = '1' WHERE `posts`.`id`=<%= request.getParameter("post") %> ;
</sql:update>
            <c:redirect url="index.xhtml"/>
