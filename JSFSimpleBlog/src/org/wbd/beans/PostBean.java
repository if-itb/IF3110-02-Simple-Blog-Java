package org.wbd.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.wbd.Post;

@ManagedBean
public class PostBean {
	private Post post;
	
	private int selectedPost;
	
	@ManagedProperty("#{mockPostGetterBean}")
	private MockPostGetterBean postGetter;

	public int getSelectedPost() {
		return selectedPost;
	}

	public void setSelectedPost(int selectedPost) {
		this.selectedPost = selectedPost;
	}

	public Post getPost() {
		return post;
	}
	
	public MockPostGetterBean getPostGetter() {
		return postGetter;
	}

	public void setPostGetter(MockPostGetterBean postGetter) {
		this.postGetter = postGetter;
	}

	public List<Post> getPosts() {
		return postGetter.getListPosts();
	}
	
	public void view() {
		post = postGetter.getPost(selectedPost);
	}
	
	public String hapus() {
		postGetter.deletePost(selectedPost);
		return "index";
	}
}
