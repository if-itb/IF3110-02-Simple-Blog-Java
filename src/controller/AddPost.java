package controller;

import java.util.Date;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.Post;
import entities.Util;

@ManagedBean(name = "addPost", eager = true)
@RequestScoped
public class AddPost {
	private Post post;
	
	public AddPost(){
		post = new Post();
	}

	public String getTitle() {
		return post.getTitle();
	}

	public void setTitle(String str) {
		post.setTitle(str);
	}

	public String getContent() {
		return post.getContent();
	}

	public void setContent(String str) {
		post.setContent(str);
	}

	public Date getDate(){
		return post.getDate();
	}

	@SuppressWarnings("deprecation")
	public void setDate(String t) {
		post.setDate(new Date(t));
	}
	
	public void addPost() {
		if(Util.getUserId() != null){
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			
			String query = "INSERT INTO post (id_user, judul, isi, waktu, is_deleted, is_published) "
					+ "VALUES ("+Util.getUserId()+","+post.getContent()+","
					+ post.getDate().getTime()+",0,0";
			dbUtil.execute(query);
		}
	}
}
