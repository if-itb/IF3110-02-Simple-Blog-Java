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
	private int user_role;

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		user_id = id;
	}

	public String getUsername() {
		return user_username;
	}

	public void setUsername(String username) {
		user_username = username;
	}

	public String getPassword() {
		return user_password;
	}

	public void setPassword(String password) {
		user_password = password;
	}

	public String getName() {
		return user_name;
	}

	public void setName(String name) {
		user_name = name;
	}

	public String getEmail() {
		return user_email;
	}

	public void setEmail(String email) {
		user_email = email;
	}

	public int getRole() {
		return user_role;
	}

	public void setRole(int role) {
		user_role = role;
	}

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
	}

	public void deleteUser(int i) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "DELETE FROM user WHERE id = " + i;

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

	public void changeName() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE user SET nama = '" + this.getName()
				+ "' WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void changeEmail() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE user SET email = '" + this.getEmail()
				+ "' WHERE id = " + this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

}