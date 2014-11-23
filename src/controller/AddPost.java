package controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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

	public void setDate(String t) {
		Date date = new SimpleDateFormat("mm/dd/yy").parse(t, new ParsePosition(0));
		post.setDate(date);
	}
	
	public void execute() {
		System.out.println("Name:   "+ alpha.getDetails().getName());
		if(alpha != null){
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			
			String query = "INSERT INTO post (id_user, judul, isi, waktu, is_deleted, is_published) "
					+ "VALUES ("+alpha.getDetails().getUserId()+",'"+post.getTitle()+"','"+post.getContent()+"',"
					+ "'2014-11-23'"+",0,0)";
			
			System.out.println(query);
			
			dbUtil.execute(query);
		}
	}
}
