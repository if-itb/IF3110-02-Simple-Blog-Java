

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import posts.Post;
import users.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comments.Comment;

import mysql.SQLStatements;

@ManagedBean(name = "allControl", eager = true) 
@SessionScoped

public class AllControl implements Serializable{
	 private static final long serialVersionUID = 1L;
	
	private Post CurrentPost;
	private int PostID;
	private User CurrentUser;
	private String Date;
	private SQLStatements s;
	private String message;
	private Comment lel;
	
	public AllControl(){
		CurrentPost = new Post();
		CurrentUser = new User();
		s = new SQLStatements();
	}
	
	public String showPost(){
		try {
			CallThePage();
			if(CurrentUser.getAuth()=="Guest"){
				return "guestpost";
			}
			else{
				return "post";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Home();
		}
		
	}
	
	public String Home(){
		PostID= 0;
		message = "";
		if(CurrentUser.getAuth().equals("Administrator")){
			return "admin";
		}
		else if (CurrentUser.getAuth().equals("Owner")){
			return "ownerhome";
		}
		else if (CurrentUser.getAuth().equals("Editor")) {
			return "editor";
		}
		else {
			return "home";
		}
	}
	public String Login() {
		message = "";
		try {
			if(s.IsUserExist(CurrentUser.getName()) && s.isPassword(CurrentUser.getName(), CurrentUser.getPass())){
				setCurrentUser(s.SelectUser(CurrentUser.getName(), CurrentUser.getPass()));
				setCookie();
				message = "";
				return Home();
			}
			else {
				if (s.IsUserExist(CurrentUser.getName())){
					message = "Incorect Password";
				}
				else{
					message = "User doesn't exist";
				}
				return "login";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Home();
		}
		
	}
	private void setCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		Cookie cookieUser = new Cookie("username", CurrentUser.getName());
		Cookie cookiePassword = new Cookie("password", CurrentUser.getPass());

		cookieUser.setMaxAge(100000);
		cookiePassword.setMaxAge(100000);

		HttpServletResponse servletResponse = (HttpServletResponse) (fc
				.getExternalContext().getResponse());
		servletResponse.addCookie(cookieUser);
		servletResponse.addCookie(cookiePassword);
	}
	
	public String Logout(){
		CurrentUser = new User();
		deleteCookie();
		return Home();
	}
	private void deleteCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		Cookie cookieUser = new Cookie("username", null);
		Cookie cookiePassword = new Cookie("password", null);

		cookieUser.setMaxAge(0);
		cookiePassword.setMaxAge(0);

		HttpServletResponse servletResponse = (HttpServletResponse) (fc
				.getExternalContext().getResponse());
		servletResponse.addCookie(cookieUser);
		servletResponse.addCookie(cookiePassword);
	}
	
	 public void setCurrentUser(User U){
		 CurrentUser = new User();
		 CurrentUser.setId(U.getId());
		 CurrentUser.setName(U.getName());
		 CurrentUser.setPass(U.getPass());
		 CurrentUser.setEmail(U.getEmail());
		 CurrentUser.setAuth(U.getAuth());
	 }
	 
	 public User getCurrentUser(){
		 return CurrentUser;
	 }
	 
	 public void setCurrentPost(Post P){
		 CurrentPost.setId(P.getId());
		 CurrentPost.setTitle(P.getTitle());
		 CurrentPost.setDate(P.getDate());
		 CurrentPost.setContent(P.getContent());
		 CurrentPost.setStatus(P.getStatus());
	 }
	 
	 public Post getCurrentPost(){
		 return CurrentPost;
	 }
	 
	 public void CallThePage() throws SQLException{
		 setCurrentPost(s.GetPost(PostID));
	 }

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getPostID() {
		return PostID;
	}

	public void setPostID(int postID) {
		PostID = postID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void checkCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		HttpServletRequest servletRequest = (HttpServletRequest) (fc
				.getExternalContext().getRequest());
		Cookie cookies[] = servletRequest.getCookies();
		CurrentPost = new Post();
		CurrentUser = new User();
		lel = new Comment();
		s = new SQLStatements();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String cookieName = cookies[i].getName();
				String cookieValue = cookies[i].getValue();

				if (cookieName.equals("username")){
					CurrentUser.setName(cookieValue);
				}
				if (cookieName.equals("password")){
					CurrentUser.setPass(cookieValue);
				}
			}
			try {
					setCurrentUser(s.SelectUser(CurrentUser.getName(), CurrentUser.getPass()));
			} catch (SQLException e) {
						// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(Home()+".jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Comment getLel() {
		return lel;
	}

	public void setLel(Comment lel) {
		this.lel = lel;
	}
	
	public void addComment(){
		lel.setPostId(Integer.toString(PostID));
		try {
			s.AddComment(lel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lel = new Comment();
	}
	public void userComment(){
		lel.setPostId(Integer.toString(PostID));
		lel.setName(CurrentUser.getName());
		lel.setEmail(CurrentUser.getEmail());
		try {
			s.AddComment(lel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lel = new Comment();
	}
	
	public void addNewPost(){
		CurrentPost.setStatus("Unpublished");
		try {
			s.AddPost(CurrentPost);
			message="Post has been made";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CurrentPost = new Post();
	}
	
	public String editPostInit(){
		try {
			CallThePage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "editpost";
	}
	
	public void editPost(){
		try {
			s.EditPost(CurrentPost.getId(), CurrentPost);
			message="Post has been changed";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
