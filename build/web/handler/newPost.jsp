<%-- 
    Document   : newPost
    Created on : Nov 25, 2014, 1:01:57 AM
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
	
	String Judul = request.getParameter("judul");
	String Tanggal = request.getParameter("tanggal");
	String Konten = request.getParameter("konten");
	String Owner = request.getParameter("owner");
	String Status	=	 request.getParameter("status");
	PostBean pBean	=	 new PostBean();
	Post.NewPost(Judul,date,Konten,Owner);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../index.jsp");
%>
