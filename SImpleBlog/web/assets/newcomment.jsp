<%-- 
    Document   : newcomment
    Created on : Nov 25, 2014, 11:30:16 AM
    Author     : Sakurai
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<% 
    if (request.getParameter("ID") != null) {
        Connection connection = null; 
        Statement stmt = null;
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/blog";
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            connection = DriverManager.getConnection(connectionURL, "root", "");
            stmt = connection.createStatement();
            int ID = Integer.parseInt(request.getParameter("ID"));
            String Name = request.getParameter("Name");
            String Comment = request.getParameter("Comment");
            String Email = request.getParameter("Email");
            String query = "INSERT INTO comment (PostID, Nama, Email, Komentar, Tanggal) VALUES ('" + ID + "', '" + Name + "', '" + Email + "', '" + Comment + "', NOW())";
            stmt.execute(query);
            connection.close();
        }catch(Exception ex){
            System.out.println("cannot connect to database");
        }
    } 
%>