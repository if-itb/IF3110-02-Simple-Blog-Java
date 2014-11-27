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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import simpleblog.model.Post;
import simpleblog.model.User;

/**
 *
 * @author Luqman
 */
@ManagedBean
@SessionScoped
public class PostController {
     private DataSource ds;
     private String title;
     private String date;
     private String content;
     
    /**
     * Creates a new instance of PostController
     */
     
     public PostController(){
         title = new String();
         date = new String();
         content = new String();
     }
     
    public List<Post> getPostList() throws SQLException, NamingException
    {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        
        Connection con = ds.getConnection();
        PreparedStatement ps 
            = con.prepareStatement(
                "SELECT * FROM post WHERE status=1"); 
	ResultSet result =  ps.executeQuery();
        
        List<Post> list = new ArrayList<Post>();
        
        while(result.next())
        {
            Post post = new Post();
            
            post.setId(result.getInt("id"));
            post.setTitle(result.getString("title"));
            post.setContent(result.getString("content"));
            post.setDate(result.getTimestamp("date").toString());
            post.setUserId(result.getInt("user_id"));
            
            list.add(post);
        }
        return list;
    } 
    
    public boolean insertPost(User user) throws NamingException, SQLException{
         try {
            Date dates = new Date();
            date = date + " " + dates.getHours() + ":" + dates.getMinutes() + ":" + dates.getSeconds();
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO post (user_id, title, date, content, status) VALUES ('"+ user.getId() +"','"+ title +"','"+ date +"','"+ content +"','0')");
            ps.executeUpdate();
            con.close();
            ps.close();
            return true;
         } catch (Exception e) {
            e.printStackTrace();
            return false;
         } 
    }
    
    public String actionInsertPost(User user) throws NamingException, SQLException{
        if(insertPost(user)){
            return "index";
        }
        else return "";
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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
}
