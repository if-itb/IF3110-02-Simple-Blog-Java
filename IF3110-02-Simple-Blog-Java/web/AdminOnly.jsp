<%-- 
    Document   : AdminOnly
    Created on : Nov 23, 2014, 5:10:08 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("currentUser") == null) {            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "Login.jsp"); 
            }
        %>
        
        <h1>
        Error: <%= (String)request.getSession().getAttribute("adminOnly") %>     
        </h1>
    </body>
</html>
