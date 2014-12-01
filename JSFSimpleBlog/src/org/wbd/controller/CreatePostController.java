package org.wbd.controller;

import java.util.Date;
import javax.faces.bean.ManagedProperty;

import org.wbd.helper.DatabaseHelper;
import org.wbd.model.Post;

public class CreatePostController {
	private int postId;
	private String newJudul;
	private Date newTanggal;
	private String newKonten;
	private boolean postPublished;
	
	private int selectedPost;
	private DatabaseHelper dbhelp;

	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	public CreatePostController() {
		dbhelp = DatabaseHelper.getInstance();
		newTanggal = new Date();
		postId = -1;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getNewJudul() {
		return newJudul;
	}
	public void setNewJudul(String newJudul) {
		this.newJudul = newJudul;
	}
	public Date getNewTanggal() {
		return newTanggal;
	}
	public void setNewTanggal(Date newTanggal) {
		this.newTanggal = newTanggal;
	}
	public String getNewKonten() {
		return newKonten;
	}
	public void setNewKonten(String newKonten) {
		this.newKonten = newKonten;
	}

	public boolean isPostPublished() {
		return postPublished;
	}

	public void setPostPublished(boolean postPublished) {
		this.postPublished = postPublished;
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

	public void init() {
		Post post = dbhelp.getPost(selectedPost);
		setPostId(post.getId());
		setNewJudul(post.getTitle());
		setNewTanggal(post.getDate());
		setNewKonten(post.getContent());
		setPostPublished(post.isPublished());
	}
	
	public String simpan() {
//		String username = loginController.getUser().getUsername();
		String username = "test";
		java.sql.Date sqlDate = new java.sql.Date(newTanggal.getTime());
		dbhelp.addPost(username, newJudul, sqlDate, newKonten);
		return "unpublished?faces-redirect=true";
	}
	
	public String simpanEdit() {
		java.sql.Date sqlDate = new java.sql.Date(newTanggal.getTime());
		dbhelp.updatePost(postId, newJudul, sqlDate, newKonten);
		if (postPublished) {
			return "index?faces-redirect=true";
		} else {
			return "unpublished?faces-redirect=true";
		}
	}
}
