/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Comment;

/**
 *
 * @author Ahmad
 */
@ManagedBean(name="commentController")
@SessionScoped
public class CommentController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String content;
    private String title;
    private String time;
    
    private DBController dbController;
     
    public CommentController() throws SQLException, ClassNotFoundException{
        dbController = DBController.getInstance();
    }
    
    public void addComment(Integer postId, String name, String email, String content) throws SQLException{
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();        
        String time = dateFormat.format(cal.getTime());
        
        getDbController().executeUpdate("INSERT INTO COMMENT (post_id, name, email, time, content) VALUES ("+postId+",'"+name+"','"+email+"','"+time+"','"+content+"')");
    }
    
    
    public List<Comment> getListComment(Integer postId) throws SQLException{
        ResultSet result = getDbController().executeQuery("SELECT * FROM `comment` where post_id="+postId+" ORDER BY id DESC");
        List<Comment> commentList = new ArrayList<>();
        while (result.next()) {
            Comment comment = new Comment();
            
            comment.setContent(result.getString("content"));
            comment.setEmail(result.getString("email"));
            comment.setName(result.getString("name"));
            comment.setTime(result.getString("time"));
            
            commentList.add(comment);
        }
        return commentList;
    }
    

    /**
     * @return the dbController
     */
    public DBController getDbController() {
        return dbController;
    }

    /**
     * @param dbController the dbController to set
     */
    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
}
