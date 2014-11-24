package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.Post;

@ManagedBean
@RequestScoped
public class ViewPost {
	private Post post;
	private int id;

	public void execute() {
		try {
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			ResultSet rs = dbUtil.execute("SELECT * FROM `post` WHERE `id` = "
					+ id);
			if (rs != null) {
				rs.next();
				post = new Post();
				post.setId(rs.getInt(1));
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				post.setDate(rs.getDate(5));
			}
		} catch (SQLException ex) {
			System.err.println("Error when getting post with id = " + id);
		}
	}

	public String getTitle() {
		return post.getTitle();
	}

	public String getContent() {
		return post.getContent();
	}

	public Date getDate() {
		return post.getDate();
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}
	
	public String goToViewPost(){
		return "view_post?faces-redirect=true";
	}

}