package com.blog.simple.model;

import java.io.Serializable;

import com.blog.simple.SHA512;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int ID_ADMIN = 1;
	private static final int ID_OWNER = 2;
	private static final int ID_EDITOR = 3;
	
	public enum Role {
		ADMIN (ID_ADMIN, "Admin"),
		OWNER (ID_OWNER, "Owner"),
		EDITOR (ID_EDITOR, "Editor");
		
		private int id;
		
		private String description;
		
		private Role(int id, String description) {
			this.id = id;
			this.description = description;
		}
		
		public int getId() {
			return id;
		}
		
		public String getDescription() {
			return description;
		}
		
		public static Role getRole(int id) throws RoleException {
			Role role;
			
			switch(id) {
			case ID_ADMIN:
				role = ADMIN;
				break;
			case ID_OWNER:
				role = OWNER;
				break;
			case ID_EDITOR:
				role = EDITOR;
				break;
			default:
				throw new RoleException("Unknown roleId: " + id);
			}
			
			return role;
		}
	}
	
	private Long id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private Role role;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		// TODO: validation
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String username) {
		this.name = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = SHA512.hashText(password);
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setRole(int id) throws RoleException {
		this.role = Role.getRole(id);
	}
	
	public Role getRole() {
		return role;
	}
	
	public int getRoleId() {
		return role.id;
	}
	
	public String getRoleDescription() {
		return role.description;
	}
	
	/**
     * The user ID is unique for each User. So this should compare User by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && (id != null)
             ? id.equals(((User) other).id)
             : (other == this);
    }

    /**
     * The user ID is unique for each User. So User with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (id != null) 
             ? (this.getClass().hashCode() + id.hashCode()) 
             : super.hashCode();
    }

    /**
     * Returns the String representation of this User. Not required, it just pleases reading logs.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("User[id=%d,username=%s,email=%s,role=%s]", 
            id, name, email, role.getDescription());
    }
}
