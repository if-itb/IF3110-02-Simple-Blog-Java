<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %><head>
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
		long commentId = random.nextLong() + 1000000000; 
		String comment_id = String.valueOf(commentId);
		String nama = request.getParameter("Nama");
		String post_id = request.getParameter("post_id");
		String comment_date = request.getParameter("comment_date");
		String komentar = request.getParameter("Komentar");
		String email = request.getParameter("Email");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		int updateQuery = 0;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
		if(comment_id != null && nama != null && post_id != null && comment_date != null && email != null && komentar != null){
			pState = conn.prepareStatement("INSERT INTO `comment` (`post_id`, `comment_id`, `email`, `nama`, `komentar`, `comment_date`) VALUES (?,?,?,?,?,?)");
			pState.setString(1, post_id);
			pState.setString(2, comment_id);
			pState.setString(3, email);
			pState.setString(4, nama);
			pState.setString(5, komentar);
			pState.setString(6, comment_date);
			updateQuery = pState.executeUpdate();
		}
	
	out.print("<li class=\"art-list-item\">\n"+
    "<div class=\"art-list-item-title-and-time\">\n"+
    "<h2 class=\"art-list-title\"><a href=\"post.html\">"+ nama +"</a></h2>\n"+
    "<div class=\"art-list-time\">"+ comment_date +"</div>\n"+
    "</div>\n"+
    "<p>" + komentar + "</p>\n"+
	"</li>");
	} catch(Exception e){out.println("Error" + e);}
	%>

</f:view>
</body>
</html>