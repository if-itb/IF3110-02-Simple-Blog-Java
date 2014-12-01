<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<%
	InitialContext ctx;
	DataSource ds;
	Connection conn;
	PreparedStatement pState;
	ResultSet rs;
	int updateQuery = 0;
	  
	try {
	    ctx = new InitialContext();
		Class.forName("com.mysql.jdbc.Driver");
		String post_id = request.getParameter("post_id");
		String tittle = request.getParameter("Judul");
		String postDate = request.getParameter("Tanggal");
		String konten = request.getParameter("Konten");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
	    if(post_id != null && tittle != null && postDate != null && konten != null){
	    	pState = conn.prepareStatement("UPDATE `post` set `tittle` = ?, `post_date` = ?, `konten` = ? WHERE `post_id` = ?");
			pState.setString(1, tittle);
			pState.setString(2, postDate);
			pState.setString(3, konten);
			pState.setString(4, post_id);
			updateQuery = pState.executeUpdate();
		}
	 	}catch(Exception e){out.println(""+e);}
	response.sendRedirect("index.jsp"); 
%>

<f:view>

</f:view>
</body>
</html>