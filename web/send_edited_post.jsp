<%-- 
    Document   : send_edited_post
    Created on : Dec 2, 2014, 12:36:18 AM
    Author     : SPM
--%>

<%@page import="data.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    Connector C = new Connector();
    Post P = new Post();
    P.setTitle(request.getParameter("judul")); 
    P.setAuthorID(1); //edit when cookie implemented
    P.setContent(request.getParameter("content"));
    P.setDate(request.getParameter("date"));
    P.setCategory(true);
    P.setPostID(Integer.parseInt(request.getParameter("post_ID")));
    C.resetPost(P);
    
    String go_to = "index.jsp";
    
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", go_to);
%>