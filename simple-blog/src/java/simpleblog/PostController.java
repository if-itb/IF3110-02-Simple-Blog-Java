/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simpleblog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import simpleblog.model.Post;
import simpleblog.model.User;

/**
 *
 * @author Luqman
 */
@ManagedBean
@ViewScoped
public class PostController implements Serializable {
     private DataSource ds;
     private String title;
     private String date;
     private String content;
     private String post_id;
     private Part image;
     private Post post;
     
    /**
     * Creates a new instance of PostController
     */
     
     public PostController(){
         title = new String();
         date = new String();
         content = new String();
         post = new Post();
    }
     
    public void setNull(){
        title = null;
        date = null;
        content = null;
    }
     
    public void getDatabasePost(){
        try{
            //get database connection
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");

            System.out.println("SELECT * FROM post WHERE post.id = '"+ getPost_id() + "'");
            
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM post WHERE post.id = '"+ getPost_id() + "'"); 
            ResultSet result =  ps.executeQuery();
            result.next();
            post.setId(result.getInt("id"));
            post.setUserId(result.getInt("user_id"));
            post.setTitle(result.getString("title"));
            
            String[] date = result.getString("date").split(" ");
            
            post.setDate(date[0]);
            post.setContent(result.getString("content"));
            post.setStatus(result.getInt("status"));

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            ps = con.prepareStatement("SELECT LAST_INSERT_ID();");
            ResultSet result = ps.executeQuery();
            result.first();
            uploadImage(result.getInt(1));
            
            con.close();
            
            ps.close();
            return true;
         } catch (Exception e) {
            e.printStackTrace();
            return false;
         } 
    }
    

    public void uploadImage(int imageId) throws IOException
    {
        String fileExtension = getFileExtension(getImage());
        String basePath = "D:" + File.separator;
        
        File outputFilePath = new File(getImagePath()+ File.separator + "resources"+File.separator+"images" + File.separator + imageId + "." + fileExtension);
        InputStream inputStream = null;
	OutputStream outputStream = null;
        System.out.println("Nama file " + outputFilePath.getAbsolutePath());
        
        try {
            inputStream = image.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024*1024*5];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            
        } catch (IOException e) {
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    
    public String getImagePath()
    {
        ExternalContext ctx;
        ctx = FacesContext.getCurrentInstance().getExternalContext(); 
        String absoluteWebPath = ctx.getRealPath("/");
        return  absoluteWebPath;
    }
    
    private String getFileExtension(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                String[] subFileName = fileName.split("\\.");
                return subFileName[subFileName.length-1];
            }
        }
        return null;
    }
    

    public boolean editPost() throws NamingException, SQLException{
         try {
            Date dates = new Date();
            post.setDate(post.getDate() + " " + dates.getHours() + ":" + dates.getMinutes() + ":" + dates.getSeconds());
            System.out.println("UPDATE post SET title= '"+ post.getTitle() +"', date= '"+ post.getDate() +"', content= '"+ post.getContent() +"' WHERE id = '"+ post_id +"'");
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(
                            "UPDATE post SET title= '"+ post.getTitle() +"', date= '"+ post.getDate() +"', content= '"+ post.getContent() +"' WHERE id = '"+ post_id +"'");
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
    
    public String actionEditPost() throws NamingException, SQLException{
        if(editPost()){
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

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * @return the post_id
     */
    public String getPost_id() {
        return post_id;
    }

    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    
    public void softDelete(){
        try {
            System.out.println("UPDATE post SET status = 2 WHERE id =" + post_id);
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(
                            "UPDATE post SET status = 2 WHERE id =" + post_id);
            ps.executeUpdate();
            con.close();
            ps.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
    
    public void hardDelete(int id){
        try {
            System.out.println("DELETE FROM post WHERE id = "+post_id);
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(
                            "DELETE FROM post WHERE id = "+id);
            ps.executeUpdate();
            con.close();
            ps.close();
         } catch (Exception e) {
            e.printStackTrace();
         } 
    }
    
    public void redirectIndex() throws Exception{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("index.xhtml");
    }
    
    public List<Post> getSoftDeletedPost() throws SQLException, NamingException
    {
        DataSource ds;
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        
        Connection con = ds.getConnection();
        PreparedStatement ps 
            = con.prepareStatement(
                "SELECT * FROM post WHERE status=2"); 
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
    
    public String restorePost(int post_id)
    {
        DataSource ds;
        try {
            Date dates = new Date();
            System.out.println("UPDATE post SET status=1 WHERE id = '"+ post_id +"'");
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
            
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE post SET status=1 WHERE id = '"+ post_id +"'");
            ps.executeUpdate();
            con.close();
            ps.close();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } 
    }
    
}
