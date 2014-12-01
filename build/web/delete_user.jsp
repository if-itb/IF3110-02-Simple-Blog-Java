<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<f:view>

<% 
	Connection conn;
	PreparedStatement pState;
	Random random = new Random();
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		int updateQuery = 0;
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
	    pState = conn.prepareStatement("DELETE FROM user WHERE username = ?");
	    pState.setString(1, String.valueOf(request.getParameter("userName")));
	    updateQuery = pState.executeUpdate();
	} catch(Exception e){out.println("Error" + e);}
	response.sendRedirect("faces/man_user.jsp"); 
	
	%>


<?php
    $session_post_id = $_GET['pc'];
    $con=mysqli_connect("localhost","root","","wbd_db");
        $sql="DELETE FROM post WHERE post_id = '$session_post_id'";
        if (!mysqli_query($con,$sql)) {
          die('Error: ' . mysqli_error($con));
        }
    mysqli_close($con);
    header('Location: index.php');
?>

</f:view>
</body>
</html>