package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.Post;

@ManagedBean
@RequestScoped
public class EditPost {

	@ManagedProperty(value = "#{viewPost}")
	private ViewPost view;

	private Post post;

	public void setView(ViewPost vp) {
		view = vp;
	}

	public EditPost() {
		post = new Post();
	}

	public int getId() {
		return post.getId();
	}

	public void setId(int id) {
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

	public void initialize() {
		view = new ViewPost();
		view.setId(this.post.getId());
		view.execute();
		this.setContent(view.getContent());
		this.setDate(view.getDate());
		this.setTitle(view.getTitle());
	}

	public String execute() {
		if (post != null) {
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();

			@SuppressWarnings("deprecation")
			String date = "" + (1900 + post.getDate().getYear()) + "-"
					+ (post.getDate().getMonth() + 1) + "-"
					+ post.getDate().getDate();

			Connection con = dbUtil.getLiveConnection();
			String query = "INSERT `post` SET `judul`=?, `isi`=?, `waktu`=? WHERE `id`=?";
			PreparedStatement pst;
			System.out
					.printf("INSERT `post` SET `judul`=%s, `isi`=%s, `waktu`=%s WHERE `id`=%d\n",
							getTitle(), getContent(), date, getId());

			try {
				pst = con.prepareStatement(query);
				pst.setString(1, getTitle());
				pst.setString(2, getContent());
				pst.setString(3, date);
				pst.setInt(4, getId());
				pst.execute();
			} catch (SQLException e) {
				System.out.println("Query Failed");
				e.printStackTrace();
			}
		}
		return "index";
	}

	public void HardDelete(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "Delete from post WHERE id = " + temp_id;

		System.out.println(query);

		dbUtil.execute(query);
	}
}
