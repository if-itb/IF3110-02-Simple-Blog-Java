<%-- 
    Document   : delete
    Created on : Nov 25, 2014, 3:58:23 PM
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
                    String sql=("DELETE FROM user WHERE user_id='" + user_id + "'");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.executeUpdate(sql);
                    
                }
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
                System.out.println("DELETE FROM user WHERE user_id=" + user_id);
                
                response.sendRedirect("ManagementUser.jsp");
        %>
    </body>
</html>
