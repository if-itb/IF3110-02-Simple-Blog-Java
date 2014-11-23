/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;
import Database.*;
import java.util.Date;
import java.lang.Object;
import java.sql.Timestamp;
/**
 *
 * @author wira gotama
 */
public class Comment {
    private int id;
    private int post_id;
    private String creator;
    private String email;
    private String text;
    private Timestamp timestamp;
    
    public Comment() {}
    
    public Comment(int id, int post_id, String creator, String email, String text, Timestamp timestamp) {
        this.id = id;
        this.post_id = post_id;
        this.creator = creator;
        this.email = email;
        this.text = text;
        this.timestamp = timestamp;
    }
    
    /* Setter */
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPostId(int post_id) {
        this.post_id = post_id;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setTimestampe(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    /* Getter */
    public int getId() {
        return id;
    }
    
    public int getPostId() {
        return post_id;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getText() {
        return text;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
}
