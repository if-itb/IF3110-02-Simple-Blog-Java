/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Database.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Posts {
    private Vector<Post> posts;
    
    private static Posts instance;
    
    private Posts() {
        posts = new Vector<Post>();
    }
    
    public static Posts getInstance() {
        if (instance==null){
            instance = new Posts();
        }
        return instance;
    }
    
    public Post findPost(int post_id)
    {
        
        ResultSet result=null;
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
            String condition = "Id_Post="+post_id;
            try {
            result = databaseAccess.selectAllRecords("post",condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }

        
        //fetch the result
        Post p = null;
        if (result!=null) {
            try 
            {
                while (result.next()) {
                
                    p = new Post(result.getInt("Id_Post"), result.getString("Judul") ,result.getInt("Id_User") ,  
                        result.getString("Konten"), result.getTimestamp("Tanggal"),result.getBoolean("Published")
                    ,result.getBoolean("Deleted"));
                    posts.add(p);
                }
            } 
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return p;
    }
    
    public void removePost(int post_id) {
       
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String condition = "Id_Post="+post_id;
        try {
            databaseAccess.deleteRecords("post", condition);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
    public void updatePostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String isPublished = p.isPublished() ? "TRUE" : "FALSE";
        String isDeleted = p.isDeleted() ? "TRUE" : "FALSE";
        ArrayList<String> columns = new ArrayList(Arrays.asList("Judul","Konten", "Tanggal","Published","Deleted"));
        ArrayList<String>values = new ArrayList(Arrays.asList("'"+p.getTitle()+"'",
                "'"+p.getText()+"'","'"+ p.getTimestamp()+"'",isPublished,isDeleted));
        String condition = "Id_Post="+p.getId();
        try {
                databaseAccess.updateRecords("post", columns, values, condition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     
    public void insertPostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String idUser = "" + p.getCreatorId() +"";
        ArrayList<String> columns = new ArrayList<String>(Arrays.asList("Judul","Tanggal","Konten","Id_User"));
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("'"+p.getTitle()+"'","'"+p.getTimeString()+"'",
                                "'"+p.getText()+"'",idUser));
        try {
            databaseAccess.insertRecords("post", columns, values);
        } catch (Exception ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Post> filter(int user_id){
        Vector<Post> posts = new Vector<Post>();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String condition = "user_id="+user_id; 
        ResultSet resultSet = null;
        
        try {
            resultSet = databaseAccess.selectAllRecords("post", condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        try 
        {
            while (resultSet.next()){
                
            posts.add(new Post(resultSet.getInt("Id_Post"), resultSet.getString("Judul"), resultSet.getInt("Id_User"), 
                    resultSet.getString("Konten"), resultSet.getTimestamp("Tanggal"),resultSet.getBoolean("Published"),
            resultSet.getBoolean("Deleted")));
            }
        
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        return posts;
    }

    public ArrayList<Post> getPublishedPost() {
        ArrayList<Post> posts=new ArrayList<Post>();
        
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ResultSet resultSet = null;
        
        try {
            resultSet = databaseAccess.selectAllRecords("post", "Published=TRUE");
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        if (resultSet!=null){
        try 
        {
            while (resultSet.next()){
            posts.add(new Post(resultSet.getInt("Id_Post"), resultSet.getString("Judul"), resultSet.getInt("Id_User"), 
                    resultSet.getString("Konten"), resultSet.getTimestamp("Tanggal"),
                    resultSet.getBoolean("Published"),resultSet.getBoolean("Deleted")));
        }
        
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        }
        return posts;
    }
    
    public ArrayList<Post> getUnpublishedPost() {
        ArrayList<Post> posts=new ArrayList<Post>();
        
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ResultSet resultSet = null;
        
        try {
            resultSet = databaseAccess.selectAllRecords("post", "Published=FALSE");
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        if (resultSet!=null){
        try 
        {
            while (resultSet.next()){
            posts.add(new Post(resultSet.getInt("Id_Post"), resultSet.getString("Judul"), resultSet.getInt("Id_User"), 
                    resultSet.getString("Konten"), resultSet.getTimestamp("Tanggal"),
                    resultSet.getBoolean("Published"),resultSet.getBoolean("Deleted")));
        }
        
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        }
        return posts;
    }
}
