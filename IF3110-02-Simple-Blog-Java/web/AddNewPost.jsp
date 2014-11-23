<%-- 
    Document   : AddNewPost
    Created on : Nov 23, 2014, 7:41:49 PM
    Author     : Asep Saepudin
--%>

<%@page import="wbd.tubesII.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>    
    <%                
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if (currentUser == null) {            
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "Login.jsp"); 
        }        
        if (currentUser.getRole().equals("Editor")) {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "UserLogged.jsp"); 
        }
    %>
    <body>
        <form action="AddNewPost" method="POST">
            Judul
            <input type="text" name="judul" id="judul"/><br>		
            Tanggal
            <input type="text" placeholder="MM/dd/yyyy" name="tanggal" id="tanggal"/><br>
            Konten
            <textarea name="konten" rows="20" cols="20" id="konten"></textarea>            
            <input type="submit" value="submit" onclick="return validate()">		
        </form>
    </body>
    <script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
