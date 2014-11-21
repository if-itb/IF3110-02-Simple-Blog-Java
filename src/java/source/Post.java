/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @throws java.sql.SQLException
     */
    public String listPosts() throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        //inisialisasi string
        String toHTML = "";
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryListPosts = "SELECT * from posts ORDER by date DESC";
            //execute query
            ResultSet result = statement.executeQuery(queryListPosts);
            //tulis hasil query
            if (!result.next()) {
                //kosong
                toHTML = "No posts yet.";
            }
            else {
                //ada hasil
                while (result.next()) {
                    toHTML =    
                            "<li class=\"art-list-item\">\n" +
                            "<div class=\"art-list-item-title-and-time\">\n" +
                            "<h2 class=\"art-list-title\"><?php echo '<a href=\"post.php?id='.$row['id'].'\">'.$row['title'].'</a>'; ?>\n" +
                            "<div class=\"art-list-time\"><?php echo ''.$row['date'].''; ?></div>\n" +
                            "<div class=\"art-list-time\"><span style=\"color:#F40034;\">&#10029;</span> Featured</div>\n" +
                            "</div>\n" +
                            "<p><?php $body = substr($row['body'], 0, 100);\n" +
                            "echo nl2br($body);\n" +
                            "if (strlen($row['body']) > 100) {\n" +
                            "echo '... <a href=\"post.php?id='.$row['id'].'\">Read More</a><br/>';\n" +
                            "}\n" +
                            "?></p>\n" +
                            "<p>\n" +
                            "<?php echo '<a href=\"edit_post.php?id='.$row['id'].'\">Edit</a>' ?> | <?php echo '<a href=\"javascript:confirmPostDelete('.$row['id'].')\">Hapus</a>'; ?>\n" +
                            "</p>\n" +
                            "</li>";
                }
            }
            //tutup koneksi database
            koneksi.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return string
        return toHTML;
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
