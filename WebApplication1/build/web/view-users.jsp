<%-- 
    Document   : view-post.jsp
    Created on : Nov 19, 2014, 2:17:39 AM
    Author     : adwisatya
--%>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Users</title>
    </head>
    <body>
        <%
		try{
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			Class.forName(xDRIVER);
			connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
			statement = connection.createStatement();
			String Data = "select * from user";
			rs = statement.executeQuery(Data);
			
			while(rs.next()){
		%>
				<tr>
				<td><%=rs.getString("nama")%></td>
				<td><%=rs.getString("username")%></td>
				<td><%=rs.getString("email")%></td>
				</tr>		
		<%
			}
			rs.close();
			statement.close();
			connection.close();
		}catch(Exception ex){
			out.println("Can't connect to database");
		}	
		%>
		
    </body>
</html>
