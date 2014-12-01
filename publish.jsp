<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Simplest Blog | Send Content</title>
    </head>
    <body>
        <f:view>
            <%
                String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
                String user = "root";
                String pwd = "asdasd123";
                Connection con;
                PreparedStatement ps;
                
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection(host, user, pwd);
                    String sql = "UPDATE post SET status='published' WHERE id_post = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, String.valueOf(request.getParameter("id_post")));
                    ps.executeUpdate();
                }
                catch (Exception e){
                    out.println("Error" + e);
                }
                response.sendRedirect("index.jsp");
            %>
        </f:view>
    </body>
</html>
