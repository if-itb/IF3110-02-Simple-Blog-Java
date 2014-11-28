package org.wbd.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.wbd.Post;

@ManagedBean
@ApplicationScoped
public class MockPostGetterBean {
	private Map<Integer, Post> posts;
	private int nextId;
	
	public MockPostGetterBean() {
		posts = new HashMap<>();
		nextId = 0;
		addPost(new Post(getNextId(), "Halo", new Date(), "Ini adalah konten dari post Halo."));
		addPost(new Post(getNextId(), "Lorem", new Date(), "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti, praesentium."));
		addPost(new Post(getNextId(), "Ipsum", new Date(), "This is some text. Nessun dorma."));
	}
	
	public void addPost(Post post) {
		posts.put(post.getId(), post);
	}
	
	public List<Post> getListPosts() {
		List<Post> result = new ArrayList<>();
		for (Post p : posts.values()) {
			result.add(p);
		}
		return result;
	}
	
	public Post getPost(int id) {
		return posts.get(id);
	}
	
	public void savePost(Post post) {
		posts.put(post.getId(), post);
	}

	public Map<Integer, Post> getPosts() {
		return posts;
	}
	
	public void deletePost(int id) {
		posts.remove(id);
		nextId = id;
	}

	public int getNextId() {
		while (posts.containsKey(nextId)) nextId++;
		return nextId;
	}

}
