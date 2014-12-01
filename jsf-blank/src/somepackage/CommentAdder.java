package somepackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import adminPackage.UserLoader;

@ManagedBean (name="commentAdder", eager=true)
public class CommentAdder {

	private Connector con;
	private String nama, email, tanggal, komentar;
	private int postID;
	private UserLoader user;
	
	public CommentAdder() throws SQLException {
		con = new Connector("db_simple_blog", "root","");
		postID = ContentLoader.staticCurrentID;
	}


	public String getNama() {
		return nama;
	}


	public String getEmail() {
		return email;
	}


	public String getTanggal() {
		return tanggal;
	}


	public String getKomentar() {
		return komentar;
	}


	public void setNama(String snama) {
		this.nama = snama;
	}


	public void setEmail(String semail) {
		this.email = semail;
	}


	public void setTanggal(String stanggal) {
		this.tanggal = stanggal;
	}


	public void setKomentar(String skomentar) {
		this.komentar = skomentar;
	}


	public void addKomen() throws SQLException {
		String snama = "'" + nama + "'";
		String semail = "'" + email + "'";
		String skomen = "'" + komentar + "'";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		String stanggal = "'" + dateFormat.format(date) + "'";
		
		String sid = "'" + postID + "'";
		con.executeUpdate("INSERT INTO tbl_comment (id_post, nama, email, tanggal, komentar) "
				+ "VALUES ("+sid+", "+snama+", "+semail+", "+stanggal+", "+skomen+")");
		resetValue();
	}
	
	public void addKomenAdmin() throws SQLException {
		String snama = "'" + getNamaDB(getValueCookie()) + "'";
		String semail = "'" + getEmailDB(getValueCookie()) + "'";
		String skomen = "'" + komentar + "'";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println(snama + " " + semail);
		
		String stanggal = "'" + dateFormat.format(date) + "'";
		
		String sid = "'" + postID + "'";
		con.executeUpdate("INSERT INTO tbl_comment (id_post, nama, email, tanggal, komentar) "
				+ "VALUES ("+sid+", "+snama+", "+semail+", "+stanggal+", "+skomen+")");
		resetValue();
	}
	
	private void resetValue() {
		nama="";
		email="";
		tanggal="";
		komentar="";
	}
	
	public String getNamaDB(String xname) throws SQLException{
		String namadb = ""; 
		try (ResultSet rs = con.executeQuery("select * from tbl_user where username = '" + xname + "'")) {
			while(rs.next()) {
				namadb = rs.getString("name");
			}
		}
		con.closeConnection();
		
		return namadb;
	}
	
	public String getEmailDB(String xname) throws SQLException{
		String emaildb = ""; 
		try (ResultSet rs = con.executeQuery("select * from tbl_user where username = '" + xname + "'")) {
			while(rs.next()) {
				emaildb = rs.getString("email");
			}
		}
		con.closeConnection();
		
		return emaildb;
	}
	
	public String getValueCookie(){
		HttpServletRequest httpServletRequest = 
		(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();   
		
		Cookie[] cookies = httpServletRequest.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++){
				if (cookies[i].getName().equalsIgnoreCase("userName")){
					cookieValue = cookies[i].getValue(); 
				}
			}
		}
		
		return cookieValue;
	}
	
}
