package somepackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.faces.bean.*;

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
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				Timestamp ts = rs.getTimestamp(5);
				post.setDate(new java.util.Date(ts.getTime()));
				result.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error at DatabaseObject.getPostList()");
			System.exit(10);
		}
		
		return result;
	}
}
