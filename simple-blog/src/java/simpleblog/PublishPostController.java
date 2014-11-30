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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import simpleblog.model.Post;

/**
 *
 * @author Luqman
 */
@ManagedBean
@ViewScoped
public class PublishPostController {

    public List<Post> getPostList() throws SQLException, NamingException
    {
        DataSource ds;
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        
        Connection con = ds.getConnection();
        PreparedStatement ps 
            = con.prepareStatement(
                "SELECT * FROM post WHERE status=0"); 
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
}
