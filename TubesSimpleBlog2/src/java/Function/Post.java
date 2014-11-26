/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anggi
 */
@ManagedBean(name = "post", eager = true)
@ViewScoped
public class Post {
	//@ManagedProperty(value="#{param.idpost}")
	private int pidToDelete;
	private int IDPost;
	private String title;
	private String content;
	private Date date;
	private String status;
	
	public int getPidToDelete(){
		return pidToDelete;
	}
	public int getIDPost(){
		return IDPost;
	}
	public String getStatus(){
		return status;
	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public void setIDPost(int id){
		this.IDPost = id;
	}
	public void setPidToDelete(int id){
		this.pidToDelete = id;
	}
	    
        public String addPost(){
            String url = "jdbc:mysql://localhost:3306/datapost";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root"; 
            String password = "";
            	try {
                   java.sql.Date sqlDate= new java.sql.Date(date.getTime());
                   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,password);
                   String insertToDB = "insert into posts (`Judul`,`Tanggal`,`Konten`,`Status`) value (?,?,?,?)";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
                   preparedStatement.setString(1, this.title);
                   preparedStatement.setDate(2, sqlDate);
		   preparedStatement.setString(3, this.content);
		   preparedStatement.setString(4, "unpublished");
		   preparedStatement.executeUpdate();
		   conn.close();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                    }
                return "unpublished_post?faces-redirect=true";
        }
        
        public void showPost(int id){
           String url = "jdbc:mysql://localhost:3306/datapost";
	   String driver = "com.mysql.jdbc.Driver";
	   String userName = "root"; 
	   String password = "";
           try{
                Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url,userName,password);
                Statement st = conn.createStatement();
                ResultSet res = st.executeQuery("Select * from posts where pid = " +id);
                
                if(res.next()){
                    title = res.getString("Judul");
                    date = res.getDate("Tanggal");
                    content = res.getString("Konten");
                    IDPost = id;
                }
           }catch (ClassNotFoundException /*| IOException*/ | InstantiationException | IllegalAccessException | SQLException e) {
	   }
		
        }
        
        public String editPost(){
            System.out.println("idedit="+IDPost);
            String url = "jdbc:mysql://localhost:3306/datapost";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root"; 
            String password = "";
            	try {
                   java.sql.Date sqlDate= new java.sql.Date(date.getTime());
                   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,password);
                   String insertToDB = "update posts set Judul =?, Tanggal =? ,Konten=? where PID=?";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
                   preparedStatement.setString(1, this.title);
                   preparedStatement.setDate(2, sqlDate);
		   preparedStatement.setString(3, this.content);
                   preparedStatement.setInt(4,IDPost);
		   preparedStatement.executeUpdate();
		   conn.close();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                    }
                return "index?faces-redirect=true";
        
        }
	public String deletePost(int id){
	   String url = "jdbc:mysql://localhost:3306/datapost";
	   String driver = "com.mysql.jdbc.Driver";
	   String userName = "root"; 
	   String password = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,password);
		   String insertToDB = "delete from posts where PID = ?";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   preparedStatement.setInt(1,id);
		   preparedStatement.executeUpdate();
		   conn.close();
		  // FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	   } catch (ClassNotFoundException /*| IOException*/ | InstantiationException | IllegalAccessException | SQLException e) {
	   }
		return "index?faces-redirect=true";
        }
     public String changeStatus(int id){
            String url = "jdbc:mysql://localhost:3306/datapost";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root"; 
            String password = "";
            try {
                Class.forName(driver).newInstance();
                Connection conn = DriverManager.getConnection(url,userName,password);
                
                  String insertToDB = "update posts set Status='published' where PID=?";
                  PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		  preparedStatement.setInt(1,id);
		  preparedStatement.executeUpdate();
		  conn.close();
             } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                    }
            return "unpublished_post?faces-redirect=true";
     }
}
