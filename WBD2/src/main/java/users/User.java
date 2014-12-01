package users;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mysql.SQLStatements;

@ManagedBean(name = "user", eager = true) 
@RequestScoped
public class User {
	private int Id;
	private String Name;
	private String Pass;
	private String Email;
	private String Auth;
	
	public User(){
		setId(0);
		setName("");
		setPass("");
		setEmail("");
		setAuth("Guest");
	}
	
	public User(int I, String N, String P, String E, String A){
		setId(I);
		setName(N);
		setPass(P);
		setEmail(E);
		setAuth(A);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAuth() {
		return Auth;
	}

	public void setAuth(String auth) {
		Auth = auth;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	public void newUser(){
		User L = new User();
		L.setName(Name);
		L.setPass(Pass);
		L.setEmail(Email);
		L.setAuth(Auth);
		SQLStatements s = new SQLStatements();
		try {
			s.AddUser(L);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
