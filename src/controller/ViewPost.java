package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entities.Comment;
import entities.Post;
import entities.UserData;

@ManagedBean
@RequestScoped
public class ViewPost {
	private int id;
	private String name, email, comment;

	private Post post;

	@ManagedProperty(value = "#{userData}")
	UserData userData;

	public void setUserData(UserData ud) {
		userData = ud;
	}

	/**
	 * 
	 * @param id_user
	 * @return posts of id_user
	 */
	public List<Post> getOwnerPostList() {
		List<Post> result = new ArrayList<>();

		Connection con = DatabaseUtility.getInstance().getLiveConnection();

		ResultSet rs;
		try {
			String idPostQuery = "SELECT * FROM `post` WHERE `is_deleted` = 0 AND `is_published` = 1 AND `id_user` = ?";

			PreparedStatement pstmt = con.prepareStatement(idPostQuery);
			pstmt.setInt(1, userData.getDetails().getUserId());
			pstmt.execute();
			rs = pstmt.getResultSet();

			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt(1));
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				post.setDate(rs.getDate(5));
				result.add(post);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in getPostList(id_user)");
			e.printStackTrace();
		}

		return result;
	}

	public String postComment() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();
		Date date = new Date(System.currentTimeMillis());
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);

		ResultSet rs = dbUtil
				.execute("INSERT INTO `comment`(`id_post`, `isi`, `waktu`, `name`, `email`) VALUES ("
						+ id
						+ ",'"
						+ comment
						+ "','"
						+ newstring
						+ "','"
						+ name + "','" + email + "')");

		System.out.println("postComment() executed");
		assert (rs == null);

		return null;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comment> getComments() {
		List<Comment> result = new ArrayList<>();

		try {
			DatabaseUtility dbUtil = DatabaseUtility.getInstance();
			ResultSet rs = dbUtil
					.execute("SELECT * FROM `comment` WHERE `id_post` = " + id
							+ " ORDER BY `num` DESC");

			if (rs != null) {
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setName(rs.getString(5));
					comment.setEmail(rs.getString(6));
					comment.setTime(rs.getString(4));
					comment.setContent(rs.getString(3));
					result.add(comment);
				}
			}
		} catch (SQLException ex) {
			System.err.println("Error when getting post with id = " + id);
		}

		return result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}
