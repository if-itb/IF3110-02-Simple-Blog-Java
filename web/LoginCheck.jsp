<%-- 
    Document   : LoginCheck
    Created on : Nov 24, 2014, 2:10:26 PM
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
        <title>Login Check</title> 
    </head> 
    <body> 
        <% 
            String username=request.getParameter("username"); 
            String password=request.getParameter("password");
            String passwordS = ""; // dari SQL
            String rol = "";
            int user_id = 0;
            
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
                    String sql=("SELECT user_id, password, role FROM user WHERE nama='"+username+"'");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    
                    String[] pass = null;
                    int rl = 0;
                    int user = 0;
                    while (rs.next()){
                        String em = rs.getString("password");
                        rl = rs.getInt("role");
                        user = rs.getInt("user_id");
                        pass = em.split("\n");
                        for (int i =0; i < pass.length; i++){
                            System.out.println(pass[i]);
                        }
                    }
                    if (pass != null){
                        passwordS = pass[0];
                        
                        switch (rl){
                            case 1: rol = "Owner"; break;
                            case 2: rol = "Editor"; break;
                            case 3: rol = "Admin"; break;
                            default : rol = "Guest"; break;
                        }
                        
                        user_id = user;
                    }
                    
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
            
            if( password.equals(passwordS)) 
            { 
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setAttribute("role",rol);
                session.setAttribute("user_id",user_id);
                response.sendRedirect("cookies.jsp"); 
            } 
            else 
                response.sendRedirect("index.jsp");
                
        %> 
    </body> 
</html>

