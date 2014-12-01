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
	Connection conn;
	PreparedStatement pState;
	Random random = new Random();
	
	try {
		long postId = random.nextLong() + 1000000000; 
		String post_id = String.valueOf(postId);
		String tittle = request.getParameter("Judul");
		String postDate = request.getParameter("Tanggal");
		String konten = request.getParameter("Konten");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		int updateQuery = 0;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
		if(post_id != null && tittle != null && postDate != null && konten != null){
			pState = conn.prepareStatement("INSERT INTO post (post_id, tittle, post_date, konten)VALUES (?,?,?,?)");
			pState.setString(1, post_id);
			pState.setString(2, tittle);
			pState.setString(3, postDate);
			pState.setString(4, konten);
			updateQuery = pState.executeUpdate();
		}
	} catch(Exception e){out.println("Error" + e);}
	response.sendRedirect("faces/index.jsp"); 
	
	%>

<f:view>

</f:view>
</body>
</html>