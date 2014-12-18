/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simpleblog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import simpleblog.model.Comment;
import simpleblog.model.Post;
import simpleblog.model.ViewPost;

/**
 *
 * @author Luqman
 */
@ManagedBean(eager=true)
@ViewScoped
public class ViewPostController {
    private int postId;
    private ViewPost viewPost;

    public void getPost()
    {
        try {
            DataSource ds;
            viewPost = new ViewPost();
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps
                    = con.prepareStatement(
                            "SELECT * FROM post WHERE id="+postId);
            ResultSet result =  ps.executeQuery();
            if(result.first())
            {
                viewPost.setId(result.getInt("id"));
                viewPost.setTitle(result.getString("title"));
                viewPost.setContent(result.getString("content"));
                viewPost.setDate(result.getTimestamp("date").toString());  
            }
             
 
        } catch (NamingException ex) {
            Logger.getLogger(ViewPostController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String inputName;
    private String inputEmail;
    private String inputComment;
       
    public List<Comment> getComment()
    {
        List<Comment> list = new ArrayList<Comment>();
        try {
            System.out.println("Bakemono "+inputName);
            System.out.println("Bakemono "+inputEmail);
            System.out.println("Bakemono "+inputComment);
            DataSource ds;
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            
            PreparedStatement ps;
            ResultSet result;
            if(getInputName() != null && getInputName() != "")
            {
                System.out.println("Bakemono : "+"INSERT INTO comment (post_id,name,email,content) VALUES ('"+postId+"','"+getInputName()+"','"+getInputEmail()+"','"+getInputComment()+"')");
                ps = con.prepareStatement("INSERT INTO comment (post_id,name,email,content) VALUES ('"+postId+"','"+getInputName()+"','"+getInputEmail()+"','"+getInputComment()+"')");
                ps.executeUpdate();
                setInputName(null);
                setInputEmail(null);
                setInputComment(null);
            }
            ps = con.prepareStatement("SELECT * FROM comment WHERE post_id="+postId);
            result =  ps.executeQuery();
            while(result.next())
            {
                Comment comment = new Comment();
                
                comment.setId(result.getInt("id"));
                comment.setPostId(result.getInt("post_id"));
                comment.setName(result.getString("name"));
                comment.setEmail(result.getString("email"));
                comment.setDate(result.getTimestamp("date").toString()); 
                comment.setContent(result.getString("content"));
                
                list.add(comment);
            }
             
            
        } catch (NamingException ex) {
            Logger.getLogger(ViewPostController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * @return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * @return the viewPost
     */
    public ViewPost getViewPost() {
        return viewPost;
    }

    /**
     * @param viewPost the viewPost to set
     */
    public void setViewPost(ViewPost viewPost) {
        this.viewPost = viewPost;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public String getInputComment() {
        return inputComment;
    }

    public void setInputComment(String inputComment) {
        this.inputComment = inputComment;
    }
    
}
