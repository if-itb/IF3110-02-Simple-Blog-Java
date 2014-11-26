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
public class Post {
    private int post_id;
    private String title;
    private String date;
    private String content;
    private boolean category;
    private int post_author_id;
    Post(){
        
    }
    public void setPostID(int value){
        post_id = value;
    }
    public void setTitle(String value){
        title = value;
    }
    public void setDate(String value){
        date = value;
    }
    public void setContent(String value){
        content = value;
    }
    public void setCategory(boolean value){
        category = value;
    }
    public void setAuthorID(int value){
        post_author_id = value;
    }
    public int getPostID(){
        return post_id;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public String getContent(){
        return content;
    }
    public boolean getCategory(){
        return category;
    }
    public int getAuthorID(){
        return post_author_id;
    }
}
