/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.Comment;
import Model.Comments;
import Model.User;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class AddCommentBean {
    
    @ManagedProperty(value="#{param.postId}")
    private String postId;

    @ManagedProperty(value="#{user}")
    private User user;
    
    private ArrayList<Comment> comments;
    
    private String name;
    private String email;
    private String text;

    public AddCommentBean() {
        
    }
    
    public String getName() {
        if (name==null) {
            if (!user.getRole().equals("guest")) {
                name = user.getUsername();
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        if (email==null) {
            if (!user.getRole().equals("guest")) {
                email = user.getEmail();
            }
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Comment> getComments() {
        if (comments==null) {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
        
            try {
                dbManager.openConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AddCommentBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            comments = Comments.getInstance().getCommentByPostId(Integer.parseInt(postId));
        
            try {
                dbManager.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AddCommentBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    public void addComment() {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        
            try {
                dbManager.openConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AddCommentBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            Comment comment = new Comment();
            comment.setName(name);
            comment.setEmail(email);
            comment.setText(text);
            comment.setCreatorId(user.getId());
            comment.setGuest(user.getRole().equals("guest"));
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            comment.setTimestamp(ts);
            comment.setPostId(Integer.parseInt(postId));
            
            Comments.getInstance().addComment(comment);
            
            comments.add(comment);
            
            try {
                dbManager.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(AddCommentBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
}
