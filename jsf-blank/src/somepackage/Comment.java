package somepackage;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Comment {
	
	private String nama, email, tanggal, komentar, stringTanggal;
	private int postID;
	
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
	public int getPostID() {
		return postID;
	}
	public String getStringTanggal() {
		String tahun = tanggal.substring(0, 4);
		int stanggal = Integer.parseInt(tanggal.substring(8,10));		
		int bulan = Integer.parseInt(tanggal.substring(5,7));
		String waktu = tanggal.substring(11,16);
		stringTanggal = stanggal + " " + convertBulan(bulan) + " " + tahun + " " + waktu;
		return stringTanggal;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	private String convertBulan(int bulan) {
		switch(bulan) {
			case 1 : return "Januari";
			case 2 : return "Februari";
			case 3 : return "Maret";
			case 4 : return "April";
			case 5 : return "Mei";
			case 6 : return "Juni";
			case 7 : return "Juli";
			case 8 : return "Agustus";
			case 9 : return "September";
			case 10 : return "Oktober";
			case 11 : return "November";
			case 12 : return "Desember";
			default : return "";
		}
	}
	
}
