package org.wbd.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.wbd.helper.DatabaseHelper;
import org.wbd.model.Comment;
import org.wbd.model.User;

public class CommentController {
	private String newSender;
	private Date newDate;
	private String newEmail;
	private String newContent;
	
	private int selectedPost;
	
	private List<Comment> comments;
	private DatabaseHelper dbhelp;

	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	public CommentController() {
		newDate = new Date();
		dbhelp = DatabaseHelper.getInstance();
	}
	
	public String getNewSender() {
		return newSender;
	}
	public void setNewSender(String newSender) {
		this.newSender = newSender;
	}
	public Date getNewDate() {
		return newDate;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	public int getSelectedPost() {
		return selectedPost;
	}
	public void setSelectedPost(int selectedPost) {
		this.selectedPost = selectedPost;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public LoginController getLoginController() {
		return loginController;
	}
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public void init() {
		setComments(dbhelp.getComments(selectedPost));
	}

	public void loadSender() {
//		User user = loginController.getUser();
//		if (user != null) {
//			setNewSender(user.getUsername());
//		}
		setNewSender("test");
	}
	
	public String kirim() {
		java.sql.Date newDateSql = new java.sql.Date(newDate.getTime());
		dbhelp.addComment(selectedPost, newSender, newEmail, newDateSql, newContent);
		setComments(dbhelp.getComments(selectedPost));
		return null;
	}

}
