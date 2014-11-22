/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Anggi
 */
@ManagedBean(name = "post", eager = true)
@RequestScoped
public class Post {
	private int IDPost;
	private String title = "TITLE COBA";
	private String content = "HAHAHAA";
	private String date = "23";
	
	public Post (){
		
	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setContent(String content){
		this.content = content;
	}
}
