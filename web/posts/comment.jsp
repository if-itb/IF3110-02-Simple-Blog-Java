<%-- 
    Document   : comment
    Created on : Nov 23, 2014, 1:26:36 AM
    Author     : Rakhmatullah Yoga S
--%>

<%@page import="source.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    int IdPost = Integer.valueOf(request.getParameter("id"));
    Post post = new Post();
    post.setAtribut(IdPost);
    out.println(post.LoadComment(IdPost));
%>
