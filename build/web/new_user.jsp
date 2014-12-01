<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Load new post</title>
</head>
<body>
<% 
	InitialContext ctx;
	Connection conn;
	PreparedStatement pState;
	int updateQuery = 0;
	  
	try {
	    ctx = new InitialContext();
            Class.forName("com.mysql.jdbc.Driver");
            String username = request.getParameter("Username");
            String nama = request.getParameter("Nama");
            String email = request.getParameter("Email");
            String role = request.getParameter("Role");
            String password = request.getParameter("Password");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
	    if(username != null && password != null && nama != null && email != null && role != null){
                pState = conn.prepareStatement("INSERT INTO user (username, password, nama, email, role)VALUES (?,?,?,?,?)");
                pState.setString(1, username);
                pState.setString(2, password);
                pState.setString(3, nama);
                pState.setString(4, email);
                pState.setString(5, role);
                updateQuery = pState.executeUpdate();
            }
	} catch(Exception e){out.println("Error" + e);}
	response.sendRedirect("faces/man_user.jsp"); 
	
	%>

<f:view>

</f:view>
</body>
</html>