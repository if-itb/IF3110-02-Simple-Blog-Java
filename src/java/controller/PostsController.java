/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.database.MySQL;
import model.Post;

/**
 *
 * @author christangga
 */
@ManagedBean(name = "postsController", eager = true)
@SessionScoped
public class PostsController implements Serializable {
	
	private int active_id;
	private String active_title;
	private long active_date;
	private String active_content;
	private boolean active_status;
	
	/**
	 * Creates a new instance of PostsController
	 */
	public PostsController() {
	}

	public int getActive_id() {
		return active_id;
	}

	public void setActive_id(int active_id) {
		this.active_id = active_id;
	}

	public String getActive_title() {
		return active_title;
	}

	public void setActive_title(String active_title) {
		this.active_title = active_title;
	}

	public long getActive_date() {
		return active_date;
	}

	public void setActive_date(long active_date) {
		this.active_date = active_date;
	}

	public String getActive_content() {
		return active_content;
	}

	public void setActive_content(String active_content) {
		this.active_content = active_content;
	}

	public boolean isActive_status() {
		return active_status;
	}

	public void setActive_status(boolean active_status) {
		this.active_status = active_status;
	}

	public void createPost(String title, long date, String content) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.createPost(title, date, content);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	
	public List<Post> getAllPosts() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllPosts();
	}
	
	public List<Post> getAllPublishedPosts() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllPublishedPosts();
	}
	
	public List<Post> getAllUnpublishedPosts() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllUnpublishedPosts();
	}
	
	public List<Post> getAllDeletedPosts() {
		MySQL mysql = new MySQL();
		
		return mysql.getAllDeletedPosts();
	}
	
	public Post getPost(int id) {
		MySQL mysql = new MySQL();
		
		return mysql.getPost(id);
	}
	
	public void updatePost(int id, String title, long date, String content, boolean status) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.updatePost(id, title, date, content, status);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	
	public void deletePost(int id) {
		MySQL mysql = new MySQL();
		
		mysql.deletePost(id);
	}
	
	public void viewPost(int id) throws IOException {
		active_id = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("view_post.xhtml");
	}
	
	public void editPost(int id, String title, long date, String content, boolean status) throws IOException {
		active_id = id;
		active_title = title;
		active_date = date;
		active_content = content;
		active_status = status;
		FacesContext.getCurrentInstance().getExternalContext().redirect("edit_post.xhtml");
	}
	
	public void restorePost(int id) throws IOException {
		MySQL mysql = new MySQL();
		
		mysql.restorePost(id);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	
}
