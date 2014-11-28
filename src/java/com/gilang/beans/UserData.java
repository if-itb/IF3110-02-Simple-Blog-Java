/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.beans;

/**
 *
 * @author Gilang
 */
public class UserData {
	
	private String user_id;
	private int role;
	private String email;
	
	public UserData(String user_id, int role, String email){
		this.user_id = user_id;
		this.role = role;
		this.email = email;
	}

	public String getUser_id() {
		return user_id;
	}

	public int getRole() {
		return role;
	}
        
	public String getEmail() {
		return email;
	}
}
