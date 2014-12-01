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
	String id	=	 request.getParameter("id");
	String user_name	=	 request.getParameter("user_name");
	String user_username = request.getParameter("user_username");
	String user_password = request.getParameter("user_password");
	String user_status = request.getParameter("user_status");
	String user_email	=	 request.getParameter("user_email");
	
		if(id != null && user_name != null && user_username != null && user_password != null  && user_email != null && user_status != null ){
			Connection connection = null;
			PreparedStatement pStatement = null;
			Class.forName(xDRIVER);
			connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
			String Query = "UPDATE users SET username=?,password=?,nama=?,email=?,status=? WHERE no=?";
			pStatement = connection.prepareStatement(Query);
			pStatement.setString(1, user_username);
			pStatement.setString(2, user_password);
			pStatement.setString(3, user_name);
			pStatement.setString(4, user_email);
			pStatement.setString(5, user_status);
			pStatement.setString(6, id);
			
			int qExec = pStatement.executeUpdate();
			if(qExec!=0){
				out.println("Update berhasil");
			}
			
			connection.close();
			pStatement.close();
		}

%>
