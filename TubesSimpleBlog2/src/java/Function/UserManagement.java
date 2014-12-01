/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Anggi
 */
@ManagedBean(name = "userManagement", eager = true)
@RequestScoped
public class UserManagement {
	private ArrayList<User> arrUser;
	
	public UserManagement(){
		arrUser = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,password);
			Statement st = conn.createStatement();
			ResultSet res= st.executeQuery("Select * from user");
			User user = null;
			while(res.next()){ 
				user = new User();
				user.setUserID(res.getInt("username_id"));
				user.setUsername(res.getString("username"));
				user.setPassword(res.getString("password"));                
				user.setRole(res.getString("role"));                
				user.setEmail(res.getString("email"));
                                arrUser.add(user);
			}
			conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
	}
	public ArrayList<User> getArrUser(){
		return arrUser;
	}
	
	public void Read(){
	
	}
	
}
