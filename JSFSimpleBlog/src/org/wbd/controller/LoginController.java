package org.wbd.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.Map;

import org.wbd.model.User;
import org.wbd.helper.DatabaseHelper;

public class LoginController {
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	private User user;
	
	public LoginController() {
		user = new User();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String doLogin() {
		//get user data from database using helper
		//validate user
		DatabaseHelper helper = DatabaseHelper.getInstance();
		User user = helper.getUserData(this.user.getUsername(), this.user.getPassword());
		
		if(user==null) {
			return "failed";
		}
		
		this.user = user;
		
		if(getCookieValue(USER_NAME)==null) {
			addCookie(USER_NAME, this.user.getUsername());
			addCookie(PASSWORD, this.user.getPassword());
			addCookie(ROLE, this.user.getRole());
		}
		
		return "success";
	}
	
	public String doLogout() {
		// remove cookie on logout
		removeCookie(USER_NAME);
		removeCookie(PASSWORD);
		removeCookie(ROLE);
		return "logged_out";
	}
	
	public String checkCookies() {
		//check cookies
		String username = getCookieValue(USER_NAME);
		if(username==null) {
			return "no_cookies";
		} else { //if cookie exist, login automatically
			String password = getCookieValue(PASSWORD);
			String role = getCookieValue(ROLE);
			
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(role);
			return "success";
		}
	}
	
	private String getCookieValue(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> cookies = context.getExternalContext().getRequestCookieMap();
		Cookie cookie = (Cookie) cookies.get(name);
		if(cookie == null) {
			return null;
		}
		return cookie.getValue();
	}
	
	private void addCookie(String name, String value) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse res = (HttpServletResponse)
				context.getExternalContext().getResponse();
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(2592000); // 1 month
		res.addCookie(cookie);
	}
	
	private void removeCookie(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> cookies = context.getExternalContext().getRequestCookieMap();
		Cookie cookie = (Cookie) cookies.get(name);
		if(cookie == null) return;
		cookie.setMaxAge(0);
	}
	
}
