package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class EditUser {
	private int user_id;
	private String user_username;
	private String user_password;
	private String user_name;
	private String user_email;
	private String user_strRole;
	private int user_role;	

	public void addUser() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "INSERT INTO user (username, password, nama, email, role) "
				+ "VALUES ('"
				+ this.getUsername()
				+ "','"
				+ this.getPassword()
				+ "','"
				+ this.getName()
				+ "',"
				+ "'"
				+ this.getEmail()
				+ "'"
				+ "," + this.getRole() + ")";

		System.out.println(query);

		dbUtil.execute(query);
		
		setUsername("");
		setPassword("");
		setName("");
		setEmail("");
		setRole(10);
	}

	public void changeEmail() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE user SET email = '" + this.getEmail()
				+ "' WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void changeName() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE user SET nama = '" + this.getName()
				+ "' WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void changePassword(){
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();
		
		String query = "UPDATE user SET password = '"+ this.getPassword() +"' WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void changeRole() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE user SET role = " + this.getRole()
				+ " WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void deleteUser(int i) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "DELETE FROM user WHERE id = " + i;

		System.out.println(query);

		dbUtil.execute(query);
	}
	
	public String getEmail() {
		return user_email;
	}

	public int getId() {
		return user_id;
	}

	public String getName() {
		return user_name;
	}

	public String getPassword() {
		return user_password;
	}

	public int getRole() {
		return user_role;
	}

	public String getStrRole() {
		return user_strRole;
	}

	public String getUsername() {
		return user_username;
	}

	public void Initialize(){
		
	}
	
	public void setEmail(String email) {
		user_email = email;
	}

	public void setId(int id) {
		user_id = id;
	}

	public void setName(String name) {
		user_name = name;
	}

	public void setPassword(String password) {
		user_password = password;
	}

	public void setRole(int role) {
		user_role = role;
	}

	public void setStrRole(String str) {
		user_strRole = str;
	}
	
	public void setUsername(String username) {
		user_username = username;
	}
	
	public void StrtoIntRole() {
		if (this.user_strRole=="Regular") user_role=10;
		else if (this.user_strRole=="Editor") user_role=20;
		else if (this.user_strRole=="Admin") user_role=30;
	}
	
	/*public String execute() {
		if(post!=null){
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			
			@SuppressWarnings("deprecation")
			String date = ""+(1900+post.getDate().getYear())+"/"+(post.getDate().getMonth()+1)+"/"+post.getDate().getDate();
			
			String inTitle = DatabaseUtility.forHTML(post.getTitle());
			String inContent = DatabaseUtility.forHTML(post.getContent());
			
			String query = "UPDATE post SET judul ='"+ inTitle+ 
					"', isi ='"+ inContent +"', waktu = '"+date+"' WHERE id = "+ post.getId();
			
			System.out.println(query);
			
			dbUtil.execute(query);
		}
		return "index";
	}
	*/
	
	public String updateUser(){
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();
		String query = "UPDATE user SET username = '"
				+ this.getUsername()
				+ "', password = '"	
				+ this.getPassword()
				+"', nama ='"
				+ this.getName()
				+"', email ='"
				+ this.getEmail()
				+"', role = "
				+this.getRole()
				+ " WHERE id = " + this.getId();
		System.out.println(query);
		dbUtil.execute(query);
		return "index";
	}
	
}
