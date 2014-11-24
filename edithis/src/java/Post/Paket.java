/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author adwisatya
 */
@ManagedBean
@RequestScoped

public class Paket{
	public String Judul;
	public Date Tanggal;
	public String Konten;
	public String Owner;
	public int id;
	public boolean valid;
	public String getJudul(){
		return Judul;
	}
	public String getTanggal(){
		return new SimpleDateFormat("d MMMM y").format(Tanggal);
	}
	public String getKonten(){
		if (Konten.length()>265){
			return Konten.substring(0, 250);
		} else {
			return Konten;
		}
	}
	public int getId(){
		return id;
	}
	public String getOwner(){
		return Owner;
	}
	public String isReadMore(){
		if (Konten.length()>250){
			return "&hellip;<a href=\"post.xhtml?id="+id+"\">Read More</a>";
		} else {
			return "";
		}
	}
}
