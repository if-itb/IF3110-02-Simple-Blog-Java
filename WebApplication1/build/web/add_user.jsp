<%-- 
    Document   : addpost
    Created on : Nov 18, 2014, 5:22:59 PM
    Author     : adwisatya
--%>
<%@page import= "java.text.SimpleDateFormat"%>
<%@page import= "java.util.Date"%>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
        <form method="post">
			<label>Nama: </label><input type="text" id="user_name" name="user_name">
			<br/>
			<label>Username: </label><input type="text" id="user_username" name="user_username">
			<br/>
			<label>Password: </label><input type="text" id="user_password" name="user_password">
			<br/>
			<label>Email: </label><input type="text" id="user_email" name="user_email">
			<br/>
			<label>Status</label>
				<select id="user_status" name="user_status">
					<option value="1"> Admin </option>
					<option value="2"> Owner </option>
					<option value="3"> Editor </option>
				</select>
			<br/>
			<input type="submit" value="Tambah">
		</form>
		<%
			String user_name	=	 request.getParameter("user_name");
			String user_username = request.getParameter("user_username");
			String user_password = request.getParameter("user_password");
			String user_status = request.getParameter("user_status");
			String user_email	=	 request.getParameter("user_email");


			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			int updateQuery= 0 ;
			if(user_name != null && user_username != null && user_password != null  && user_email != null && user_status != null ){
				try{
					Connection connection = null;
					Statement statement = null;
					ResultSet rs = null;
					Class.forName(xDRIVER);
					connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
					statement = connection.createStatement();
					
					String query = "insert into users(username,password,nama,email,status,created) VALUES(\'"+user_username+"\',\'"+user_password+"\',\'"+user_name+"\',\'"+user_email+"\',\'"+user_status+"\',\'"+sdf.format(date)+"\')";
					updateQuery = statement.executeUpdate(query);
					
					if(updateQuery != 0){
						out.print("Penambahan user sukses");
						statement.close();
						connection.close();
					}

				}catch(SQLException ex){
					out.println(ex);
					out.println("Koneksi Bermasalah");
				}finally{

				}
			}			
		%>		
    </body>
</html>
