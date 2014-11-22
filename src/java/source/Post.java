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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Try Ajitiono
 */
public class Post {
    protected static int idPost;
    private static String judulPost;
    private static String tanggalPost;
    private static String kontenPost;
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
     * @return toHTML yang akan ditulis di HTML
     * @throws java.sql.SQLException
     */
    public String listPosts() throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("");
        KoneksiDatabase.setDatabase("localhost","blog");
        //inisialisasi string
        String toHTML = "";
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryListPosts = "SELECT * from post ORDER by tanggal DESC";
            //execute query
            ResultSet result = statement.executeQuery(queryListPosts);
            //tulis hasil query
            if (!result.next()) {
                //kosong
                toHTML = "No posts yet.";
            }
            else { //ada hasil
                Date date;
                while (result.next()) { //apabila result masih ada
                    //inisialisasi variabel
                    idPost = result.getInt("id");
                    judulPost = result.getString("judul");
                    kontenPost = result.getString("konten");
                    if (kontenPost.length() > 100) {
                        kontenPost = kontenPost.substring(0, 100); //pemotongan teks
                    }
                    date = result.getDate("tanggal");
                    //ubah menjadi string
                    tanggalPost = date.toString();
                    toHTML =    
                            "<li class=\"art-list-item\">\n" +
                            "<div class=\"art-list-item-title-and-time\">\n" +
                            "<h2 class=\"art-list-title\"><a href=\"post.jsp?id='" + idPost + "\"> " + judulPost + " </a>\n" +
                            "<div class=\"art-list-time\">" + tanggalPost + "</div>\n" +
                            "<div class=\"art-list-time\"><span style=\"color:#F40034;\">&#10029;</span> Featured</div>\n" +
                            "</div>\n" +
                            "<p> " + kontenPost + "\n" +
                            "... <a href=\"post.jsp?id= " + idPost + "\">Read More</a><br/>\n" +
                            "</p>\n" +
                            "<p>\n" +
                            "<a href=\"edit_post.jsp?id=" + idPost + "\">Edit</a> | <a href=\"javascript:confirmDelete()\">Hapus</a>\n" +
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
     * @param judul judul post
     * @param tanggal tanggal post
     * @param konten konten post
     * @throws java.sql.SQLException
     */
    public void addPost(String judul, String tanggal, String konten) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryAddPost = "INSERT INTO post (judul,konten,tanggal) VALUES ('" + judul + "', '" + konten + "', '" + tanggal + "')";
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
            String queryPublishPost = "UPDATE post SET published=1 WHERE id=" + post_ID;
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
     * @param judul judul post
     * @param tanggal tanggal post
     * @param konten konten post
     * @throws java.sql.SQLException
     */
    public void editPost(int post_ID, String judul, String tanggal, String konten) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try ( //statement
            Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            //query
            String queryEditJudul = "UPDATE post SET judul='" + judul + "' WHERE id=" + post_ID;
            String queryEditTanggal = "UPDATE post SET tanggal='" + tanggal + "' WHERE id=" + post_ID;
            String queryEditKonten = "UPDATE post SET konten='" + konten + "' WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryEditJudul);
            statement.executeUpdate(queryEditTanggal);
            statement.executeUpdate(queryEditKonten);
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
            String queryDeletePost = "DELETE FROM post WHERE id=" + post_ID;
            String queryDeleteComments = "DELETE FROM komentar WHERE id=" + post_ID;
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
