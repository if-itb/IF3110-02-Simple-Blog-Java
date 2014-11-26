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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import simpleblog.model.ViewPost;

/**
 *
 * @author Luqman
 */
@ManagedBean
@RequestScoped
public class ViewPostController {
    private int postId;
    /**
     * Creates a new instance of ViewPostController
     */
    public ViewPost getPost() throws NamingException, SQLException
    {
        System.out.println("getPost()");
        DataSource ds;
        ViewPost post = new ViewPost();
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        
        Connection con = ds.getConnection();
        PreparedStatement ps 
            = con.prepareStatement(
                "SELECT * FROM post WHERE status="+postId); 
	ResultSet result =  ps.executeQuery();
        if(result.first())
        {
            post.setId(result.getInt("id"));
            post.setTitle(result.getString("title"));
            post.setContent(result.getString("content"));
            post.setDate(result.getTimestamp("date").toString());
        }
        return post;
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
    
}
