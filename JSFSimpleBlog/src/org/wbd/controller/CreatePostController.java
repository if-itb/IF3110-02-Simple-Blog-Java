package org.wbd.controller;

import java.util.Date;

import javax.faces.bean.ManagedProperty;

import org.wbd.helper.DatabaseHelper;
import org.wbd.model.Post;

public class CreatePostController {
	private int newId;
	private String newJudul;
	private Date newTanggal;
	private String newKonten;
	
	private int selectedPost;
	private DatabaseHelper dbhelp;

	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	public CreatePostController() {
		newTanggal = new Date();
		newId = -1;
	}

	public int getNewId() {
		return newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
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
		setNewId(post.getId());
		setNewJudul(post.getTitle());
		setNewTanggal(post.getDate());
		setNewKonten(post.getContent());
	}
	
	public String simpan() {
		String username = loginController.getUser().getUsername();
		dbhelp.addPost(username, newJudul, (java.sql.Date) newTanggal, newKonten);
		return "index?faces-redirect=true";
	}
}
