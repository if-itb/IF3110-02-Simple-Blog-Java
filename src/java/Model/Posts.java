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
    
    public void addPost(Post p) {
        posts.add(p);
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
                        result.getString("Konten"), result.getTimestamp("Tanggal"));
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
    
    public void savePost() {
    /* Asumsi : koneksi diurus oleh kelas yang memanggil atau controller */
        
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("id"));
        
        for (int i=0; i<posts.size(); i++) {
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
    
    public void updatePostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        ArrayList<String> columns = new ArrayList(Arrays.asList("Judul","Konten", "Tanggal"));
        ArrayList<String>values = new ArrayList(Arrays.asList("'"+p.getTitle()+"'",
                "'"+p.getText()+"'","'"+ p.getTimestamp()+"'"));
        String condition = "Id_Post="+p.getId();
        try {
                databaseAccess.updateRecords("post", columns, values, condition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     
    private void insertPostDB(Post p) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String idUser = "" + p.getId() +"";
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
            resultSet = databaseAccess.selectAllRecords("Posts", condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        try 
        {
            while (resultSet.next()){
                
            posts.add(new Post(resultSet.getInt("Id_Post"), resultSet.getString("Judul"), resultSet.getInt("Id_User"), 
                    resultSet.getString("Konten"), resultSet.getTimestamp("Tanggal")));
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
            resultSet = databaseAccess.selectAllRecords("post", null);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        
        if (resultSet!=null){
        try 
        {
            while (resultSet.next()){
            posts.add(new Post(resultSet.getInt("Id_Post"), resultSet.getString("Judul"), resultSet.getInt("Id_User"), 
                    resultSet.getString("Konten"), resultSet.getTimestamp("Tanggal")));
        }
        
        } 
        catch (SQLException e1) {
                e1.printStackTrace();
        }
        }
        return posts;
    }
}
