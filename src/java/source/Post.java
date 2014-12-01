/* Kelas: Post.java
 */
package source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Try Ajitiono + Rakhmatullah Yoga Sutrisna
 */
public class Post {
    /**
     * Atribut-atribut
     */
    private int idPost;
    private String judulPost;
    private String tanggalPost;
    private String kontenPost;
    private int publishStatus;
    private boolean cookieOn;
    private User user;
    
    /**
     * Konstruktor Post, gunanya untuk membuat objek user
     */
    public Post() {
        user = new User();
    }
    
    /**
     * melakukan set username berdasarkan cookie
     * @param c
     */
    public void cookieHeaderCheck(CookieHelper c) {
        //prekondisi: cookie sudah pasti ada
        user.setUsername(c.getUsername());
        cookieOn = true;
    }
    /**
     * Menambahkan komentar ke dalam basis data
     * @param ID 
     * @return  HTML script untuk memuat komentar yang akan digunakan oleh AJAX
     * @throws java.sql.SQLException 
     */
    public String LoadComment(int ID) throws SQLException {
        //login database
        String HTMLcode = "";
        KoneksiDatabase.setUser("root2");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try {
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            String QueryLoadComment = "SELECT * FROM komentar WHERE idpost="+idPost+" ORDER BY Waktu DESC";
            ResultSet result = statement.executeQuery(QueryLoadComment);
            while(result.next()) {
                String Nama = result.getString("Nama");
                String Email = result.getString("Email");
                String Comment = result.getString("Komentar");
                Timestamp Time = result.getTimestamp("Waktu");
                HTMLcode +=
                        "    <li class=\"art-list-item\">\n" +
                        "        <div class=\"art-list-item-title-and-time\">\n" +
                        "            <h2 class=\"art-list-title\">" + Nama + "</h2>\n" +
                        "            <div class=\"art-list-time\">" + Time.toString() + "</div>\n" +
                        "        </div>\n" +
                        "        <p>" + Comment + "</p>\n" +
                        "    </li>";
            }
        } catch (SQLException ex) {
        }
        return HTMLcode;
    }
    /**
     * @param PostID
     * @param nama
     * @param email
     * @param comment
     * @throws SQLException 
     */
    public void AddComment(int PostID, String nama, String email, String comment) throws SQLException {
        String InsertQuery;
        KoneksiDatabase.setUser("root2");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        try {
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            InsertQuery = "INSERT INTO komentar (idpost, nama, email, komentar) VALUES ('" + PostID + "', '" + nama + "', '" + email + "', '" + comment + "')";
            statement.executeUpdate(InsertQuery);
        } catch (SQLException ex) {

        }
    }
    /**
     * Menambahkan header message pada halaman utama blog
     * @return header tulisan yang dikembalikan ke halaman utama
     * @throws SQLException
     */
    public String showMessageHeader() throws SQLException {
        String header;
        if (cookieOn) {
            header = "Welcome " + user.getUsername() + ", your role is " + user.getRole() + 
                     "<li> <form action=\"/IF3110-02-Simple-Blog-Java/LogoutServlet\" method=\"post\">\n" +
                     "<input type=\"submit\" value=\"Logout\" >\n" +
                     "</form> </li>" +
                     "<a href=\"/IF3110-02-Simple-Blog-Java/admin/index.jsp\"><div align=\"right\"><li><font color=\"blue\">Dashboard</font></li></div></a>";
        }
        else {
            header = "Welcome guest, please login below.<br>\n" +
        "           <form action=\"LoginServlet\" method=\"POST\">\n" +
        "            <input type=\"text\" name=\"username\" placeholder=\"username\"/>\n" +
        "            <input type=\"password\" name=\"password\" placeholder=\"password\"/>\n" +
        "            <input type=\"submit\" value=\"Login\"/>\n" +
        "           </form>";
        }
        return header;
    }
    
    /**
     * Menambahkan header message pada halaman manajemen blog
     * @return header tulisan yang dikembalikan ke halaman utama
     * @throws SQLException
     */
    public String showManagementHeader() throws SQLException {
        String header;
        if (cookieOn) {
            header = "Welcome " + user.getUsername() + ", your role is " + user.getRole() + 
                     "<li> <form action=\"/IF3110-02-Simple-Blog-Java/LogoutServlet\" method=\"post\">\n" +
                     "<input type=\"submit\" value=\"Logout\" >\n" +
                     "</form></li>" +
                     "<br><a href=\"/IF3110-02-Simple-Blog-Java/admin/index.jsp\"><div align=\"right\"><li><font color=\"blue\">Dashboard</font></li></div></a>";
        }
        else {
            header = "Welcome guest, please login below.<br>\n" +
        "           <form action=\"LoginServlet\" method=\"POST\">\n" +
        "            <input type=\"text\" name=\"username\" placeholder=\"username\"/>\n" +
        "            <input type=\"password\" name=\"password\" placeholder=\"password\"/>\n" +
        "            <input type=\"submit\" value=\"Login\"/>\n" +
        "           </form>";
        }
        return header;
    }
    
    /**
     * Mengecek apakah pengguna adalah admin
     * @return 1 apabila admin, 0 bila bukan
     */    
    public boolean isAdmin() {
        try {
            return (user.getRole().compareTo("admin") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Mengecek apakah pengguna adalah editor
     * @return 1 apabila admin, 0 bila bukan
     */
    public boolean isEditor() {
        try {
            return (user.getRole().compareTo("editor") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Mengecek apakah pengguna adalah owner
     * @return 1 apabila admin, 0 bila bukan
     */
    public boolean isOwner() {
        try {
            return (user.getRole().compareTo("owner") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Melakukan set atribut post
     * @param post_ID id post
     * @throws java.sql.SQLException
     */
    public void setAtribut(int post_ID) throws SQLException {
        try {
            //login database
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
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
                publishStatus = result.getInt("publishStatus");
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
    public String listPublishedPosts() throws SQLException {
        //inisialisasi string
        String toHTML = "";
        boolean shortened;
        try {
            //login database
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryListPosts = "SELECT * from `post` WHERE publishStatus=1 ORDER by tanggal DESC";
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
                    publishStatus = result.getInt("publishStatus");
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
                            "<h2 class=\"art-list-title\"><a href=\"posts/post.jsp?id=" + idPost + "\"> " + judulPost + " </a>\n" +
                            "<div class=\"art-list-time\">" + tanggalPost + "</div>\n" +
                            "<div class=\"art-list-time\"><span style=\"color:#F40034;\">&#10029;</span> Featured</div>\n" +
                            "</div>\n" +
                            "<p> " + kontenPost + "\n" + "</p>\n";                            
                    if (shortened) //dipotong
                        toHTML += "... <a href=\"posts/post.jsp?id=" + idPost + "\">Read More</a><br/>\n";
                    //edit post: untuk semua kecuali guest
                    if (cookieOn) {
                        if (isAdmin() || isEditor() || isOwner()) {
                            toHTML +=  "<p>\n" +
                            "<a href=\"posts/edit_post.jsp?id=" + idPost + 
                            "\">Edit</a>";
                        }
                        //delete post: untuk admin dan owner
                        if (isAdmin() || isOwner()) {    
                            toHTML += " | <a href=\"posts/delete_post.jsp?id=" + idPost + 
                            "\" onclick='return confirmDelete()'>Hapus</a>\n" +
                            "</p>\n" +
                            "</li>";
                        }
                    }
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
     *
     * @return
     * @throws SQLException
     */
    public String listManagementPosts() throws SQLException {
        //inisialisasi string
        String toHTML = "";
        boolean shortened;
        try {
            //login database
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryListPosts = "SELECT * from `post` ORDER by tanggal DESC";
            //execute query
            ResultSet result = null;
            //header tabel
            toHTML += "<table id=\"t01\"> <tr>" +
                      "<th <tr style=\"vertical-align:center\" rowspan=\"2\" id=\"column1\">Judul Post</th>\n" +
                      "<th <tr style=\"vertical-align:center\" rowspan=\"2\" id=\"column2\">Tanggal Post</th>\n" +
                      "<th <tr style=\"vertical-align:center\" rowspan=\"2\" id=\"column3\">Konten Post</th>\n" +
                      "<th <tr style=\"vertical-align:center\" rowspan=\"2\" id=\"column4\">Status</th>\n" +
                      "<th id=\"column5\" colspan=\"5\">Aksi</th>\n" +
                      "</tr>\n" +
                      "<th id=\"columnx\">Publish</th>\n" +
                      "<th id=\"columnx\">Trash</th>\n" +
                      "<th id=\"columnx\">Restore</th>\n" +  
                      "<th id=\"columnx\">Delete</th>\n" +
                      "<th id=\"columnx\">Edit</th>\n" +
                      "</tr>\n";
            //tulis hasil query
            Date date;
            result = statement.executeQuery(queryListPosts);
            while (result.next()) { //apabila result masih ada
                shortened = false;
                //inisialisasi variabel
                idPost = result.getInt("id");
                judulPost = result.getString("judul");
                kontenPost = result.getString("konten");
                publishStatus = result.getInt("publishStatus");
                if (kontenPost.length() > 50) {
                    kontenPost = kontenPost.substring(0, 50); //pemotongan teks
                    shortened = true;
                }
                date = result.getDate("tanggal");
                //ubah menjadi string
                tanggalPost = date.toString();
                //judul, tanggal, konten post
                toHTML += "<tr>\n" + "<th>" + judulPost + "</th>"
                                   + "<th>" + tanggalPost + "</th>"
                                   + "<th>" + kontenPost;
                if (shortened) //dipotong
                    toHTML += "... <a href=../posts/post.jsp?id=" + idPost + ">Read More</a></th>";
                else //tidak dipotong
                    toHTML += "</th>";
                //status post
                switch (publishStatus) {
                    case 0: toHTML += "<th> Unpublished </th>"; break;
                    case 1: toHTML += "<th> Published </th>"; break;
                    case 2: toHTML += "<th> Trashed </th>"; break;
                }
                //aksi berdasarkan role, lalu berdasarkan post
                if (isAdmin()) {
                    if (publishStatus == 0) { //status unpublished
                        toHTML += "<th><a href='../posts/publish_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th><a href='../posts/trash_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th></th>\n";
                    }
                    else if (publishStatus == 1) { //status published
                        toHTML += "<th></th>\n" +
                                  "<th><a href='../posts/trash_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th></th>\n";
                    }
                    else if (publishStatus == 2) { //status trash
                        toHTML += "<th></th>\n" +
                                  "<th></th>\n" +
                                  "<th><a href='../posts/restore_post.jsp?id=" + idPost + "'> X </a></th>\n";
                    }
                    toHTML += "<th><a href='../posts/delete_post.jsp?id=" + idPost + "' onclick='return confirmDelete()'> X </a></th>\n" +
                              "<th><a href='../posts/edit_post.jsp?id=" + idPost + "'> X </a></th></tr>\n";
                }
                else if (isEditor()) {
                    if (publishStatus == 0) { //status unpublished
                        toHTML += "<th><a href='../posts/publish_post.jsp?id=" + idPost + "'> X </a></th>\n";
                    }
                    else if (publishStatus == 1) { //status published
                        toHTML += "<th></th>\n";
                    }
                    toHTML +=  "<th></th>\n" +
                               "<th></th>\n" +
                               "<th></th>\n<th><a href='../posts/edit_post.jsp?id=" + idPost + "'> X </a></tr>\n" +
                               "<th></th>\n";
                }
                else if (isOwner()) {
                    if (publishStatus == 0) { //status unpublished
                        toHTML += "<th><a href='../posts/publish_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th><a href='../posts/trash_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th></th>\n";
                    }
                    else if (publishStatus == 1) { //status published
                        toHTML += "<th></th>\n" +
                                  "<th><a href='../posts/trash_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                                  "<th></th>\n";
                    }
                    else if (publishStatus == 2) { //status trash
                        toHTML += "<th></th>\n" +
                                  "<th></th>\n" +
                                  "<th></th>\n";
                    }
                    toHTML += "<th><a href='../posts/delete_post.jsp?id=" + idPost + "'> X </a></th>\n" +
                              "<th><a href='../posts/edit_post.jsp?id=" + idPost + "'> X </a></tr>\n";
                }
            }
            toHTML += "</table>\n<br>\n";
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
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
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
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryPublishPost = "UPDATE post SET publishStatus=1 WHERE id=" + post_ID;
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
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
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
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
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
    
    /**
     * Membuang post secara soft (soft delete)
     * @param post_ID id post
     * @throws SQLException
     */
    public void trashPost(int post_ID) throws SQLException {
        try { 
            //login database
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryTrashPost = "UPDATE post SET publishStatus=2 WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryTrashPost);
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mengembalikan post dari trash ke unpublished
     * @param post_ID id post
     * @throws SQLException
     */
    public void restorePost(int post_ID) throws SQLException {
        try { 
            //login database
            KoneksiDatabase.setUser("root2");
            KoneksiDatabase.setPassword("akhfa");
            KoneksiDatabase.setDatabase("localhost","blog");
            //statement
            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            //query
            String queryRestorePost = "UPDATE post SET publishStatus=0 WHERE id=" + post_ID;
            //execute query
            statement.executeUpdate(queryRestorePost);
        }
        catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
