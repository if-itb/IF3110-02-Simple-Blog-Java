package org.wbd.model;

import java.util.Calendar;
import java.util.Date;

public class Post {
	private String author;
	private String title;
	private Calendar date;
	private String content;
	
	public Post() {
		
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date.getTime();
	}
	
	public void setDate(Date date) {
		this.date.setTime(date);
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
