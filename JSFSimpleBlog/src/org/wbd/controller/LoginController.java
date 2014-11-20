package org.wbd.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.Map;

public class LoginController {
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	//TODO : add attributes
	
	public LoginController() {
		//TODO : initialize attributes
	}
	
	public String doLogin() {
		//TODO : code to do login
		return "success";
	}
	
	public void doLogout() {
		//TODO : code to do logout
	}
	
	public void checkCookies() {
		//TODO : code to check cookies
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
	
}
