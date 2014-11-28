/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.beans;

/**
 *
 * @author Gilang
 */
public class Post {
	
	private int post_id;
	private String user_id;
	private String title;
	private String content;
	private String date;
	private boolean published;
	private boolean deleted;
	
	public Post(int post_id, String user_id, String title, String content,
					String date, boolean published, boolean deleted){
		this.post_id = post_id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.published = published;
		this.deleted = deleted;
	}

	public int getPost_id() {
		return post_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public boolean isPublished() {
		return published;
	}

	public boolean isDeleted() {
		return deleted;
	}
	
	
}
