package org.wbd.controller;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.wbd.helper.DatabaseHelper;
import org.wbd.model.Post;

public class PostController {
	private Post post;
	private int selectedPost;
	private DatabaseHelper dbhelp;
	
	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	public PostController() {
		dbhelp = DatabaseHelper.getInstance();
	}

	public int getSelectedPost() {
		return selectedPost;
	}

	public void setSelectedPost(int selectedPost) {
		this.selectedPost = selectedPost;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public Post getPost() {
		return post;
	}

	public List<Post> getPosts() {
		String username = loginController.getUser().getUsername();
		return dbhelp.getPosts(username);
	}
	
	public void view() {
		post = dbhelp.getPost(selectedPost);
	}
	
	public String hapus() {
		dbhelp.deletePost(selectedPost);
		return "index";
	}
	
	public String publish() {
		//TODO: implement
		return "index";
	}
}
