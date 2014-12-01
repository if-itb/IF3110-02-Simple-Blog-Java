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
                        <td>Email</td>
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
                    String sql=("SELECT user_id, nama, password, role, email FROM user");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);
                    
                    while (rs.next()){
                        String x = rs.getString("nama");
                        String y = rs.getString("password");
                        String email = rs.getString("email");
                        int z = rs.getInt("role");
                        int user_id = rs.getInt("user_id"); 
                    %>
                    
                    <tr>
                        <td><% out.println(x); %></td>
                        <td><% out.println(y); %></td>
                        <td><% out.println(z); %></td>
                        <td><% out.println(email); %></td>
                        
                        <td> <form type ="button" action = "UpdateForm.jsp?user_id=<%=user_id%>" method ="post"> <button type="submit" value="Submit"> Update </button></form></td>
                        <td> <form type ="button" action = "delete.jsp?user_id=<%=user_id%>" method ="post"> <button type="submit" value="Submit"> Delete </button></form></td>
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
        <form type ="button" action = "AddForm.jsp" method ="post"> <button type="submit" value="Submit"> Add User </button></form>
        <br>
        <br>
        <a href="home-admin.jsp">Back to main page</a>
        <script>
            function delete(){
                <%
                    
                %>
            }
        </script>
    </body> 
</html>