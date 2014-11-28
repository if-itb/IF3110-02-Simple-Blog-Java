/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilang
 */
public class PostList {
	
	private List<Post> postList;
	
	public PostList(){
		postList = new ArrayList<>();
	}
	
	public void add(Post post){
		postList.add(post);
	}
	
	public List<Post> getPostList(){
		return postList;
	}
}
