package somepackage;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
public class PostAdder {
	
	private Connector con;
	private String judul, content, tanggal;
	
	
	public PostAdder() {
		try {
			con = new Connector("db_simple_blog", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTanggal() {
		return tanggal;
	}
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	
	public String addPost() throws SQLException {
		tanggal = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tanggal");
		String sjudul = "'" + judul + "'";
		String scontent = "'" + content + "'";
		String stanggal = "'" + tanggal + "'";
		con.executeUpdate("INSERT into tbl_posting (judul, tanggal, konten) VALUES ("+sjudul+", "+stanggal+", "+scontent+")");
		
		return("index?faces-redirect=true");
	}
	
}
