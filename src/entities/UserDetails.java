package entities;

public class UserDetails {
	private String name, email, username, password;
	private int user_id, role;

	public UserDetails() {}
	
	public UserDetails(String name, String email, int user_id) {
		this.name = name;
		this.email = email;
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String str) {
		name = str;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String str) {
		email = str;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int n) {
		user_id = n;
	}
}
