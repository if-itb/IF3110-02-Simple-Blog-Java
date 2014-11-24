package entities;

import java.util.Date;

public class Post {
	private String title, content;
	private Date date;
	private int id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String str) {
		title = str;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String str) {
		content = str;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date t) {
		date = t;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		id = i;
	}

}
