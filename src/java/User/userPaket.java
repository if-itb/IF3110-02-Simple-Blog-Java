/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author adwisatya
 */
public class userPaket {
	public String nama;
	public String username;
	public String password;
	public String email;
	public String status;
	public Date created;
	
	public String getNama(){
		return nama;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getEmail(){
		return email;
	}
	public String getStatus(){
		return status;
	}
	public String getTanggal(){
		return new SimpleDateFormat("y-MMM-d").format(created);
	}
}
