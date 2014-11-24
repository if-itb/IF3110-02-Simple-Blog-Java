<%-- 
    Document   : index
    Created on : Nov 23, 2014, 5:39:59 AM
    Author     : akhfa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="source.User"%>
<%@page import="source.CookieHelper"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%  
            CookieHelper cookie = new CookieHelper(request.getCookies());
            if(cookie.thereIsCookie())
            {
                out.println("Welcome " + cookie.getUsername() + "as a "+ cookie.getRole());
            }
            else
            {
                response.sendRedirect("../login/index.html");
            }
            
        %>
        <form action="../LogoutServlet" method="post">
        <input type="submit" value="Logout" >
    </body>
</html>
