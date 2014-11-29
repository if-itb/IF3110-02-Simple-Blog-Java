package somepackage;

import javax.faces.bean.ManagedBean;

@ManagedBean (name="managedBean", eager=true)
public class Content {
	
	private int ID;
	private String judul, content, descContent;
	private String tanggal, stringTanggal;
	private boolean published, deleted;
	
	public int getID() {
		return ID;
	}
	public String getJudul() {
		return judul;
	}
	public String getContent() {
		return content;
	}
	public String getTanggal() {
		return tanggal;
	}
	public String getDescContent() {
		int max = 200;
		if (content.length() <= max) {
			descContent = content;
		} else {
			descContent = content.substring(0, max);
			descContent += "...";
		}
		return descContent;
	}
	public String getStringTanggal() {
		String tahun = tanggal.substring(0, 4);
		int stanggal = Integer.parseInt(tanggal.substring(8,10));		
		int bulan = Integer.parseInt(tanggal.substring(5,7));
		stringTanggal = stanggal + " " + convertBulan(bulan) + " " + tahun;
		return stringTanggal;
	}
	public boolean isPublished() {
		return published;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public void setContent(String content) {
		this.content = content.replaceAll("\n", "<br/>");
	}
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	public void setPublished(boolean published) {
		this.published=published;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
