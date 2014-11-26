<%-- 
    Document   : index
    Created on : Nov 25, 2014, 3:06:33 PM
    Author     : user
--%>

<%@page import="data.Comment"%>
<%@page import="data.Connector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Simple-Blog</h1>
	
	<%
	    Connector N = new Connector();
	    //out.println(N.setComment("Ini comment hasil dari javah", 1, "feli-c-s@hotmail.com", 1));
	    ArrayList<Post> postlist = new ArrayList<Post>();
	    postlist = N.getPost(1);
	    if (postlist == null) {
		out.println("No posts here, move on~");
	    }
	    else {
		out.println("<ul>");
		for (Post P : postlist){
		    out.println("<li>");
		    out.println(P.getTitle());
		    out.println("<br></br>");
		    out.println(P.getDate().toString());
		    out.println(" | By ");
		    out.println( N.getUsernameByID(P.getAuthorID()) );
		    out.println("<br></br>");
		    out.println(P.getContent().toString());
		    out.println("</li>");
		    
		    ArrayList<Comment> CommentList = new ArrayList<Comment>();
		    CommentList = N.getComment(P.getPostID());
		    if (! CommentList.isEmpty()){
			out.println("<ul>");
			for (Comment C : CommentList){
			    out.println("<li>"); 
			    out.println(C.getDate());
			    out.println("<br></br>");
			    out.println(C.getContent());
			    out.println("<br></br>");
			    out.println("</li>");
			}
			out.println("</ul>");
		    }
		    else {
			out.println(CommentList.isEmpty());
		    }
		    out.println("<br></br>");
		}
		out.println("</ul>");
	    }
	 %>
	    
	 
    </body>
</html>
