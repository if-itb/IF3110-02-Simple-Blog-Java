package org.wbd.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.wbd.helper.DatabaseHelper;
import org.wbd.model.Post;

public class DeletedPostsController {

	private DatabaseHelper dbhelp;
	
	public DeletedPostsController() {
		dbhelp = DatabaseHelper.getInstance();
	}
	
	public List<Post> getPosts() {
		String username = "test";
		return dbhelp.getDeletedPosts(username);
	}
	
	public String batalHapus(int postId) {
		dbhelp.undoDeletePost(postId);
		return "deleted";
	}
	
	public String hapus(int postId) {
		dbhelp.finalDeletePost(postId);
		return "deleted";
	}
}
