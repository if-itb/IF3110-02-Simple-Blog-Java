<%-- 
    Document   : admin crud
    Created on : Nov 30, 2014, 12:04:14 PM
    Author     : Ryuxaki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<sql:setDataSource var="connection" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simple_blog_java"
     user="root"  password=""/>

<sql:update dataSource="${connection}" var="result">
    DELETE FROM user WHERE username = '<%= request.getParameter("username") %>'
</sql:update>
    
<sql:query dataSource="${connection}" var="result">
    SELECT * from user;
</sql:query>

<c:forEach var="row"  items="${result.rows}">
    <tr>
        <td><c:out value="${row.username}"/></td>
        <td><c:out value="${row.password}"/></td>
        <td><c:out value="${row.email}"/></td>
        <td><c:out value="${row.role}"/></td>
        <td>
            <a href="edit_user.xhtml?username=${row.username}" class="edit" id="edit"> edit </a> | <a class="delete" id="delete"> delete </a>
        </td>
    </tr>
</c:forEach>