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

	private int saved_id;
	private String saved_email;
	private String saved_username;
	private String saved_password;
	private String saved_role;
	
	/**
	 * Creates a new instance of UsersController
	 */
	public UsersController() {
	}

	public int getSaved_id() {
		return saved_id;
	}

	public void setSaved_id(int saved_id) {
		this.saved_id = saved_id;
	}

	public String getSaved_email() {
		return saved_email;
	}

	public void setSaved_email(String saved_email) {
		this.saved_email = saved_email;
	}

	public String getSaved_username() {
		return saved_username;
	}

	public void setSaved_username(String saved_username) {
		this.saved_username = saved_username;
	}

	public String getSaved_password() {
		return saved_password;
	}

	public void setSaved_password(String saved_password) {
		this.saved_password = saved_password;
	}

	public String getSaved_role() {
		return saved_role;
	}

	public void setSaved_role(String saved_role) {
		this.saved_role = saved_role;
	}

	public String getLoginEmail() {
		String emailCookie = getCookieValue("email");
		if (emailCookie != null) {
			return emailCookie;
		}
		
		return "";
	}
	
	public String getLoginUsername() {
		String userCookie = getCookieValue("username");
		if (userCookie != null) {
			return userCookie;
		}
		
		return "guest";
	}
	
	public boolean isAdmin() {
		String roleCookie = getCookieValue("role");
		if (roleCookie != null) {
			return roleCookie.equals("admin");
		}
		
		return false;
	}
	
	public boolean isEditor() {
		String roleCookie = getCookieValue("role");
		if (roleCookie != null) {
			return roleCookie.equals("editor");
		}
		
		return false;
	}
	
	public boolean isOwner() {
		String roleCookie = getCookieValue("role");
		if (roleCookie != null) {
			return roleCookie.equals("owner");
		}
		
		return false;
	}
	
	public boolean isLogin() {
		String roleCookie = getCookieValue("role");
		return roleCookie != null;
	}
	
	public void createUser(String email, String username, String password, String role) throws IOException {
		MySQL mysql = new MySQL();
		
		if (mysql.createUser(email, username, password, role)) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("view_user.xhtml");
                } else {
                    
                }
	}

	public User getUser(int id) {
		MySQL mysql = new MySQL();
		
		return mysql.getUser(id);
	}

	public List<User> getAllUsers() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllUsers();
	}

	public void updateUser(int id, String email, String username, String password, String role) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.updateUser(id, email, username, password, role);
		FacesContext.getCurrentInstance().getExternalContext().redirect("view_user.xhtml");
	}

	public void deleteUser(int id) {
		MySQL mysql = new MySQL();
		
		mysql.deleteUser(id);
	}

	public void viewUser(int id) throws IOException {
		saved_id = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("view_user.xhtml");
	}
	
	public void editUser(int id, String email, String username, String password, String role) throws IOException {
		saved_id = id;
		saved_email = email;
		saved_username = username;
		saved_password = password;
		saved_role = role;
		FacesContext.getCurrentInstance().getExternalContext().redirect("edit_user.xhtml");
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
			Cookie email_cookie = new Cookie("email", user.getEmail());
			Cookie username_cookie = new Cookie("username", user.getUsername());
			Cookie role_cookie = new Cookie("role",user.getRole());
			email_cookie.setMaxAge(30*60);
			email_cookie.setPath("/");
			username_cookie.setMaxAge(30*60);
			username_cookie.setPath("/");
			role_cookie.setMaxAge(30*60);
			role_cookie.setPath("/");
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.addCookie(email_cookie);
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
		Cookie email_cookie = new Cookie("email","");
		Cookie username_cookie = new Cookie("username","");
		Cookie role_cookie = new Cookie("role","");
		email_cookie.setMaxAge(0);
		email_cookie.setPath("/");
		username_cookie.setMaxAge(0);
		username_cookie.setPath("/");
		role_cookie.setMaxAge(0);
		role_cookie.setPath("/");
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setContentType("text/html");
		response.addCookie(email_cookie);
		response.addCookie(username_cookie);
		response.addCookie(role_cookie);
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

	public String getCookieValue(String name) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;

		Cookie[] userCookies = request.getCookies();
		if (userCookies != null && userCookies.length > 0 ) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(name)) {
					cookie = userCookies[i];
					return cookie.getValue();
				}
			}
		}

		return null;
	}

}
