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
        <%= post.getKonten() %> <br/> <br/> <br/>

        <%
            User currentUser = (User)request.getSession().getAttribute("currentUser");              
        %>
        <form method="post" action="#">
            <input type="hidden" name="id" id="id" value="<%= post.getId()  %>">            
            <label for="Nama">Nama:</label>
            <% if (currentUser == null) { %>
            <input type="text" name="nama" id="nama">
            <% } else  {%>
            <input type="text" name="nama" id="nama" disabled="true" value="<%= currentUser.getName() %>">
            <% }%>
            <br>
            <label for="Email">Email:</label>
            <% if (currentUser == null) { %>
            <input type="text" name="email" id="email" onkeyup="validateEmail()" onchange="validateEmail()">
            <% } else  {%>
            <input type="text" name="email" id="email" disabled="true" onkeyup="validateEmail()" value="<%= currentUser.getEmail() %>">
            <% }%>
            <br>
            <label for="Komentar">Komentar:</label><br>            
            <textarea name="komentar" rows="20" cols="20" id="komentar"></textarea>

            <input type="submit" name="submitKomentar" id="submitKomentar" value="Kirim" class="submit-button" id="SubmitKomentar" onclick="sendComment(); return false	">
        </form>
        <br>
        <br>
        <div id="komentar-placeholder">
				
	</div>
    </body>
    <script type="text/javascript" src="assets/js/myjs.js" ></script>
</html>
