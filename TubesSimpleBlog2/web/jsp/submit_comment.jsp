<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
<%
String url = "jdbc:mysql://localhost:3306/datapost";
	   String driver = "com.mysql.jdbc.Driver";
	   String userName = "root"; 
	   String password = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,password);
		   
		   Date today = new Date();
		   Timestamp date = new Timestamp(today.getTime());
		   String insertToDB = "insert into komentar (`Nama`,`Email`,`Komentar`,`postid`,`tanggal`) value (?,?,?,?,?)";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   preparedStatement.executeUpdate();
		   conn.close();
	   } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
	   }

%> 

	<li class="art-list-item">
                <div class="art-list-item-title-and-time">
                    <h2 class="art-list-title"><a href="post.php" >.$rowc['Nama'].</a></h2>
                    <div class="art-list-time">.$rowc['Tanggal'].</div>
                </div>
                <p id="komen">.$rowc['Komentar'].</p>
            </li>');

<%

%>
    </body>
</html>
