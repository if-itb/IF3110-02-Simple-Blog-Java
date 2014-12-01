package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public Test() {
		
	}
	
	public static void main(String[] args) throws Exception{
		Statement stmt;
		ResultSet rs;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db", "root", "");
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM post");
		while(rs.next()){
			System.out.println(rs.getString("tittle"));
		}
	}
}
