<%-- 
    Document   : admPublish
    Created on : Nov 25, 2014, 10:16:59 PM
    Author     : adwisatya
--%>
<%@page import = "Post.Post" %>
<%
	String id	=	 request.getParameter("id");
	Post.publish(id);
	response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../admin.jsp");
%>

