package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.Post;
import entities.UserData;

@ManagedBean
@RequestScoped
public class AddPost {
	@ManagedProperty(value = "#{userData}")
	private UserData alpha;

	public void setAlpha(UserData z) {
		alpha = z;
	}

	private Post post;

	public AddPost() {
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

	public Date getDate() {
		return post.getDate();
	}

	public void setDate(Date date) {
		post.setDate(date);
		System.out.println(post.getDate().toString());
	}

	public String execute() {
		if (alpha != null) {
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();

			@SuppressWarnings("deprecation")
			String date = "" + (1900 + post.getDate().getYear()) + "-"
					+ (post.getDate().getMonth() + 1) + "-"
					+ post.getDate().getDate();

			Connection con = dbUtil.getLiveConnection();
			String query = "INSERT INTO `post` (`id_user`, `judul`, `isi`, `waktu`, `is_deleted`, `is_published`) VALUES (?, ?, ?, ?, 0, 0)";
			System.out
			.printf("INSERT INTO `post` (`id_user`, `judul`, `isi`, `waktu`, `is_deleted`, `is_published`) VALUES (%d, %s, %s, %s, 0, 0)\n",
					alpha.getUserID(), getTitle(),
					getContent(), date);
			PreparedStatement pst;
			try {
				pst = con.prepareStatement(query);
				pst.setInt(1, alpha.getUserID());
				pst.setString(2, getTitle());
				pst.setString(3, getContent());
				pst.setString(4, date);
				pst.execute();
			} catch (SQLException e) {
				System.out.println("Failed Query");
				e.printStackTrace();
			}
		}
		return "index";
	}
}
