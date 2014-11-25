/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Comment;
import model.database.MySQL;

/**
 *
 * @author christangga
 */
@ManagedBean
@SessionScoped
public class CommentsController implements Serializable {

	/**
	 * Creates a new instance of CommentsController
	 */
	public CommentsController() {
	}

	public void createComment(int post_id, String name, String email, String comment) {
		MySQL mysql = new MySQL();
		
		mysql.createComment(post_id, name, email, comment);
	}
	
	public List<Comment> getAllComments(int post_id) {
		MySQL mysql = new MySQL();
	
		return mysql.getAllComments(post_id);
	}

	public boolean deleteComment(int id) {
		MySQL mysql = new MySQL();
		
		return mysql.deleteComment(id);
	}

}
