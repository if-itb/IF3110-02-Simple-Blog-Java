/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author ASUS
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ASUS
 */

public class viewPost {
    private int postid;
    public String judul;
    public String konten;
    public Date tanggal;
    
    public int getPostid(){
    return postid;}
    
    public String getJudul(){
    return judul;}
    
    public String getKonten(){
    return konten;}
    
    public Date getTanggal(){
    return tanggal;}

    public void connectDb(){
        String url = "jdbc:mysql://localhost:3306/datapost";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root"; 
	String password = "";
	 try {
		  Class.forName(driver).newInstance();
		  Connection conn = DriverManager.getConnection(url,userName,password);
		  Statement st = conn.createStatement();
		/*  ResultSet res = st.executeQuery("SELECT * FROM  post");
		  while (res.next()) {
		  int id = res.getInt("id");
                  String msg = res.getString("msg");
		  System.out.println(id + "\t" + msg);
		  }
		  int val = st.executeUpdate("INSERT into event VALUES("+1+","+"'Easy'"+")");
		  if(val==1)
			  System.out.print("Successfully inserted value");*/
                  ResultSet res= st.executeQuery("Select * from posts ");
 //                 System.out.println(res.getString(2));
                  res.next();
                  if (res.next()){
                 /* judul=res.getString(2);
                  tanggal = res.getDate(3);
                  konten = res.getString(4); */
                      System.out.println(res.getString(4));
                      System.out.println(res.getDate(3));
                      System.out.println(res.getString(2));
                       
                        
                  }
                  
                  conn.close();
		  } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		  }
    }
 }
