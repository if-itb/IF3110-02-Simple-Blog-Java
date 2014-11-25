package controller;


import java.util.Date;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.UserData;
import entities.Post;

@ManagedBean
@RequestScoped
public class AddPost {
	@ManagedProperty(value="#{userData}")
	private UserData alpha;
	
	public void setAlpha(UserData z) {
		alpha = z;
	}
	
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

	public void setDate(Date date) {
		post.setDate(date);
		System.out.println(post.getDate().toString());
	}
	
	public String execute() {
		if(alpha != null){
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			
			@SuppressWarnings("deprecation")
			String date = ""+(1900+post.getDate().getYear())+"/"+(post.getDate().getMonth()+1)+"/"+post.getDate().getDate();
			
			String inTitle = DatabaseUtility.forHTML(post.getTitle());
			String inContent = DatabaseUtility.forHTML(post.getContent());
			
			String query = "INSERT INTO post (id_user, judul, isi, waktu, is_deleted, is_published) "
					+ "VALUES ("+alpha.getDetails().getUserId()+",'"+ inTitle +"','"+ inContent +"',"
					+ "'"+date+"'"+",0,0)";
			
			dbUtil.execute(query);
		}
		return "index";
	}
}
