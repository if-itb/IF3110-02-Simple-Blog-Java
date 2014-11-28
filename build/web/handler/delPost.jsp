<%-- 
    Document   : delPost
    Created on : Nov 25, 2014, 9:50:13 PM
    Author     : adwisatya
--%>

<%@page import = "java.Post.Post" %>
<%@page import= "java.User.User" %>
<%@page import= "java.Post.PostBean" %>

<%
	String ID	=	request.getParameter("id");

	//PostBean pBean	=	 new PostBean();
	//pBean.EditPost(ID, Judul, Tanggal, Konten);
	Post.DeletePost(ID);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../index.jsp");

%>
