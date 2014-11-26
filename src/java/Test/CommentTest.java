/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import Database.DatabaseAccess;
import Model.Comment;
import Model.Comments;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinyu
 */
public class CommentTest {
     public static void main(String[] args){
         DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        /*Comment comment1 = new Comment();
        comment1.setName("Kevin Yudi Utama");
        comment1.setCreatorId(0);
        comment1.setEmail("kevin.kayu@gmail.com");
        comment1.setGuest(true);
        comment1.setPostId(1);
        comment1.setTimeString("2016-01-04");
        comment1.setText("Ini adalah komentar pertama");
        Comments.getInstance().addComment(comment1);
        
        comment1.setName("Kevin Yudi Utama");
        comment1.setCreatorId(1);
        comment1.setEmail("kevin.kayu@gmail.com");
        comment1.setGuest(false);
        comment1.setPostId(1);
        comment1.setTimeString("2016-01-07");
        comment1.setText("Ini adalah komentar kedua");
        Comments.getInstance().addComment(comment1);*/
        
        ArrayList<Comment> comments = Comments.getInstance().getCommentByPostId(18);
        
        for (Comment comment:comments) {
            System.out.println(comment.getName());
            System.out.println(""+comment.getCreatorId()+"");
            System.out.println(comment.getEmail());
            System.out.println(comment.getEmail());
            System.out.println(comment.isGuest()?"TRUE":"FALSE");
            System.out.println(comment.getPostId());
            System.out.println(comment.getTimeString());
            System.out.println(comment.getText());
            System.out.println("--------------------------------");
        }
        
        
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
