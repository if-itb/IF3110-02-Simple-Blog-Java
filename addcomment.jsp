<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<%@ page import="com.mysql.jdbc.Driver, java.sql.PreparedStatement, java.sql.DriverManager, java.util.Random" %><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>The Simplest Blog | Add Comment</title>
</head>
    <body>
        <f:view>
            <%
                String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
                String user = "root";
                String pwd = "asdasd123";
                Connection con;
                PreparedStatement ps;
                
                try {
                    String nama = request.getParameter("Nama");
                    String waktu = request.getParameter("waktu");
                    String id_post = request.getParameter("id_post");
                    String komentar = request.getParameter("Komentar");
                    String email = request.getParameter("Email");
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    int updateQuery = 0;
                    con = DriverManager.getConnection(host, user, pwd);
                    if (nama != null && waktu != null && id_post != null && komentar != null && email != null) {
                        ps = con.prepareStatement("INSERT INTO `comment` (`nama`, `email`, `komentar`, `waktu`, `id_post`) VALUES (?,?,?,?,?)");
                        ps.setString(1, nama);
                        ps.setString(2, email);
                        ps.setString(3, komentar);
                        ps.setString(4, waktu);
                        ps.setString(5, id_post);
                        updateQuery = ps.executeUpdate();
                    }
                    out.print("<li class=\"art-list-item\">\n"+
                    "<div class=\"art-list-item-title-and-time\">\n"+
                    "<h2 class=\"art-list-title\"><a href=\"post.html\">"+ nama +"</a></h2>\n"+
                    "<div class=\"art-list-time\">"+ waktu +"</div>\n"+
                    "</div>\n"+
                    "<p>" + komentar + "</p>\n"+
                    "</li>");
                } catch (Exception e) {
                    out.println("Error" + e);
                }
            %>
        </f:view>
    </body>
</html>

