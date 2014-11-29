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
import javax.faces.bean.SessionScoped;
import model.Comment;

/**
 *
 * @author Eldwin Christian
 * Kelas Comment Controller, menambah komentar serta mengambil list
 * komentar dari basis data
 */
@ManagedBean(name="commentController")
@SessionScoped
public class CommentController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    // the comment attributes
    private String content;
    private String title;
    private String time;
    
    private final DBController dbController;
    
    public CommentController() throws SQLException, ClassNotFoundException{
        dbController = DBController.getInstance();
    }
    
    public void addComment(Integer postId, String name, String email, String content) throws SQLException{
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();        
        String _time = dateFormat.format(cal.getTime());
        
        dbController.executeUpdate("INSERT INTO COMMENT "
                + "(`post_id`, `name`, `email`, `time`, `content`) VALUES "
                + "(?, ?, ?, ?, ?)",
                new String[]{postId.toString(), name, email, _time, content});
    }
    
    // get all comments for specific post
    public List<Comment> getListComment(Integer postId) throws SQLException{
        ResultSet result = dbController.executeQuery("SELECT * FROM `comment` "
                + "where `post_id` = ? ORDER BY `id` DESC",
                new String[]{postId.toString()});
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
    
    /* Kelompok Getter-Setter */
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
