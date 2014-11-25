package com.blog.simple.model;

import java.util.Date;

public class Post {
	private int postID;
	private String Judul;
	private String Konten;
	private Date Tanggal;
	private String Status;
	public String Preview;
	
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getJudul() {
		return Judul;
	}
	public void setJudul(String judul) {
		Judul = judul;
	}
	public String getKonten() {
		return Konten;
	}
	public void setKonten(String konten) {
		Konten = konten;
	}
	public Date getTanggal() {
		return Tanggal;
	}
	public void setTanggal(Date tanggal) {
		Tanggal = tanggal;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPreview() {
		return Preview;
	}
	public void setPreview(String preview) {
		Preview = preview;
	}
	
}
