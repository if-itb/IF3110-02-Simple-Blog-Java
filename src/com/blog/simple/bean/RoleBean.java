package com.blog.simple.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.blog.simple.model.User.Role;

@ManagedBean(name = "roleBean")
@ApplicationScoped
public class RoleBean {
	private SelectItem[] roleList;
	
	@PostConstruct
	private void init() {
		Role[] roles = Role.values();
		roleList = new SelectItem[roles.length];
		
		for(int i = 0; i < roleList.length; i++) {
			roleList[i] = new SelectItem(roles[i], roles[i].getDescription());
		}
	}
	
	public SelectItem[] getRoleList() {
		return roleList;
	}
	
	public Role getRole(Integer id) {
		Role role;
		
		if(id == null) {
			role = null;
		} else {
			if(id > 0 && id <= roleList.length) {
				role = Role.getRole(id);
			} else {
				role = null;
			}
		}
		
		return role;
	}
}
