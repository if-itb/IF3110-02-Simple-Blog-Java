<%-- 
    Document   : logout
    Created on : Nov 24, 2014, 2:24:33 PM
    Author     : Teofebano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <% 
            session.removeAttribute("username"); 
            session.removeAttribute("password"); 
            session.invalidate(); 
            response.sendRedirect("index.jsp");
        %> 
        

    </body>
</html>
