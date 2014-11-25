package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.faces.bean.*;
import entities.Post;
import entities.UserDetails;

@ManagedBean
@ApplicationScoped
public class DatabaseObject {
	public List<Post> getPostList() {
		/**
		 * Return Posts that is not deleted and published
		 */

		DatabaseUtility dbUtil = DatabaseUtility.getInstance();
		ResultSet rs = dbUtil
				.execute("SELECT * FROM `post` WHERE `is_deleted` = 0 AND `is_published` = 1");

		List<Post> result = new ArrayList<Post>();
		try {
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
			System.err.println("Error at DatabaseObject.getPostList()");
			System.exit(10);
		}

		return result;
	}

	/**
	 * 
	 * @return users in database
	 */
	public List<UserDetails> getUserList() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();
		ResultSet rs = dbUtil.execute("SELECT * FROM `user`");

		List<UserDetails> result = new ArrayList<UserDetails>();
		try {
			while (rs.next()) {
				UserDetails user = new UserDetails();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setRole(rs.getInt(6));
				result.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error at DatabaseObject.getPostList()");
			System.exit(10);
		}

		return result;
	}
}
