<%-- 
    Document   : EditPost
    Created on : Nov 23, 2014, 10:12:56 PM
    Author     : Asep Saepudin
--%>

<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="wbd.tubesII.Post"%>
<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");
            if (currentUser == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Login.jsp"); 
            }
            
            Post editedPost = (Post)request.getSession().getAttribute("editedPost");
            if (editedPost == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "UserLogged.jsp"); 
            }
        %>
        <form action="UpdatePost" method="POST">
            Judul
            <input type="text" name="judul" id="judul" value="<%= editedPost.getJudul() %>"/><br>		
            Tanggal
            <%
                Date date = editedPost.getTanggal();
                Format formatter = new SimpleDateFormat("MM/dd/yyyy");
            %>
            <input type="text" id="tanggal" placeholder="MM/dd/yyyy" name="tanggal" value="<%= formatter.format(date) %>"/><br>
            Konten
            <textarea name="konten" id="konten" rows="20" cols="20"><%= editedPost.getKonten() %></textarea>            
            <input type="hidden" value="<%= editedPost.getId() %>" name="id">
            <input type="hidden" value="<%= editedPost.getStatus() %>" name="status">
            <input type="submit" value="submit" onclick="return validate()">		
        </form>
    </body>
    <script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
