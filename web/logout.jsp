<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<%
    Cookie username = new Cookie("username", "");
    if (username != null)
    {
        username.setMaxAge(0);
        response.addCookie(username);
    }
    
    Cookie role = new Cookie("role", "");
    if (role != null)
    {
        role.setMaxAge(0);
        response.addCookie(role);
    }
    
%>
<c:redirect url="index.xhtml"/>