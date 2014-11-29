package controller;


import java.util.Date;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.Post;

@ManagedBean
@RequestScoped
public class EditPost {

	
	@ManagedProperty(value="#{viewPost}")
	private ViewPost view;
	
	private Post post;
	
	public void setView(ViewPost vp){
		view = vp;
	}
	
	public EditPost(){
		post = new Post();
	}

	public int getId(){
		return post.getId();
	}
	
	public void setId(int id){
		post.setId(id);
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

	public String getDate() {
		return post.getDate().toString();
	}

	public void setDate(Date date) {
		post.setDate(date);
		System.out.println(post.getDate().toString());
	}
	
	public void initialize(){
		view = new ViewPost();
		view.setId(this.post.getId());
		view.execute();
		this.setContent(view.getContent());
		this.setDate(view.getDate());
		this.setTitle(view.getTitle());
	}
	
	public String execute() {
		if(post!=null){
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			
			@SuppressWarnings("deprecation")
			String date = ""+(1900+post.getDate().getYear())+"-"+(post.getDate().getMonth()+1)+"-"+post.getDate().getDate();
			
			String inTitle = DatabaseUtility.forHTML(post.getTitle());
			String inContent = DatabaseUtility.forHTML(post.getContent());
			
			String query = "UPDATE post SET judul ='"+ inTitle+ 
					"', isi ='"+ inContent +"', waktu = '"+date+"' WHERE id = "+ post.getId();
			
			System.out.println(query);
			
			dbUtil.execute(query);
		}
		return "index";
	}
}
