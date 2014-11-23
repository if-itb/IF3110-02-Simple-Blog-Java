package entities;

public class UserDetails {
	private String name, email;
	private int user_id;
	
	public UserDetails(String name, String email, int user_id) {
		this.name = name;
		this.email = email;
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getUserId(){
		return user_id;
	}
}
