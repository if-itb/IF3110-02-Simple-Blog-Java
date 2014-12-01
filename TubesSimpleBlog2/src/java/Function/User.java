/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anggi
 */
@ManagedBean(name = "user", eager = true)
@ViewScoped
public class User {
//	@ManagedProperty(value="#{param.iduser}")
	private int uidToDelete;
	private int uidToUpdate;
	private String username;
	private String password;
	private String role;
	private int userID;
        private String email;
        
        public String getEmail(){
            return email;}
	public void setEmail(String e){
            this.email=e;}
	public int getUidToUpdate(){
		return uidToUpdate;
	}
	public int getUidToDelete(){
		return uidToDelete;
	}
	public int getUserID(){
		return userID;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getRole(){
		return role;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setRole(String role){
		this.role = role;
	}
	public void setUserID(int userID){
		this.userID = userID;
	}
	public void setUidToDelete(int id){
		this.uidToDelete = id;
	}
	public void setUidToUpdate(int id){
		this.uidToUpdate = id;
	}
	
	public String updateUser(){
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String passWord = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,passWord);
		   String insertToDB = "update user set username = ?, password = ?, role = ?, email = ? where username_id = ?";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   System.out.println(this.username+" "+this.password+" "+this.role+" "+this.userID);
		   preparedStatement.setString(1, this.username);
		   preparedStatement.setString(2, this.password);
		   preparedStatement.setString(3, this.role);
                   preparedStatement.setString(4, this.email);
                   preparedStatement.setInt(5, userID);
		   preparedStatement.executeUpdate();
		   conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
		return "usermanagement?faces-redirect=true";
	}
	
	public String deleteUser(int id){
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String pass = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,pass);
		   String insertToDB = "delete from user where username_id = ?";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   preparedStatement.setInt(1,id);
		   preparedStatement.executeUpdate();
		   conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
		 return "usermanagement?faces-redirect=true";
    }
	
	public String createUser(){
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String pass = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,pass);
		   String insertToDB = "insert into user (`username`, `password`, `role`, `email`) value (?,?,?,?)";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   preparedStatement.setString(1, this.username);
		   preparedStatement.setString(2, this.password);
		   preparedStatement.setString(3, this.role);
		   preparedStatement.setString(4, this.email);
		   preparedStatement.executeUpdate();
		   conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
		return "usermanagement?faces-redirect=true";
	}
	
	public void showUser(int id){
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String pass = "";
		try {
		   Class.forName(driver).newInstance();
		   Connection conn = DriverManager.getConnection(url,userName,pass);
		   Statement st = conn.createStatement();
		   ResultSet res= st.executeQuery("Select * from user where username_id = "+id);

		   if(res.next()){
			   username=res.getString("username");
			   role = res.getString("role");
			   userID = id;
                           email = res.getString("email");
		   }
		   conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
	}
}
