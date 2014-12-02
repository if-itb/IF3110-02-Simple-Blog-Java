<%-- 
    Document   : Comment
    Created on : Nov 28, 2014, 6:43:07 PM
    Author     : SPM
--%>

<%@page import="data.Connector"%>
<%@page import="data.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*" %>
        <%
	    //?post_ID=" + post_ID + "&author=" + author + "&email=" + email + "&comment=" + comment
	    //`comment-content`V, `comment-post-id`V,`comment-email`, `comment-user-id`V
	    Connector C = new Connector();
	    int i = Integer.parseInt(request.getParameter("Userid"));
	    	    
	    Comment inputComment = new Comment(); 
	    inputComment.setCommentPostID(i);
	    if ((request.getParameter("Nama")).equalsIgnoreCase("guest")) {
		//inputComment.setUserID(0); // default for guest
		//inputComment.setEmail(mail);
		C.setComment(request.getParameter("Komentar"), i, request.getParameter("email"), 0);
	    }
	    else {
		//inputComment.setUserID(1); //dummy
		C.setComment(request.getParameter("Komentar"), i, C.getEmailByID(1), 1);
	    }
	    
	    
	    //String go_to = new String("post.jsp?id=" + i);
	    //response.setStatus(response.SC_MOVED_TEMPORARILY);
	    //response.setHeader("Location", go_to);
	%>
