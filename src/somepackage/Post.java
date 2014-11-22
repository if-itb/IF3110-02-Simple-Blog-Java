package somepackage;

import java.util.Date;

public class Post {
	private String title, content;
	private Date date;

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

}
