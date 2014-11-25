<%-- 
    Document   : comment
    Created on : Nov 23, 2014, 1:26:36 AM
    Author     : Rakhmatullah Yoga S
--%>

<%@page import="source.Komentar"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="source.KoneksiDatabase"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <%
            int IdPost = Integer.valueOf(request.getParameter("id"));
            System.out.println(IdPost);
            out.println(Komentar.LoadComment(IdPost));
        %>
