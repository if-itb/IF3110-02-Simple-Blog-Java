/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Try Ajitiono
 */
public class Post {
    protected static int IDPost;
    private static String Title;
    private static String Content;
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
     * @param title judul post
     * @param date tanggal post
     * @param content konten post
     * @throws java.sql.SQLException
     */
    public void addPost(String title, String date, String content) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryAddPost = "INSERT INTO posts (title,body,date) VALUES ('" + title + "', '" + content + "', '" + date + "')";
            //execute query
            statement.executeUpdate(queryAddPost);
            //tutup koneksi database
            koneksi.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mengubah status post di database dari unpublished menjadi published
     * @param post_ID post id di database
     * @throws java.sql.SQLException
     */
    public void publishPost(int post_ID) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryPublishPost = "UPDATE posts SET published=1";
            //execute query
            statement.executeUpdate(queryPublishPost);
            //tutup koneksi database
            koneksi.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mengubah aspek dari post di database
     * @param post_ID post id di database
     * @param title input judul baru dari pengguna
     * @param date input tanggal baru dari pengguna
     * @param content input konten baru dari pengguna
     * @throws java.sql.SQLException
     */
    public void editPost(int post_ID, String title, String date, String content) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryEditTitle = "UPDATE posts SET title='" + title + "' WHERE post_id=" + post_ID;
            String queryEditDate = "UPDATE posts SET date='" + date + "' WHERE post_id=" + post_ID;
            String queryEditContent = "UPDATE posts SET content='" + content + "' WHERE post_id=" + post_ID;
            //execute query
            statement.executeUpdate(queryEditTitle);
            statement.executeUpdate(queryEditDate);
            statement.executeUpdate(queryEditContent);
            //tutup koneksi database
            koneksi.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Menghapus post dari database
     * @param post_ID post id di database
     * @throws java.sql.SQLException
     */
    public void deletePost(int post_ID) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
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
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
