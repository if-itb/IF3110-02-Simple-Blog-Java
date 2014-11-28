/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.beans;

/**
 *
 * @author daniar heri
 */
public class Komentar {
    
	private int comment_id;
	private String user_id;
	private String email;
	private int post_id;
	private String content;
	private String date;
        
        public Komentar(int comment_id, int post_id, String user_id, String email,
							String content, String date){
			
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.user_id = user_id;
		this.content = content;
		this.date = date;
		this.email = email;
	}
    
	public String getEmail(){
		return email;
	}
		
	public int getComment_id(){
		return comment_id;
	}
        
	public int getPost_id() {
		return post_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}
}
