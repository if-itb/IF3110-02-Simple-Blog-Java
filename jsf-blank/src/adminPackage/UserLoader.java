package adminPackage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import somepackage.Connector;

@ManagedBean
@SessionScoped
public class UserLoader {
	private String usname;
	private String uspass;
	private static Connector con;
	private ArrayList<User> user, users;
	public static int UNDEFINED = -999;
	public static int currentUserID = UNDEFINED;
	private User currentUser;
	private Map<String, Integer> roleMap;
	
	private UserAdder userAdder;
	
	private Cookie cookie;
	
	public UserLoader(){
		try {
			con = new Connector("db_simple_blog", "root","");
			retrieveUsers();
			buildRoleMap();
//			changeCurrentUser();
			userAdder = new UserAdder();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public String getUspass() {
		return uspass;
	}

	public void setUspass(String uspass) {
		this.uspass = uspass;
	}
	
	public Cookie getCookie() {
		return cookie;
	}

	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}
	
	public ArrayList<User> getUser() {
		return user;
	}
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}
	
	public Map<String, Integer> getRoleMap() {
		return roleMap;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	private void retrieveUsers() throws SQLException {
		users = new ArrayList<>();
		try(ResultSet rs = con.executeQuery("SELECT * FROM `tbl_user`")){
			while(rs.next()) {
				User temp = new User();
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setID(Integer.parseInt(rs.getString("user_id")));				
				temp.setEmail(rs.getString("email"));
				temp.setRole_id(Integer.parseInt(rs.getString("role_id")));
				users.add(temp);
			}
		}
		con.closeConnection();
	}
	
	public void updateUsers() {
		try {
			retrieveUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toEditUser(int id) {
		currentUserID = id;
		changeCurrentUser();
		return("edit-user?faces-redirect=true");
	}
	
	private void changeCurrentUser() {
		if (currentUserID != UNDEFINED) {
			for (User user : users) {
				if (user.getID() == currentUserID) {
					currentUser = user;
					break;
				}
			}
		}
	}
	
	private void buildRoleMap() throws SQLException {
		roleMap = new LinkedHashMap<String, Integer>();
		try(ResultSet rs = con.executeQuery("SELECT * FROM `tbl_role`")){
			while(rs.next()) {
				roleMap.put(rs.getString("role_name"), Integer.parseInt(rs.getString("role_id")));
			}
		}
		con.closeConnection();
	}
	
	public String editUser() throws SQLException {
		String username = "'" + currentUser.getUsername() + "'";
		String password = "'" + currentUser.getPassword() + "'";
		String name = "'" + currentUser.getName() + "'";
		String email = "'" + currentUser.getEmail() + "'";
		
		con.executeUpdate("UPDATE tbl_user SET username=" + username + ", password=" + password + 
				", name=" + name + ", email=" + email + ", role_id=" + currentUser.getRole_id() + 
				" WHERE user_id=" + currentUser.getID());
		retrieveUsers();
		
		return("manajemen-user?faces-redirect=true");
	}
	
	public String deleteUser(int id) throws SQLException {		
		con.executeUpdate("DELETE FROM tbl_user WHERE user_id=" + id);
		retrieveUsers();
		return("manajemen-user?faces-redirect=true");
	}

	public void getUserFromDatabase() throws SQLException {
		user = new ArrayList<>();
		try (ResultSet rs = con.executeQuery("select * from tbl_user where username = '" + usname + "'")) {
			while(rs.next()) {
				User temp = new User();
				temp.setID(Integer.parseInt(rs.getString("user_id")));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setName(rs.getString("name"));
				temp.setRole_id(Integer.parseInt(rs.getString("role_id")));
				user.add(temp);
			}
		}
		con.closeConnection();
		setUser(user);
	}
	
	
	public UserAdder getUserAdder() {
		return userAdder;
	}

	public void setUserAdder(UserAdder userAdder) {
		this.userAdder = userAdder;
	}
	
	public String addUser() throws SQLException {
		userAdder.addUser();
		retrieveUsers();
		return("manajemen-user?faces-redirect=true");
	}

	public void login() throws SQLException, IOException{
		getUserFromDatabase();
		HttpServletResponse httpServletResponse = 
			(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if (usname.equals(user.get(0).getUsername()) && uspass.equals(user.get(0).getPassword())) {		// berhasil login
			
			Cookie cook = new Cookie("userName", usname);   
			cook.setMaxAge(3600);
			httpServletResponse.addCookie(cook); 
			httpServletResponse.sendRedirect("success.jsf");
			setCookie(cook);
		}
		else{	// tidak berhasil login
			httpServletResponse.sendRedirect("invalid.jsf");
		}
	}
	
	public void logout() throws IOException {
		
		Cookie cookieX = getCookie();
		cookieX.setValue(null);
		cookieX.setMaxAge(0);
		setCookie(cookieX);
		
		HttpServletResponse httpServletResponse = 
				   (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		httpServletResponse.addCookie(cookieX);
		httpServletResponse.sendRedirect("logout.jsf");
	}
	
	public String getValueCookie(){
		HttpServletRequest httpServletRequest = 
		(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();   
		
		Cookie[] cookies = httpServletRequest.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++){
				if (cookies[i].getName().equalsIgnoreCase("userName")){
					cookieValue = cookies[i].getValue(); 
				}
			}
		}
		
		return cookieValue;
	}
			
	public boolean isLoggedIn(){
		boolean auth = true;
		if(getCookie()!=null){
			if((getValueCookie() == null) || (getValueCookie() == "")){
				auth = true;	// belum login
			}
			else{
				auth = false;	// login
			}
		}
		return auth;
	}
	
	public boolean isAdmin(){
		if(getUser().get(0).getRole_id()==2){
			return true;
		}
		else{
			return false;			
		}
	}
	
	public boolean isOwner(){
		if(getUser().get(0).getRole_id()==3){
			return true;
		}
		else{
			return false;			
		}
	}
	
	public boolean isEditor(){
		if(getUser().get(0).getRole_id()==4){
			return true;
		}
		else{
			return false;			
		}
	}
	

}

