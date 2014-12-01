<%-- 
    Document   : trashPost
    Created on : Dec 1, 2014, 9:05:54 PM
    Author     : adwisatya
--%>

<%@ page pageEncoding="UTF-8" %>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%
	String id	=	 request.getParameter("id");
	
	try{
		Connection connection = null;
	
		PreparedStatement pStatement = null;
		Class.forName(xDRIVER);
		connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
		String Query = "UPDATE post SET Status=? WHERE ID=?";
		pStatement = connection.prepareStatement(Query);
		pStatement.setString(1, "3");
		pStatement.setString(2, id);

		int qExec = pStatement.executeUpdate();
		if(qExec!=0){
			out.println("Update berhasil");
		}
		connection.close();
		pStatement.close();
	}catch (SQLException ex){
		throw ex;
	}
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "index.jsp");
%>
