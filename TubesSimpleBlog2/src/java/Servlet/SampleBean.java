/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

/**
 *
 * @author Anggi
 */
public class SampleBean {
	private String nama;
	private String role;
        private int postid;
        private String email;
        
        public String getEmail(){
            return email;
        }
	public void setEmail(String email){
            this.email = email;
        }
	public String getNama() {
	 return nama;
	}
	
	public void setNama(String nama) {
	 this.nama = nama;
	}
	
	public String getRole() {
	 return role;
	}
	
	public void setRole(String role) {
	 this.role = role;
	}
        
        public int getPostid(){
            return postid;
        }
        public void setPostid(int id){
            this.postid = id;
        }
        public String Logout(){
            this.nama = "";
            this.role = "";
            return "index?faces-redirect=true";
        }
}
