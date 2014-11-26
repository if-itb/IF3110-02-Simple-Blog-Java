/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author user
 */
public class Comment {
    private int comment_id; // sets by SQL
    private String comment_date; // sets by connector.java
    private String comment_content; 
    private int comment_post_id;
    private String comment_email;
    private int comment_user_id;
    Comment(){}
    public void setCommentID(int value){
        comment_id = value;
    }
    public void setDate(String value){
        comment_date = value;
    }
    public void setContent(String value){
        comment_content = value;
    }
    public void setCommentPostID(int value){
        comment_post_id = value;
    }
    public void setEmail(String value){
        comment_email = value;
    }
    public void setUserID(int value){
        comment_user_id = value;
    }
    public int getCommentID(){
        return comment_id;
    }
    public String getDate(){
        return comment_date;
    }
    public String getContent(){
        return comment_content;
    }
    public int getCommentPostID(){
        return comment_post_id;
    }
    public String getEmail(){
        return comment_email;
    }
    public int getUserID(){
        return comment_user_id;
    }
}
