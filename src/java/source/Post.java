/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Try Ajitiono
 */
public class Post {
    
    /**
     * Mengecek apakah pengguna adalah admin
     * @return
     */
    public boolean isAdmin() {
        return true;
    }
    
    /**
     * Mengecek apakah pengguna adalah editor
     * @return
     */
    public boolean isEditor() {
        return true;
    }
    
    /**
     * Mengecek apakah pengguna adalah owner
     * @return
     */
    public boolean isOwner() {
        return true;
    }
    
    /**
     * Menampilkan semua post yang statusnya published
     */
    public void listPosts() {
        
    }
    
    /**
     * Menambahkan suatu post ke dalam database
     */
    public void addPost() {
        
    }
    
    /**
     * Mengubah status post di database dari unpublished menjadi published
     * @param post_ID post id di database
     */
    public void publishPost(int post_ID) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        //statement
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        //query
        String queryPublishPost = "UPDATE posts SET published=1";
        //execute query
        statement.executeUpdate(queryPublishPost);
        //tutup koneksi database
        koneksi.close();
    }
    
    /**
     * Mengubah aspek dari post di database
     * @param post_ID post id di database
     */
    public void editPost(int post_ID) {
        
    }
    
    /**
     * Menghapus post dari database
     * @param post_ID post id di database
     */
    public void deletePost(int post_ID) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        //statement
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        //query
        String queryDeletePost = "DELETE FROM posts WHERE post_id=" + post_ID;
        String queryDeleteComments = "DELETE FROM comments WHERE post_id=" + post_ID;
        //execute query
        statement.executeUpdate(queryDeletePost);
        statement.executeUpdate(queryDeleteComments);
        //tutup koneksi database
        koneksi.close();
    }
}
