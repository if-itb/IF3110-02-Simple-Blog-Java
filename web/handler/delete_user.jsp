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
			String user_id = request.getParameter("id");

			boolean updateQuery= true ;
			if(user_id != null){
				try{
					
					PreparedStatement preparedStatement;
					ResultSet rs = null;
					Class.forName(xDRIVER);
					Connection connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
					preparedStatement = connection.prepareStatement("delete from users where no = ?");
					preparedStatement.setString(1,user_id);
					updateQuery = preparedStatement.execute();
					if(updateQuery != false){
						out.print("User berhasil dihapus");
						System.out.println("User berhasil dihapus");
						preparedStatement.close();
						connection.close();
					}
					response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", "../userAdm.jsp");
				}catch(SQLException ex){
					ex.printStackTrace();
					out.println("Koneksi Bermasalah. Tidak dapat menghapus user.");
					System.out.println("Koneksi Bermasalah. Tidak dapat menghapus user.");
				}finally{
				}
			}			
		%>		