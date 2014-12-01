package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import entities.Post;

@ManagedBean
public class EditPost {

	@ManagedProperty(value = "#{viewPost}")
	private ViewPost view;

	private Post post;

	public EditPost() {
		post = new Post();
	}

	public String getContent() {
		return post.getContent();
	}

	public String getDate() {
		return post.getDate().toString();
	}

	public int getId() {
		return post.getId();
	}

	public String getTitle() {
		return post.getTitle();
	}

	public void HardDelete(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "Delete from post WHERE id = " + temp_id;

		System.out.println(query);

		dbUtil.execute(query);
	}

	public void initialize() {
		view = new ViewPost();
		view.setId(this.post.getId());
		view.execute();
		this.setContent(view.getContent());
		post.setDate(view.getDate());
		this.setTitle(view.getTitle());
	}

	public void setContent(String str) {
		post.setContent(str);
	}

	public void setDate(String dateStr) {
		System.out.println("Set date Called");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
		try {
			date = formatter.parse(dateStr);
			post.setDate(date);
			System.out.println(getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to set Date!");
			e.printStackTrace();
		}
	}

	public void setId(int id) {
		post.setId(id);
	}

	public void setTitle(String str) {
		post.setTitle(str);
	}

	public void setView(ViewPost vp) {
		view = vp;
	}
}
