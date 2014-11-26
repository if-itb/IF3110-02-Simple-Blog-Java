/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.database.MySQL;

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

	public boolean isAdmin() {
		String roleCookie = getCookie("role");
		if (roleCookie != null) {
			return roleCookie.equals("admin");
		}
		
		return false;
	}
	
	public boolean isEditor() {
		String roleCookie = getCookie("role");
		if (roleCookie != null) {
			return roleCookie.equals("editor");
		}
		
		return false;
	}
	
	public boolean isOwner() {
		String roleCookie = getCookie("role");
		if (roleCookie != null) {
			return roleCookie.equals("owner");
		}
		
		return false;
	}
	
	public void createUser(String username, String password, String role) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.createUser(username, password, role);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/view_user.xhtml");
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
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/view_user.xhtml");
	}

	public void deleteUser(int id) {
		MySQL mysql = new MySQL();
		
		mysql.deleteUser(id);
	}

	public void viewUser(int id) throws IOException {
		active_id = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/view_user.xhtml");
	}
	
	public void editUser(int id, String username, String password, String role) throws IOException {
		active_id = id;
		active_username = username;
		active_password = password;
		active_role = role;
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SimpleBlog/faces/edit_user.xhtml");
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
		
		if (user != null) { //ada user di database, mulai 
			Cookie username_cookie = new Cookie("username", user.getUsername());
			Cookie role_cookie = new Cookie("role",user.getRole());
			username_cookie.setMaxAge(30*60);
			username_cookie.setPath("/");
			role_cookie.setMaxAge(30*60);
			role_cookie.setPath("/");
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.addCookie(username_cookie);
			response.addCookie(role_cookie);
			response.sendRedirect("index.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or password is invalid"));
		}
	}

	public void logout() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie username_cookie = new Cookie("username","");
		Cookie role_cookie = new Cookie("role","");
		username_cookie.setMaxAge(0);
		username_cookie.setPath("/");
		role_cookie.setMaxAge(0);
		role_cookie.setPath("/");
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setContentType("text/html");
		response.addCookie(username_cookie);
		response.addCookie(role_cookie);
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

	public String getCookie(String role) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;

		Cookie[] userCookies = request.getCookies();
		if (userCookies != null && userCookies.length > 0 ) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(role)) {
					cookie = userCookies[i];
					return cookie.getValue();
				}
			}
		}

		return null;
	}

}
