<%-- 
    Document   : ViewPost
    Created on : Nov 23, 2014, 11:27:55 PM
    Author     : Asus
--%>

<%@page import="wbd.tubesII.Post"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>    
    <%                        
        Post post = (Post)request.getSession().getAttribute("post");
        if (post == null) {            
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "PublishedPosts"); 
        }
    %>
    <body>
        
            Judul : <br/>
            <%= post.getJudul() %> <br/> <br/>
            Tanggal : <br/>
            <%= post.getTanggal().toString() %> <br/> <br/>
            Konten <br/>
            <%= post.getKonten() %> <br/> <br/>
    </body>
    <script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
