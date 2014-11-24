/* Kelas: Post.java
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
    /**
     * Atribut-atribut
     */
    protected static int idPost;
    private static String judulPost;
    private static String tanggalPost;
    private static String kontenPost;
    private static boolean publishStatus;
    public String currentUser;
    public String currentPass;
    public String currentRole;
    public boolean cookieOn;
    
    public void cookieHeaderCheck(CookieHelper c) {
        cookieOn = false;
        if(c.thereIsCookie()) {
            setUser(c.getUsername());
            cookieOn = true;
        }
    }
    
    public String showMessageHeader() {
        String header;
        if (cookieOn) {
            header = "Welcome " + currentUser + ", your role is " + currentRole;
        }
        else {
            header = "Welcome" + ", please login <a href=\"login/index.html\">here</a>.";
        }
        return header;
    }
    
    public void setUser(String user) {
        currentUser = user;
        try {
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String querySelectPost = "SELECT * from user WHERE username='" + user + "'";
            //execute query
            ResultSet result = statement.executeQuery(querySelectPost);
            //tulis hasil query
            while (result.next()) {
                currentPass = result.getString("password");
                currentRole = result.getString("role");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mengecek apakah pengguna adalah admin
     * @return
     */    
    public boolean isAdmin() {
        return (currentRole.compareTo("admin") == 0);
    }
    
    /**
     * Mengecek apakah pengguna adalah editor
     * @return
     */
    public boolean isEditor() {
        return (currentRole.compareTo("editor") == 0);
    }
    
    /**
     * Mengecek apakah pengguna adalah owner
     * @return
     */
    public boolean isOwner() {
        return (currentRole.compareTo("owner") == 0);
    }
    
    /**
     * Melakukan set atribut post
     * @param post_ID id post
     * @throws java.sql.SQLException
     */
    public void setAtribut(int post_ID) throws SQLException {
        try {
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String querySelectPost = "SELECT * from post WHERE id=" + post_ID;
            //execute query
            ResultSet result = statement.executeQuery(querySelectPost);
            //tulis hasil query
            idPost = post_ID;
            while (result.next()) {
                judulPost = result.getString("judul");
                tanggalPost = result.getString("tanggal");
                kontenPost = result.getString("konten");
                publishStatus = result.getString("publishStatus").compareTo("0") != 0;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mereturn id post
     * @return idPost
     */
    public int getID() {
        return idPost;
    }
    
    /**
     * Mereturn judul post
     * @return judulPost
     */
    public String getJudul() {
        return judulPost;
    }
    
    /**
     * Mereturn tanggal post
     * @return tanggalPost
     */
    public String getTanggal() {
        return tanggalPost;
    }
    
    /**
     * Mereturn konten post
     * @return kontenPost
     */
    public String getKonten () {
        return kontenPost;
    }
    
    /**
     * Menampilkan semua post yang statusnya published
     * @return toHTML yang akan ditulis di HTML
     * @throws java.sql.SQLException
     */
    public String listPosts() throws SQLException {
        //inisialisasi string
        String toHTML = "";
        boolean shortened;
        try {
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryListPosts = "SELECT * from `post` ORDER by tanggal DESC";
            //execute query
            ResultSet result = statement.executeQuery(queryListPosts);
            //tulis hasil query
            if (!result.next()) {
                //kosong
                toHTML = "No posts yet.";
            }
            else { //ada hasil
                Date date;
                result = statement.executeQuery(queryListPosts);
                while (result.next()) { //apabila result masih ada
                    shortened = false;
                    //inisialisasi variabel
                    idPost = result.getInt("id");
                    judulPost = result.getString("judul");
                    kontenPost = result.getString("konten");
                    if (kontenPost.length() > 100) {
                        kontenPost = kontenPost.substring(0, 100); //pemotongan teks
                        shortened = true;
                    }
                    date = result.getDate("tanggal");
                    //ubah menjadi string
                    tanggalPost = date.toString();
                    toHTML +=    
                            "<li class=\"art-list-item\">\n" +
                            "<div class=\"art-list-item-title-and-time\">\n" +
                            "<h2 class=\"art-list-title\"><a href=\"post.jsp?id=" + idPost + "\"> " + judulPost + " </a>\n" +
                            "<div class=\"art-list-time\">" + tanggalPost + "</div>\n" +
                            "<div class=\"art-list-time\"><span style=\"color:#F40034;\">&#10029;</span> Featured</div>\n" +
                            "</div>\n" +
                            "<p> " + kontenPost + "\n";
                            
                    if (shortened) //dipotong
                        toHTML += "... <a href=\"post.jsp?id= " + idPost + "\">Read More</a><br/>\n";
                    toHTML +=   "</p>\n" +
                    "<p>\n" +
                    "<a href=\"edit_post.jsp?id=" + idPost + "\">Edit</a> | <a href=\"delete_post.jsp?id=" + idPost + "\" onclick=\"javascript:confirmDelete()\">Hapus</a>\n" +
                    "</p>\n" +
                    "</li>";
                }
            }
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
        try { 
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryAddPost = "INSERT INTO post (judul,konten,tanggal,publishStatus) VALUES ('" + judul + "', '" + konten + "', '" + tanggal + "', 0)";
            //execute query
            statement.executeUpdate(queryAddPost);
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
        try { 
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryPublishPost = "UPDATE post SET published=1 WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryPublishPost);
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
        try {
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi(); 
            Statement statement = koneksi.createStatement();
            //query
            String queryEditJudul = "UPDATE post SET judul='" + judul + "' WHERE id=" + post_ID;
            String queryEditTanggal = "UPDATE post SET tanggal='" + tanggal + "' WHERE id=" + post_ID;
            String queryEditKonten = "UPDATE post SET konten='" + konten + "' WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryEditJudul);
            statement.executeUpdate(queryEditTanggal);
            statement.executeUpdate(queryEditKonten);
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
        try { 
            //login database
            KoneksiDatabase.setUser("root");
            KoneksiDatabase.setPassword("");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryDeletePost = "DELETE FROM post WHERE id=" + post_ID;
            String queryDeleteComments = "DELETE FROM komentar WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryDeletePost);
            statement.executeUpdate(queryDeleteComments);
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
