package entities;

public class UserDetails {
	private String name, email;
	
	public UserDetails(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
