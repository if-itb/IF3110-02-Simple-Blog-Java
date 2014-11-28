package com.blog.simple.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.blog.simple.SHA512;
import com.blog.simple.db.DAOFactory;
import com.blog.simple.db.UserDAO;
import com.blog.simple.model.User;

@ManagedBean(name = "userManager")
@SessionScoped
public class UserManager implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1998717464534180478L;
	
	private User user;
	private UserDAO userDao = DAOFactory.getInstance("simple-blog").getUserDAO();

	/**
	 * the username
	 */
	private String username;
	
	/**
	 * password in cleartext, not hashed
	 */
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isLoggedIn() {
		return BeanUtil.getSession().getAttribute("userId") != null;
	}
	
	/**
	 * Do a login
	 * @return index if successful, return to login if failed
	 */
	public String login() {
		String retval;
		
		if(!isLoggedIn()) {
			user = userDao.find(username, SHA512.hashText(password));
			if(user != null) {
				// set session, redirect to home, clear password & username?
				HttpSession session = BeanUtil.getSession();
				
				session.setAttribute("userId", user.getId());
				session.setAttribute("userName", user.getName());
				session.setAttribute("userEmail", user.getEmail());
				session.setAttribute("userRole", user.getRoleId());
				
				// throw away
				username = null;
				password = null;
				user = null;
				
				retval = "index";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your username or password isn't valid"));
				retval = "login";
			}
		} else {
			retval = "index";
		}
		
		return retval;
	}
	
	/**
	 * Do a logout
	 * @return destination
	 */
	public String logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		
		// redirect to home
		try {
			ec.redirect("index.xhtml");
		} catch (IOException e) {
			// quietly
		}
		
		return "index.xhtml";
	}
}
