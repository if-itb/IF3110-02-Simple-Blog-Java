package org.wbd.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.wbd.Post;

@ManagedBean
public class CreatePostBean {
	private int newId;
	private String newJudul;
	private Date newTanggal;
	private String newKonten;
	
	private int selectedPost;
	
	@ManagedProperty("#{mockPostGetterBean}")
	private MockPostGetterBean postGetter;
	
	public CreatePostBean() {
		newTanggal = new Date();
		newId = -1;
	}
	
	public String getNewJudul() {
		return newJudul;
	}
	public void setNewJudul(String newJudul) {
		this.newJudul = newJudul;
	}
	public Date getNewTanggal() {
		return newTanggal;
	}
	public void setNewTanggal(Date newTanggal) {
		this.newTanggal = newTanggal;
	}
	public String getNewKonten() {
		return newKonten;
	}
	public void setNewKonten(String newKonten) {
		this.newKonten = newKonten;
	}
	
	public int getSelectedPost() {
		return selectedPost;
	}

	public void setSelectedPost(int selectedPost) {
		this.selectedPost = selectedPost;
	}

	public MockPostGetterBean getPostGetter() {
		return postGetter;
	}

	public void setPostGetter(MockPostGetterBean postGetter) {
		this.postGetter = postGetter;
	}

	public int getNewId() {
		return newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
	}

	public void init() {
		Post post = postGetter.getPost(selectedPost);
		setNewId(post.getId());
		setNewJudul(post.getJudul());
		setNewTanggal(post.getTanggal());
		setNewKonten(post.getKonten());
	}
	
	public String simpan() {
		if (newId == -1) newId = postGetter.getNextId();
		Post newPost = new Post(newId, newJudul, newTanggal, newKonten);
		postGetter.addPost(newPost);
		return "index?faces-redirect=true";
	}
}
