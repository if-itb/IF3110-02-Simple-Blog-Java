/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sakurai
 */
@ManagedBean(name = "Viewpost", eager = true)
@SessionScoped
public class ViewPost {
    private int PostID;
    
    public ViewPost(){
        
    }
    
    public void setPostId(int PostID){
        this.PostID = PostID;
    }
    
    public int getPostId(){
        return PostID;
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException{
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/blog";
            String user = "root";
            String password = "";
            conn =  DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | InstantiationException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public void Redirect(int PostID) throws IOException{
        setPostId(PostID);
        ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
        extcon.redirect("/SImpleBlog/ViewPost.xhtml");
    }
    
    public Post getPost() throws ClassNotFoundException, SQLException, IllegalAccessException{
        ResultSet result;
        Post post = new Post();
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "Select * From post where ID = " + PostID + ";";
            result = stmt.executeQuery(query);
            while(result.next()){
              post.setId(result.getInt(1));
              post.setJudul(result.getString(2));
              post.setTanggal(result.getString(3));
              post.setContent(result.getString(4));
              post.setAuthor(result.getString(5));
              post.setStatus(result.getString(6));
            }
            conn.close();
            
        } catch(SQLException e){
            System.err.println(e);
        }
        
        return post;
    }
}
