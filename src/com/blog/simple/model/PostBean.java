package com.blog.simple.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param;

@ManagedBean(name="postBean")
@ViewScoped
public class PostBean {
	
	public List<Post> getPostList() {
		List<Post> list = new ArrayList<Post>();
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "SELECT * FROM post";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostID(rs.getInt("id"));
				post.setJudul(rs.getString("judul"));
				post.setKonten(rs.getString("konten"));
				post.setStatus(rs.getString("status"));
				post.setTanggal(rs.getDate("tanggal"));
				if(post.getKonten().length() >210) {
					post.setPreview(rs.getString("konten").substring(0,210));
				}
				else {
					post.setPreview(post.getKonten());
				}
				list.add(post);
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
		return list;
	}
	
	public List<Post> getPublishedPostList() {
		List<Post> list = new ArrayList<Post>();
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "SELECT * FROM post WHERE status='published'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostID(rs.getInt("id"));
				post.setJudul(rs.getString("judul"));
				post.setKonten(rs.getString("konten"));
				post.setStatus(rs.getString("status"));
				post.setTanggal(rs.getDate("tanggal"));
				if(post.getKonten().length() <210) {
					post.setPreview(post.getKonten());
				}
				else {
					post.setPreview(rs.getString("konten").substring(0,210));
				}
				list.add(post);
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
		return list;
	}
	
	public List<Post> getUnpublishedPostList() {
		List<Post> list = new ArrayList<Post>();
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "SELECT * FROM post WHERE status='unpublished'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostID(rs.getInt("id"));
				post.setJudul(rs.getString("judul"));
				post.setKonten(rs.getString("konten"));
				post.setTanggal(rs.getDate("tanggal"));
				post.setStatus(rs.getString("status"));
				if(post.getKonten().length() <210) {
					post.setPreview(post.getKonten());
				}
				else {
					post.setPreview(rs.getString("konten").substring(0,210));
				}
				list.add(post);
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
		return list;
	}
	
	public void deletePost() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "DELETE FROM post WHERE id="+id;
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
		try {
			FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void publishPost() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog", "root", "");
			String sql = "UPDATE post SET status='published' WHERE id="+id;
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
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().dispatch("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
