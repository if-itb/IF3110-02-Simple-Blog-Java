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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <%
            int IdPost = Integer.valueOf(request.getParameter("var"));
            out.println(Komentar.LoadComment(IdPost));
        %>
    </body>
</html>
