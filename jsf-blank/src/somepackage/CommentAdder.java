package somepackage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean (name="commentAdder", eager=true)
public class CommentAdder {

	private Connector con;
	private String nama, email, tanggal, komentar;
	private int postID;
	
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
	
	private void resetValue() {
		nama="";
		email="";
		tanggal="";
		komentar="";
	}
	
}
