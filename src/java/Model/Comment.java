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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author wira gotama
 */
public class Comment {
    private int id;
    private int post_id;
    private int creatorId;
    private String email;
    private String text;
    private String name;
    private boolean guest;

    public int getPost_id() {
        return post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }
    private Timestamp timestamp;
    
    public Comment() {
        guest = true;
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate=null;   
        try {
            parsedDate = dateFormat.parse("2012-01-01");
        } catch (ParseException ex) {
            Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.timestamp = new java.sql.Timestamp(parsedDate.getTime());
    }
    
    public Comment(int id, int post_id, int creator, String email, String text, Timestamp timestamp,String nama,boolean guest) {
        this.id = id;
        this.post_id = post_id;
        this.creatorId = creator;
        this.email = email;
        this.text = text;
        this.timestamp = timestamp;
        this.name = nama;
        this.guest = guest;
        this.timestamp = timestamp;
    }
    
    /* Setter */
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPostId(int post_id) {
        this.post_id = post_id;
    }
    
    public void setCreatorId(int creator) {
        this.creatorId = creator;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    /* Getter */
    public int getId() {
        return id;
    }
    
    public int getPostId() {
        return post_id;
    }
    
    public int getCreatorId() {
        return creatorId;
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
    
    public String getTimeString() {
        if (timestamp==null){
            return "";
        }
        String timeString = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
        return timeString;
    }
    
    public void setTimeString(String newTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = dateFormat.parse(newTime);   
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
