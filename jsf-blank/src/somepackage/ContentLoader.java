package somepackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean (name="contentLoader", eager=true)
public class ContentLoader {
	
	private Connector con;
	private ArrayList<Content> posting, publishedPost, unpublishedPost, deletedPost;
	private Content currentContent;
	public static int staticCurrentID;	
	
	public ContentLoader() {
		try {
			con = new Connector("db_simple_blog", "root","");
			listPosting();
			addPublishedPost();
			addUnpublishedPost();
			addDeletedPost();
			changeCurrentContent(staticCurrentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Content> getPosting() {
		return posting;
	}
	
	public ArrayList<Content> getPublishedPost() {
		return publishedPost;
	}
	public ArrayList<Content> getUnpublishedPost() {
		return unpublishedPost;
	}
	public ArrayList<Content> getDeletedPost(){
		return deletedPost;
	}
	public void setPosting(ArrayList<Content> posting) {
		this.posting = posting;
	}

	public Content getCurrentContent() {
		return currentContent;
	}
	public void setCurrentContent(Content content) {
		currentContent = content;
	}
	
	public String toContentPage(int ID) {
		staticCurrentID = ID;
		return("konten?faces-redirect=true");
	}
	public String toEditPage(int ID){
		staticCurrentID = ID;
		return("edit?faces-redirect=true");
	}
	
	private void listPosting() throws SQLException {
		posting = new ArrayList<>();
		try (ResultSet rs = con.executeQuery("select * from tbl_posting order by `tanggal` desc")) {
			while(rs.next()) {
				Content temp = new Content();
				temp.setJudul(rs.getString("judul"));
				temp.setContent(rs.getString("konten"));
				temp.setTanggal(rs.getString("tanggal"));
				temp.setID(Integer.parseInt(rs.getString("id_post")));				
				temp.setPublished(Integer.parseInt(rs.getString("status_published"))==1 ? true : false);
				temp.setDeleted(Integer.parseInt(rs.getString("status_delete"))==1 ? true : false);
				
				posting.add(temp);
			}
		}		
		con.closeConnection();
	}
	
	private void addPublishedPost() {
		publishedPost = new ArrayList<>();
		for (Content content : posting) {
			if(content.isPublished() && !content.isDeleted())
				publishedPost.add(content);
		}
	}
	
	private void addUnpublishedPost() {
		unpublishedPost = new ArrayList<>();
		for (Content content : posting) {
			if (!content.isPublished() && !content.isDeleted())
				unpublishedPost.add(content);
		}
	}
	
	private void addDeletedPost() {
		deletedPost = new ArrayList<>();
		for (Content content : posting) {
			if (content.isDeleted())
				deletedPost.add(content);
		}
	}
	
	public String editPost() throws SQLException {
		int id=currentContent.getID();
//		String judul="'" + posting.get(currentIdx).getJudul() + "'";
//		String content="'" + posting.get(currentIdx).getContent() + "'";
//		String tanggal="'" + posting.get(currentIdx).getTanggal() + "'";
		
		String judul="'" + currentContent.getJudul() + "'";
		String content="'" + currentContent.getContent() + "'";
		String tanggal="'" + currentContent.getTanggal() + "'";
		
		con.executeUpdate("UPDATE tbl_posting SET judul=" + judul + ", tanggal=" + tanggal + ", konten=" + content +
				" WHERE id_post=" + id);
		return("index?faces-redirect=true");
	}
	
	private void changeCurrentContent(int id) {
		for (Content content : posting) {
			if (content.getID() == id) {
				currentContent = content;
				staticCurrentID = id;
				break;
			}
		}
	}

	public String deletePost(int ID) throws SQLException {
		staticCurrentID = ID;
		int id=currentContent.getID();
		//System.out.println(id);
		con.executeUpdate("DELETE FROM tbl_posting WHERE id_post=" + id);
		return("index?faces-redirect=true");
	}
	public String deletePostTemp(int ID) throws SQLException {
		con.executeUpdate("UPDATE tbl_posting SET status_delete= 1 WHERE id_post=" + ID);
		return("index?faces-redirect=true");
	}
	public String publishPost(int ID) throws SQLException {
		con.executeUpdate("UPDATE tbl_posting SET status_published= 1 WHERE id_post=" + ID);
		return("index?faces-redirect=true");
	}
	public String restorePost(int ID) throws SQLException {
		con.executeUpdate("UPDATE tbl_posting SET status_published = 0, status_delete= 0 WHERE id_post=" + ID);
		return("index?faces-redirect=true");
	}
}