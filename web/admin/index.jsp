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
                out.println("Welcome " + cookie.getUsername() + " as an "+ cookie.getRole());
            }
            else
            {
                response.sendRedirect("../login/index.html");
            }
            
        %>
        
        <form action="user_management.jsp" method="POST" id="tombol">
            <input type="submit" value="User Management">
        </form>
        <form action="post_management.jsp" method="POST" id="tombol">
            <input type="submit" value="Post Management">
        </form>
        
        <form action="../LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form>
        
        <a href="../index.jsp"> Main Page </a>
    </body>
</html>
