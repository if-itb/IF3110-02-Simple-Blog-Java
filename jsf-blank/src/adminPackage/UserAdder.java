package adminPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import somepackage.Connector;

@ManagedBean
public class UserAdder {
	private Connector con;
	private String username, password, name, email;
	int role;
	private Map<String, Integer> roleMap;
	
	public UserAdder() {
		try {
			con = new Connector("db_simple_blog", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getRole() {
		return role;
	}
	
	public Map<String, Integer> getRoleMap() throws SQLException {
		roleMap = new LinkedHashMap<String, Integer>();
		try (ResultSet rs = con.executeQuery("select * from tbl_role ")) {
			while(rs.next()) {				
				roleMap.put(rs.getString("role_name"), Integer.parseInt(rs.getString("role_id")));
			}
		}
		con.closeConnection();
		return roleMap;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	private void reset() {
		username = "";
		password = "";
		name = "";
		email = "";
		role = 1;
	}
	
	public void addUser() throws SQLException {
		String susername = "'" + username + "'";
		String spassword = "'" + password + "'";
		String sname = "'" + name + "'";
		String semail = "'" + email + "'";
		con.executeUpdate("INSERT INTO `tbl_user`(`username`, `password`, `name`, `email`, `role_id`) VALUES (" + 
		susername+", "+spassword+", "+sname+", "+semail+", "+role+")");
		reset();
	}
	
}
