/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author adwisatya
 */
@ManagedBean
@RequestScoped

public class PostBean {
	private String Judul;
	private Date Tanggal;
	private String Konten;
	private String Owner;
	private int id;
	private boolean valid;
	
	public String getJudul() {
		if (!valid){
			return "404 POST NOT FOUND";
		} else {
			return Judul;
		}
	}
	public void setJudul(String judul) {
		Judul = judul;
	}
	public String getKonten() {
		if (!valid){
			return ""
			+ "The Post you're looking for is <b>NOT</b> found in our database.<br>"
			+ "<br>"
			+ "<br>"
			+ "Do you perhaps follow an expired link?<br>"
			+ "Maybe you input the wrong post id?<br>"
			+ "Either way, this is not the page you are looking for.<br>"
			+ "<h:link outcome=\"index.xhtml\" value=\"Click here to go back to homepage.\"/>";
		} else {
			return Konten;
		}
	}
	public void setKonten(String konten) {
		Konten = konten;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		ViewPost(id);
		this.id = id;
	}
	public boolean isValid() {
		return valid;
	}
	public PostBean(){
		try {
			valid=false;
			Tanggal=new Date();
			Post.Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String NewPost(String a, String b, String c, String d){
		try {
			Judul=a;
			Tanggal=new SimpleDateFormat("dd-MM-yyyy").parse(b);
			Konten=c;
			Owner = d;
			Post.NewPost(Judul,Tanggal,Konten, Owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index.xhtml?faces-redirect=true";
	}
	public void ViewPost(int id){
		try {
			Paket tp=Post.ViewPost(id);
			valid=tp.valid;
			if (tp.valid){
				Judul=tp.Judul;
				Tanggal=tp.Tanggal;
				Konten=tp.Konten;
				this.id=id;
			}
		} catch (Exception e) {
			valid=false;
			e.printStackTrace();
		}
		
	}
	public String DisplayContent(){
		return Konten.replaceAll("\n","<br/>");
	}
	public String EditPost(int id, String a, String b, String c){
		try {
			Judul=a;
			Tanggal=new SimpleDateFormat("dd MMMM yyyy").parse(b);
			Konten=c;
			Post.EditPost(id,Judul,Tanggal,Konten);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "post.xhtml?faces-redirect=true&amp;includeViewParams=true";
	}
	public void DeletePost(int id){
		try {
			Post.DeletePost(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getTanggal() {
		SimpleDateFormat tp=new SimpleDateFormat("dd MMMM y");
		if (!valid){
			return tp.format(new Date());
		} else {
			return tp.format(Tanggal);
		}
	}
	public void setTanggal(String tanggal) {
		SimpleDateFormat tp=new SimpleDateFormat("dd-MM-yyyy");
		try {
			Tanggal = tp.parse(tanggal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Paket> manypost;
	public ArrayList<Paket> getManypost() {
		try {
			manypost=Post.ViewMany();
			return manypost;
		} catch (Exception e) {
			e.printStackTrace();
			manypost= new ArrayList<Paket>();
			Paket t=new Paket();
			t.Judul="Invalid Post";
			t.Konten="<p>Something's went wrong<br><br>Just ignore this for the time being</p>";
			t.id=0;
			manypost.add(t);
			return manypost;
		}
	}
	public ArrayList<Paket> listManyPost(){
		try {
			manypost=Post.ViewMany();
			return manypost;
		} catch (Exception e) {
			e.printStackTrace();
			manypost= new ArrayList<Paket>();
			Paket t=new Paket();
			t.Judul="Invalid Post";
			t.Konten="<p>Something's went wrong<br><br>Just ignore this for the time being</p>";
			t.id=0;
			manypost.add(t);
			return manypost;
		}
	}
}

