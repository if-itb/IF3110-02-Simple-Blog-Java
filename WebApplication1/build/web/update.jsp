<%-- 
    Document   : update.jsp
    Created on : Nov 20, 2014, 8:52:38 PM
    Author     : adwisatya
--%>
<%@ page pageEncoding="UTF-8" %>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%
	String user_name	=	 request.getParameter("user_name");
	String user_username = request.getParameter("user_username");
	String user_password = request.getParameter("user_password");
	String user_status = request.getParameter("user_status");
	String user_email	=	 request.getParameter("user_email");

		if(user_name != null && user_username != null && user_password != null  && user_email != null && user_status != null ){
			ResultSet result = null;
			Connection connection = null;
			PreparedStatement pStatement;
			String Query = "update users SET email='Aryya Dwisatya W' WHERE no = '4';";
			pStatement = connection.prepareStatement(Query);
			//pStatement.setString(1, user_name);
			//pStatement.setString(2, user_password);
			//pStatement.setString(3, user_email);
			//pStatement.setString(4, user_username);

			int qExec = pStatement.executeUpdate();
			if(qExec!=0){
				out.println("Update berhasil");
			}
			result.close();
			connection.close();
			pStatement.close();
		}

%>
