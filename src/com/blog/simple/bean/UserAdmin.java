package com.blog.simple.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import com.blog.simple.SHA512;
import com.blog.simple.db.DAOFactory;
import com.blog.simple.db.UserDAO;
import com.blog.simple.model.User;

@ManagedBean(name = "userAdmin")
@SessionScoped
public class UserAdmin implements Serializable {
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the UserDAO object
	 */
	private UserDAO dao = DAOFactory.getInstance("simple-blog").getUserDAO();
	
	/**
	 * Object that stores the userList
	 */
	private static Collection<User> userList;
	
	/**
	 * userId that is going to be edited
	 */
	private Long userEditId;
	
	/**
	 * temporary object for addAction
	 */	
	private User userAdd;
	
	/**
	 * temporary object to store old passwordHash
	 */
	private String oldPasswordHash;
	
	@PostConstruct
	public void init() {
		userList = dao.selectUserAsCollection();
		userEditId = null;
		
		userAdd = null;
	}
	
	public Collection<User> getUserList() {
		return userList;
	}
	
	public Long getUserEditId() {
		return userEditId;
	}
	
	public User getUserAdd() {
		return userAdd;
	}
	
	public String editAction(Long id) {
		userEditId = id;
		
		return null;
	}
	
	public String cancelEditAction() {
		return editAction(null);
	}
	
	public String addAction() {
		userAdd = new User();
		
		return null;
	}
	
	public String cancelAddAction() {
		userAdd = null;
		
		return null;
	}
	
	public String refresh() {
		userList = dao.selectUserAsCollection();
		
		return null;
	}
	
	public String addUser() {
		dao.create(userAdd);
		
		userAdd.setPassword(SHA512.hashText(userAdd.getPassword()));
		userAdd = null;
		
		return refresh();
	}
	
	public User findUser(Long id) {
		return dao.find(id);
	}
	
	public String deleteUser(Long id) {
		HttpSession session = BeanUtil.getSession();
		
		if(session.getAttribute("userId") != null) {
			Long currentUserId = (Long)session.getAttribute("userId");
			
			if(currentUserId != id) {
				dao.delete(id);
			} else {
				// cannot delete self; show message
			}
		} else {
			// print error message
		}
		
		return refresh();
	}
	
	public String updateUser(User user) {
		assert(userEditId == user.getId());
		
		dao.update(user);
		
		userEditId = null;
		
		return refresh();
	}
	
	/**
	 * Validator for changing existing User password
	 * @param event {@link ComponentSystemEvent} object
	 */
	public void validateChangePassword(ComponentSystemEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		UIComponent components = event.getComponent();
		
		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("password");
		String password = uiInputPassword.getLocalValue() == null ? ""
						: uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();
		
		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
								: uiInputConfirmPassword.getLocalValue().toString();
		
		if(password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		} else {
			if(!password.equals(confirmPassword)) {
				FacesMessage msg = new FacesMessage("Password must match confirm password");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage(passwordId, msg);
				fc.renderResponse();
			}
		}
	}
	
	/**
	 * Validator for new User password
	 * @param event {@link ComponentSystemEvent} object
	 */
	public void validateNewPassword(ComponentSystemEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		UIComponent components = event.getComponent();
		
		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("newPassword");
		String password = uiInputPassword.getLocalValue() == null ? ""
						: uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();
		
		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("newConfirmPassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
								: uiInputConfirmPassword.getLocalValue().toString();
		
		if(password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		} else {
			if(!password.equals(confirmPassword)) {
				FacesMessage msg = new FacesMessage("Password must match confirm password");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage(passwordId, msg);
				fc.renderResponse();
			}
		}
	}
}
