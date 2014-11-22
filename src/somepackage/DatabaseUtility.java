package somepackage;

public class DatabaseUtility {
	private static DatabaseUtility instance = new DatabaseUtility();

	public UserDetails findUser(String username, String password) {
		if (username.equals("secret") && password.equals("secret"))
			return new UserDetails("Daniel Ishutin", "dendimon@gmail.com");
		
		return null;
	}
	
	private DatabaseUtility() {
	}

	public static DatabaseUtility getInstance() {
		return instance;
	}
	
	
}
