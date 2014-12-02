//UserBean.java(Bean class which holds user data in a bean)


package ExamplePackage;
public class UserBean {
   
    private String username;
    private String password;
    public boolean valid;
	public String role;

    public String getUsername() {
       return username;
   }
    public void setUsername(String newUsername) {
       username = newUsername;
   }     

    public String getPassword() {
       return password;
   }
    public void setPassword(String newPassword) {
       password = newPassword;
   }
        
    public boolean isValid() {
       return valid;
   }
    public void setValid(boolean newValid) {
       valid = newValid;
   }

	public String getRole() {
		return role;
	}
	public void setRole(String newRole) {
		role = newRole;
	}
	
}