package org.wbd;

import java.util.Date;

public class Post {
	private int id;
	private String judul;
	private Date tanggal;
	private String konten;
	
	public Post(int id, String j, Date t, String k) {
		this.setId(id);
		this.setJudul(j);
		this.setTanggal(t);
		this.setKonten(k);
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getKonten() {
		return konten;
	}

	public void setKonten(String konten) {
		this.konten = konten;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
