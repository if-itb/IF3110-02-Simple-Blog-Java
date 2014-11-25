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
    private Vector<Comment> comments;
    
    public Post() {}
    
    public Post(int id, String title, int creatorId, String text, Timestamp timestamp) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.text = text;
        this.timestamp = timestamp;
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
    
    /* Other */
    public void addComment(Comment c) {
        comments.add(c);
    }
    
    public void removeComment(int comment_id) {
        boolean stop = false;
        for (int i=0; i<=comments.size() && !stop; i++) {
            if (comments.get(i).getId()==comment_id) {
               comments.remove(i);
               stop = true;
            }
        }
        //update ke database, asumsi koneksi ke database udah established
        if (stop) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
            String condition = "id="+comment_id;
            try {
                    databaseAccess.deleteRecords("Comments", condition);
            } catch (SQLException e1) {
                    e1.printStackTrace();
            }
        }
    }
    
    public void loadComment() {
    /* Asumsi : koneksi diurus oleh kelas yang memanggil atau controller */
       
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        comments = new Vector<Comment>();
        //query sql
        ArrayList<String> columns = new ArrayList(Arrays.asList("id", "post_id", "creator", "email", "text", "timestamp"));
        String condition = "post_id=" + this.id; // jika ada kondisi dapat dimasukkan seperti pada
        ResultSet result = null;
        try {
                result = databaseAccess.selectRecords("Comments", columns, condition);
        } catch (SQLException e1) {
                e1.printStackTrace();
        }
        //fetch the result
        try {
                while (result.next()) {
                    Comment c = new Comment(result.getInt("id"), result.getInt("post_id"), result.getString("creator"),
                                result.getString("email"), result.getString("text"), result.getTimestamp("timestamp"));
                    addComment(c);
                }
        } catch (SQLException e1) {
                e1.printStackTrace();
        }
    }
    
    public void saveComment() {
    /* Asumsi : koneksi diurus oleh kelas yang memanggil atau controller */
        
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("id"));
        
        for (int i=0; i<=comments.size(); i++) {
            String condition = "post_id=" + comments.get(i).getId();
            ResultSet result = null;
            
            //cek apakah comment yang bersangkutan ada di database atau tidak
            try {
                result = databaseAccess.selectRecords("Comments", columns, condition);
            } catch (SQLException ex) {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (result!=null) { //update
                updateCommentDB(comments.get(i));
            }
            else { //insert
                insertCommentDB(comments.get(i));
            }
        }
    }
    
    private void updateCommentDB(Comment c) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("text", "timestamp"));
        ArrayList<String>values = new ArrayList(Arrays.asList(c.getText(), c.getTimestamp()));
        String condition = "id="+c.getId();
        try {
                databaseAccess.updateRecords("Comments", columns, values, condition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insertCommentDB(Comment c) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("id", "post_id", "creator", "email", "text", "timestamp"));
        ArrayList<String> values = new ArrayList(Arrays.asList(c.getId(), c.getPostId(), c.getCreator(),
                c.getEmail(), c.getText(), c.getTimestamp()));
        try {
            databaseAccess.insertRecords("Comments", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edit() { //edit utk bagian JSP?
    
    }
}
