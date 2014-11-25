<%-- 
    Document   : ManagementUser
    Created on : Nov 24, 2014, 5:24:10 PM
    Author     : Teofebano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.Connection"%>
<%@ page language="java" import="java.sql.PreparedStatement"%>
<%@ page language="java" import="java.sql.ResultSet"%>
<%@ page language="java" import="java.sql.SQLException"%>
<%@ page language="java" import="java.sql.DriverManager"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.String"%>

<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Management User</title> 
    </head> 
    <body> 
       
            <table border="2">
                    <tr>
                        <td>Name</td>
                        <td>Password</td>
                        <td>Role</td>
                        <td>Update</td>
                        <td>Delete</td>
                    </tr>
        <% 
                       
            Connection con = null;
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

                // Retrieve password and role
		try 
		{
                    String sql=("SELECT nama, password, role FROM user");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next()){
                        String x = rs.getString("nama");
                        String y = rs.getString("password");
                        int z = rs.getInt("role");
                    %>
                    
                    <tr>
                        <td><% out.println(x); %></td>
                        <td><% out.println(y); %></td>
                        <td><% out.println(z); %></td>
                        <td> <form action = "home.jsp" method ="post"> <input type="submit" value="Submit">  </form></td>
                        <td> <form action = "home.jsp" method ="post"> <input type="submit" value="Submit">  </form></td>
                    </tr>
                    
                    <%
                    }
            
                    rs.close();
                    con.close();
                    %>
            </table>
            
                    <%
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}                
        %>    
        <br>
        <a href="home-admin.jsp">Back to main page</a>
    </body> 
</html>