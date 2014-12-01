package adminPackage;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class User {
	private int ID;
	private String username;
	private String password;
	private String name;
	private String email;
	private String roleString;
	private int role_id;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
		roleString = toRoleString(role_id);
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	public String getRoleString() {
		return roleString;
	}
	private String toRoleString(int idRole) {
		switch(idRole) {
			case 1 : return "guest";
			case 2 : return "admin";
			case 3 : return "owner";
			case 4 : return "editor";
			default : return "";
		}
	}
	
	public int toRoleID(String role) {
		if (role.equals("guest"))
			return 1;
		if (role.equals("admin"))
			return 2;
		if (role.equals("owner"))
			return 3;
		if (role.equals("editor"))
			return 4;
		return 0;
	}

}
