/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author christangga
 */
public class Post {

	private int id;
	private String title;
	private long date;
	private String content;
	private boolean status;
	
	/**
	 * Creates a new instance of Post
	 */
	public Post() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		if (status) {
			return String.valueOf(id) + " " + title + " " + date + " " + content + " true";
		} else {
			return String.valueOf(id) + " " + title + " " + date + " " + content + " false";
		}
	}
}
