<%-- 
    Document   : recovPost
    Created on : Nov 25, 2014, 10:16:59 PM
    Author     : adwisatya
--%>
<%@page import = "Post.Post" %>
<%
	String id	=	 request.getParameter("id");
	Post.RecovPost(id);
	response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../trash.jsp");
%>
