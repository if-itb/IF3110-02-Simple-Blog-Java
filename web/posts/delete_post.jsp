<%-- 
    Document   : trash_post
    Created on : Nov 24, 2014, 6:10:01 PM
    Author     : Imburden
--%>

<%@page import="source.CookieHelper"%>
<%@page import="source.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="0; url=../admin/post_management.jsp" />
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="description" content="Deskripsi Blog">
        <meta name="author" content="Judul Blog">

        <!-- Twitter Card -->
        <meta name="twitter:card" content="summary">
        <meta name="twitter:site" content="omfgitsasalmon">
        <meta name="twitter:title" content="Simple Blog">
        <meta name="twitter:description" content="Deskripsi Blog">
        <meta name="twitter:creator" content="Simple Blog">
        <meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">

        <meta property="og:type" content="article">
        <meta property="og:title" content="Simple Blog">
        <meta property="og:description" content="Deskripsi Blog">
        <meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
        <meta property="og:site_name" content="Simple Blog">

        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
        <title>Redirecting... - Not a Simple Blog</title>
    </head>
    <body class="default">
        <% CookieHelper cookie = new CookieHelper(request.getCookies());
           //apabila tidak ada cookie
           if(!cookie.thereIsCookie()) {
               response.sendRedirect("../login/index.html");
           }
         %>
        <% Post post = new Post(); %>
        <% post.deletePost(Integer.valueOf(request.getParameter("id"))); %>
    </body>
</html>
