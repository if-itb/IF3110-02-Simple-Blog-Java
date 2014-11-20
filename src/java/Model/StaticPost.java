/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import DB.*;
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
public class StaticPost {
    private Vector<Post> posts;
    
    public StaticPost () {}
    
    public void addPost(Post p) {
        posts.add(p);
    }
    
    public Post getPost(int post_id)
    {
        Post p1;
        boolean stop = false;
        for (int i=0; i<=posts.size() && !stop; i++) {
            if (posts.get(i).getId()==post_id) {
               p1 =  posts.get(i);
               stop = true;
            }
        }
        
        ResultSet result =null;
        if (stop) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
            String condition = "id="+post_id;
            try {
            result = databaseAccess.selectAllRecords("Posts",condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        }
        
        //fetch the result
        Post p = null;
        try 
        {
            while (result.next()) {
                
                p = new Post(result.getInt("post_id"), result.getString("title") ,result.getString("creator") ,  
                        result.getString("text"), result.getTimestamp("timestamp"));
                
            }
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        return p;
    }
    
    public void removePost(int post_id) {
        boolean stop = false;
        for (int i=0; i<=posts.size() && !stop; i++) {
            if (posts.get(i).getId()==post_id) {
               posts.remove(i);
               stop = true;
            }
        }
        //update ke database, asumsi koneksi ke database udah established
        if (stop) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
            String condition = "id="+post_id;
            try {
                    databaseAccess.deleteRecords("Posts", condition);
            } catch (SQLException e1) {
                    e1.printStackTrace();
            }
        }
    }
    
    public void savePost() {
    /* Asumsi : koneksi diurus oleh kelas yang memanggil atau controller */
        
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("id"));
        
        for (int i=0; i<=posts.size(); i++) {
            String condition = "post_id=" + posts.get(i).getId();
            ResultSet result = null;
            
            //cek apakah post yang bersangkutan ada di database atau tidak
            try {
                result = databaseAccess.selectRecords("Posts", columns, condition);
            } catch (SQLException ex) {
                Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (result!=null) { //update
                updatePostDB(posts.get(i));
            }
            else { //insert
                insertPostDB(posts.get(i));
            }
        }
    }
    
    private void updatePostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("text", "timestamp"));
        ArrayList<String>values = new ArrayList(Arrays.asList(p.getText(), p.getTimestamp()));
        String condition = "id="+p.getId();
        try {
                databaseAccess.updateRecords("Posts", columns, values, condition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     
    private void insertPostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns;
        columns = new ArrayList(Arrays.asList("id", "title" ,"creator", "text", "timestamp"));
        ArrayList<String> values = new ArrayList(Arrays.asList(p.getId(), p.getTitle(), p.getCreator(),
                p.getText(), p.getTimestamp()));
        try {
            databaseAccess.insertRecords("Posts", columns, values);
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Post> filter(int user_id){
        Vector<Post> posts = new Vector<Post>();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String condition = "user_id="+user_id; 
        ResultSet resultSet = null;
        
        try {
            resultSet = databaseAccess.selectAllRecords("Posts", condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        try 
        {
            while (resultSet.next()){
            posts.add(new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("creator"), 
                    resultSet.getString("text"), resultSet.getTimestamp("timestamp")));
        }
        
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        return posts;
    }
    
    
    
    
          
   
}
