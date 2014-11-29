<%-- 
    Document   : insertcomment
    Created on : Nov 23, 2014, 1:48:28 AM
    Author     : Rakhmatullah Yoga S
--%>

<%@page import="source.Komentar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    int idPost = Integer.parseInt(request.getParameter("id"));
    String nama = request.getParameter("nama");
    String email = request.getParameter("email");
    String komentar = request.getParameter("komentar");
    String sqlQuery = "INSERT INTO komentar (ID, Nama, Email, Komentar) VALUES ('"+ idPost + "', '" + nama + "', '" + email + "', '" + komentar + "')";
    Komentar.AddComment(idPost, nama, email, komentar);
    //out.println(Komentar.LoadComment(idPost));
%>