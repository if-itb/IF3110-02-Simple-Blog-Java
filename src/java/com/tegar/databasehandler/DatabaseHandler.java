package com.tegar.databasehandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHandler
{
	public Connection conn;
	public DatabaseHandler()
	{
		try
		{
            String url = "jdbc:mysql://localhost:3306/simpleblog_withjava";
            String user = "root";
            String password = "";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(url, user, password);
		} 
		catch (SQLException ex) 
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public ResultSet GetPostFromDatabase()
	{
		Statement stmt = null;
		ResultSet rs = null;
		List<String> Posts = new ArrayList<String>();
		try 
		{
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT konten FROM post");
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally 
		{
		    if (rs != null) 
		    {
		        try 
		        {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) 
		    {
		        try 
		        {
		        	stmt.close();
		        } 
		        catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		return rs;
	}
}