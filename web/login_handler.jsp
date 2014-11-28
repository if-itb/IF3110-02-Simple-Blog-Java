<%-- 
    Document   : login_handler
    Created on : Nov 25, 2014, 10:07:57 PM
    Author     : USER
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<%
   Cookie Cusr = new Cookie(request.getParameter("username"),request.getParameter("password"));
   //Cookie Cpass = new Cookie("pass",request.getParameter("password"));
   
   Cusr.setMaxAge(60);
   //Cpass.setMaxAge(30000000);
   
   response.addCookie(Cusr);
   //response.addCookie(Cpass);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <sql:setDataSource var="Tubes2WBD" 
                           driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/"
                           user="root"
                           password=""/>
      
        <% String usr = request.getParameter("username");
           String pass = request.getParameter("password");
           String Query = "SELECT * FROM Tubes2WBD.users WHERE username=\""+usr+"\" AND password=\""+pass+"\""; 
        %>
        
        
        <sql:query dataSource="${Tubes2WBD}" var="result">
            <%= Query %>
        </sql:query>
            
        <c:choose>
                <c:when test="${result.rowCount gt 0}">
                    <c:redirect url="index.jsp"/>
                </c:when>
                <c:otherwise>
                    <form method="post" action="login.jsp" id="errorform">
                        <input type="hidden" name="errormessage" id="errormessage" value="errormessage">
                    </form>
                    <script>
                        document.getElementById("errorform").submit();
                    </script>--%>
                </c:otherwise>
        </c:choose>
   
    </body>
</html>
