package com.blog.simple.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="editBean")
@RequestScoped
public class EditBean {
	private String id;
	private String judul;
	private String konten;
	private String tanggal;
	private String status;
	
	public void dispatchPost() {
		setId(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "SELECT * FROM post WHERE id="+getId();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				setId(rs.getString("id"));
				setJudul(rs.getString("judul"));
				setKonten(rs.getString("konten"));
				setTanggal(rs.getDate("tanggal").toString());
				setStatus(rs.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String editPost() {
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "UPDATE post SET judul='"+judul+"', konten='"+konten+"', tanggal='"+tanggal+"' WHERE id="+getId();
			System.out.println(getKonten());
			System.out.println(getId());
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return "Unpublished";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getKonten() {
		return konten;
	}

	public void setKonten(String konten) {
		this.konten = konten;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
