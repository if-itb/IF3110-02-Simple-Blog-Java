/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.Serializable;
import model.database.MySQL;
import model.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author christangga
 */
@ManagedBean(name = "usersController", eager = true)
@SessionScoped
public class UsersController implements Serializable {

	private int active_id;
	private String active_username;
	private String active_password;
	private String active_role;
	
	/**
	 * Creates a new instance of UsersController
	 */
	public UsersController() {
	}

	public int getActive_id() {
		return active_id;
	}

	public void setActive_id(int active_id) {
		this.active_id = active_id;
	}

	public String getActive_username() {
		return active_username;
	}

	public void setActive_username(String active_username) {
		this.active_username = active_username;
	}

	public String getActive_password() {
		return active_password;
	}

	public void setActive_password(String active_password) {
		this.active_password = active_password;
	}

	public String getActive_role() {
		return active_role;
	}

	public void setActive_role(String active_role) {
		this.active_role = active_role;
	}

	public void createUser(String username, String password, String role) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.createUser(username, password, role);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/user/index.xhtml");
	}

	public User getUser(int id) {
		MySQL mysql = new MySQL();
		
		return mysql.getUser(id);
	}

	public List<User> getAllUsers() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllUsers();
	}

	public void updateUser(int id, String username, String password, String role) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.updateUser(id, username, password, role);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/user/index.xhtml");
	}

	public void deleteUser(int id) {
		MySQL mysql = new MySQL();
		
		mysql.deleteUser(id);
	}

	public void viewUser(int id) throws IOException {
		active_id = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/user/view.xhtml");
	}
	
	public void editUser(int id, String username, String password, String role) throws IOException {
		active_id = id;
		active_username = username;
		active_password = password;
		active_role = role;
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/user/edit.xhtml");
	}
	
	public void login(String username, String password) throws IOException {
		MySQL mysql = new MySQL();
		List<User> users = mysql.getAllUsers();
		User user = null;
		
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}
		
		if (user != null) {
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("username", user.getUsername(), null);
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("role", user.getRole(), null);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/index.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or password is invalid"));
		}
	}

	public void logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.err.println(FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("username").toString());
		FacesContext.getCurrentInstance().getExternalContext().redirect("/user/login.xhtml");
	}
	
}
/*
reading cookie
Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance()
   .getExternalContext()
   .getRequestCookieMap();
*/
