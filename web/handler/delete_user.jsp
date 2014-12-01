<%-- 
    Document   : addpost
    Created on : Nov 18, 2014, 5:22:59 PM
    Author     : adwisatya
--%>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		<%
			String user_username = request.getParameter("user_username");

			boolean updateQuery= true ;
			if(user_username != null){
				try{
					
					PreparedStatement preparedStatement;
					ResultSet rs = null;
					Class.forName(xDRIVER);
					Connection connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
					preparedStatement = connection.prepareStatement("delete from users where username = ?");
					preparedStatement.setString(1,user_username);
					updateQuery = preparedStatement.execute();
					if(updateQuery != false){
						out.print("User berhasil dihapus");
						preparedStatement.close();
						connection.close();
					}

				}catch(SQLException ex){
					out.println("Koneksi Bermasalah. Tidak dapat menghapus user.");
				}finally{

				}
			}			
		%>		