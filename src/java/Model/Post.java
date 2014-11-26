package Model;

import Database.*;
import java.sql.Timestamp;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author wira gotama
 */

@ManagedBean
@RequestScoped
public class Post {
    private int id;
    private String title;
    private int creatorId;
    private String text;
    private Timestamp timestamp;
    private boolean isPublished;
    private boolean isDeleted;
    
    public Post() {
        title = "";
        text = "";
        isPublished = false;
        isDeleted = false;
    }
    
    public Post(int id, String title, int creatorId, String text, Timestamp timestamp,boolean isPublished,boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.text = text;
        this.timestamp = timestamp;
        this.isPublished = isPublished;
        this.isDeleted = isDeleted;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    /* Setter */
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    /* Getter */
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
       return this.title;
    }
    
    public int getCreatorId() {
        return this.creatorId;
    }
    
    public String getText() {
        return this.text;
    }

    
    public String getTrimText() {
        if (text.length()>101){
            return text.substring(0, 100) +"...";
        }
        else{
            return text;
        }
    }
    
    public Timestamp getTimestamp() {
        return this.timestamp;
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
