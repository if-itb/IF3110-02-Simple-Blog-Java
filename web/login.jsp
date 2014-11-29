<%-- 
    Document   : login
    Created on : Nov 25, 2014, 8:04:26 PM
    Author     : USER
--%>

<%@page import="org.apache.tomcat.util.http.Cookies"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<%  
    Cookie cookie = null;
    String usrC=null;
    String passC=null;
    int typeC=4;
    Cookie[] cookies = null;
    cookies = request.getCookies();
    for (Cookie c:cookies){
    	if (c.getName().equals("LogName")){
    		usrC=c.getValue();
    		for (Cookie c2:cookies){
    		    if (c2.getName().equals("LogType")){
   					typeC=Integer.parseInt(c2.getValue());
   				}
    		}
    	}
    }
    if (typeC==1){
    	out.println("Selamat Datang, Owner "+usrC);
    } else if (typeC==2){
    	out.println("Selamat Datang, Editor "+usrC);
    } else if (typeC==3){
    	out.println("Selamat Datang, Admin "+usrC);
    } else if (typeC==4){
    	out.println("Selamat Datang, Guest");
    }
    /*if(cookies!=null)
    {
        //out.println("Yay, cookies! "+cookies.length);
        /*for(int i=0; i<cookies.length; i++)
        {
            cookie = cookies[i];
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            //out.println(cookie.getName()+" "+cookie.getValue()+" <br/>");
        }*//*
        cookie = cookies[cookies.length-1];
        usrC = cookie.getName();
        passC = cookie.getValue();
        out.println("Selamat datang kembali "+usrC);
        out.println("<br/>bala");
    }*/
    //else out.println("No cookies :(");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%--
            if(usrC!=null && passC!=null)
            {
        --%>
        <%
        if (typeC!=4){
        %>
        <form method="post" action="index.jsp" id="CookiedLogin">
            <input type="hidden" name="username" id="username" value="${usrC}">	
            <input type="hidden" name="password" id="password" value="${passC}">
        </form>
        <script>
            //var s1="Selamat datang kembali";
            //var s2= <%= usrC %>;
            //alert(s2);
            //s1.concat(s2);
            document.getElementById("CookiedLogin").submit();
        </script>
        <% } else {%>
        <h3>Silakan login</h3><br>
        <form method="post" action="login_handler.jsp">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" value="">	
            <br>
            <label for="Password">Password</label>
            <input type="password" name="password" id="password" value="">
            <br>
            <input type="submit" name="submit" value="Login" class="submit-button">
        </form>
        <% if(request.getParameter("errormessage")!=null)
            {
                out.println("Username dan password Anda salah");
            }
        %>

        <% } %>
    </body>
</html>
