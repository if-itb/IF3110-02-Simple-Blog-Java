/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lingga;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jelink
 */

@ManagedBean(name="comment",eager=true)
@SessionScoped
public class Comment {
    int id_komentar;
    String nama;
    String email;
    String komentar;
    String timestamp;
    int post_id;
    
    public Comment(){
	
    }

    public Comment(int id_komentar, String nama, String email, String komentar, String timestamp, int post_id) {
	this.id_komentar = id_komentar;
	this.nama = nama;
	this.email = email;
	this.komentar = komentar;
	this.timestamp = timestamp;
    }
    
    public void init(User pengguna) {
	nama = pengguna.getName();
	email = pengguna.getEmail();
	komentar = "";
    }

    public int getPost_id() {
	return post_id;
    }

    public void setPost_id(int post_id) {
	this.post_id = post_id;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

    public int getId_komentar() {
	return id_komentar;
    }

    public void setId_komentar(int id_komentar) {
	this.id_komentar = id_komentar;
    }

    public String getNama() {
	return nama;
    }

    public void setNama(String name) {
	this.nama = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getKomentar() {
	return komentar;
    }

    public void setKomentar(String komentar) {
	this.komentar = komentar;
    }
    
    public void print(){
	System.out.println("cetak cetak");
    }
}
