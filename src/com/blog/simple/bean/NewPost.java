package com.blog.simple.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ramandika
 */
@ManagedBean(name="newPost")
@RequestScoped
public class NewPost {
    String title;
    Statement stmt;  
    ResultSet result;
    private String tanggal;
    String content;
    Connection con;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }  

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public String add() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog","root","");  
        } catch (ClassNotFoundException e) {  
            System.out.println("Class Not Found");  
        } catch (SQLException e) {  
            System.out.println("Unable to connect");  
        }    
            try {
                System.out.println("Ready to do Query");
                String query = "INSERT INTO post (judul, tanggal, konten, status) VALUES (?,?,?, 'unpublished')";
                PreparedStatement ps = con.prepareStatement(query);
                
                Object values[] = {
	                title,
	                tanggal,
                    content
                };
                for(int i = 0; i < values.length; i++) 
                    ps.setObject(i+1, values[i]);
                
                int affectedRow = ps.executeUpdate();  
                if(affectedRow == 0)
                    throw new SQLException("Data insertion failed");
                  
            } catch (SQLException e) {
                throw e;
            }
       return "index.xhtml";
    }

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
    
    
}