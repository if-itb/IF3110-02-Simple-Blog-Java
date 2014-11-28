<%-- 
    Document   : updatePost.jsp
    Created on : Nov 25, 2014, 9:13:09 PM
    Author     : adwisatya
--%>

<%@page import= "java.User.userPaket"%>
<%@page import = "java.Post.Post" %>
<%@page import= "java.User.User" %>
<%@page import= "java.Post.PostBean" %>
<%@page import= "java.text.SimpleDateFormat" %>
<%@page import= "java.util.Date" %>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String ID	=	request.getParameter("ID");
	String Judul = request.getParameter("judul");
	String Konten = request.getParameter("konten");

	//PostBean pBean	=	 new PostBean();
	//pBean.EditPost(ID, Judul, Tanggal, Konten);
	Post.EditPost(ID, Judul, date, Konten);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../show_post.jsp?id="+ID);

%>
