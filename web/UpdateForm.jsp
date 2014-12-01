<%-- 
    Document   : UpdateForm
    Created on : Nov 25, 2014, 4:55:12 PM
    Author     : Teofebano
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         Connection con = null;
         String user_id= request.getParameter("user_id");
                // Connect to DB
                try {
                    //Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/simpleblog_withjava";
                    String user = "root";
                    String passwordSQL = "";

                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                    con = DriverManager.getConnection(url, user, passwordSQL);
                }
                catch(SQLException ex){
                System.out.println(ex);
                }
                
                try 
		{
                    String sql=("SELECT nama, password, role,email FROM user WHERE user_id='" +user_id+ "'");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);
                    while (rs.next())
                    {
                    String x = rs.getString("nama");
                    String y = rs.getString("password");
                    int z = rs.getInt("role");
                    String w = rs.getString("email");
                    %>
                   <h2>Update Details</h2> 
                    <form action="update.jsp?user_id=<%=user_id%>" method="post">
                    <br/>Username   :<input type="text" name="username" value="<%=x%>"> 
                    <br/>Password   :<input type="text" name="password" value="<%=y%>">
                    <br/>Role   :<input type="text" name="role" value="<%=z%>">
                    <br/>Email   :<input type="text" name="email" value="<%=w%>">
                    <br/><br><input type="submit" value="Submit"> 
                    </form>
                    <%
                    
                    }
                    
                }
                catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}  
        %>
                    
    </body>
</html>
