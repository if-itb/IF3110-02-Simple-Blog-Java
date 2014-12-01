package somepackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CommentLoader {
	
	Connector con;
	ArrayList<Comment> listComment;
	private int postID;
	
	public CommentLoader() throws SQLException {
		con = new Connector("db_simple_blog","root","");
		postID = ContentLoader.staticCurrentID;
	}
	
	public int getPostID() {
		return postID;
	}
	public void setPostID(int id) {
		postID=id;
	}
	
	public ArrayList<Comment> getListComment() throws SQLException {
		listComment = new ArrayList<>();
		String id = "'"+ postID +"'";
		try(ResultSet rs = con.executeQuery("SELECT * FROM tbl_comment WHERE id_post="+id + 
				"ORDER BY tanggal desc")){
			while(rs.next()) {
				Comment temp = new Comment();
				temp.setPostID(postID);
				temp.setNama(rs.getString("nama"));
				temp.setEmail(rs.getString("email"));
				temp.setTanggal((rs.getString("tanggal")));
				temp.setKomentar(rs.getString("komentar"));
				listComment.add(temp);
			}
		}
		con.closeConnection();
		
		return listComment;
	}
	
}
