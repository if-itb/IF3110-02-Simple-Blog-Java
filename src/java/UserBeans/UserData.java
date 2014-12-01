/*;;


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yusuf
 */
public class UserData {

    public UserData(){
    }
    
    public User getUser(int uid) {
        User user = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM user WHERE uid=" + uid;
        try {
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(sql);
            con.close();
            user = new User();
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUser(String username) {
        User user = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM user WHERE username='" + username + "'";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);

            user = new User();
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public ArrayList<User> getListUser(){
        ArrayList<User> records = new ArrayList<User>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM user";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setUid(rs.getInt("uid"));
                user.setUsername(rs.getString("username"));
                records.add(user);
            }
            con.close();
        } catch (SQLException ex) {
            String exex = ex.getMessage();
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public void writeKomentar(Komentar komentar) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO `blogjava`.`komentar` (`cid`, `pid`, `komentator`, `komen`, `email`, `commentdate`) VALUES (NULL, " + "'" + komentar.getPid()
                + "', '" + komentar.getKomentator() + "', '"
                + komentar.getKomen() + "', '"
                + komentar.getEmail() + "', '"
                + komentar.getCommentDate() + "')";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            String exex = ex.getMessage();
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeKomentar(int Pid, String komentator,
            String komen, String email, Date commentDate) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO komentar VALUES (NULL, " + "'" + Pid
                + "', '" + komentator + "', '"
                + komen + "', '"
                + email + "', '"
                + commentDate + "')";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            String exe = ex.getMessage();
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Komentar getKomentar(int cid) {
        Komentar komentar = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM komentar WHERE cid=" + cid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            con.close();

            komentar = new Komentar();
            while (rs.next()) {
                komentar.setCid(rs.getInt("cid"));
                komentar.setPid(rs.getInt("pid"));
                komentar.setKomentator(rs.getString("komentator"));
                komentar.setKomen(rs.getString("komen"));
                komentar.setEmail(rs.getString("email"));
                komentar.setCommentDate(rs.getString("commentdate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return komentar;
    }

    public ArrayList<Komentar> getListKomentar(int pid) {
        ArrayList<Komentar> records = new ArrayList<Komentar>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM komentar WHERE pid=" + pid+" ORDER BY cid ASC";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);

            while (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setCid(rs.getInt("cid"));
                komentar.setPid(rs.getInt("pid"));
                komentar.setKomentator(rs.getString("komentator"));
                komentar.setKomen(rs.getString("komen"));
                komentar.setEmail(rs.getString("email"));
                komentar.setCommentDate(rs.getString("commentdate"));
                records.add(komentar);
            }
            con.close();
        } catch (SQLException ex) {
            String exex = ex.getMessage();
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public void writeBlogPost(BlogPost blogPost) {
        
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO blogpost VALUES (NULL, '"+ blogPost.getUid()
                + "', '" + blogPost.getPosttitle()
                + "', '" + blogPost.getPostcontent() + "', '"
                + blogPost.getPostdate() + "', '"
                + blogPost.publishedToInt() + "', '"
                + blogPost.deletedToInt() + "')";
        
        try {
            pst = con.prepareStatement(sql);
            pst.execute();   
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BlogPost getBlogPost(int pid) {
        BlogPost blogPost = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        //String sql = "select * from blogpost where pid=1";
        String sql = "SELECT blogpost.*, user.fullname FROM blogpost NATURAL JOIN user WHERE blogpost.uid = user.uid AND blogpost.pid=" + pid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);

            blogPost = new BlogPost();
            rs.first();
                blogPost.setUsername(rs.getString("fullname"));
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getString("postdate"));
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                con.close();
        } catch (SQLException ex) {
            String logg = ex.getMessage();
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogPost;
    }

    public ArrayList<BlogPost> getAllBlogPost() {
        ArrayList<BlogPost> records = new ArrayList<BlogPost>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT blogpost.*, user.fullname FROM blogpost NATURAL JOIN user WHERE blogpost.uid = user.uid AND published=1 ORDER BY blogpost.postdate DESC";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            
            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setUsername(rs.getString("fullname"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                records.add(blogPost);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<BlogPost> getAllUnpublishedBlogPost(){
        ArrayList<BlogPost> records = new ArrayList<BlogPost>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT blogpost.*, user.fullname FROM blogpost NATURAL JOIN user WHERE blogpost.uid = user.uid AND published=0 ORDER BY blogpost.postdate DESC";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            
            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setUsername(rs.getString("fullname"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                records.add(blogPost);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<BlogPost> getDeletedPosts(){
        ArrayList<BlogPost> records = new ArrayList<BlogPost>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT blogpost.*, user.fullname FROM blogpost NATURAL JOIN user WHERE blogpost.uid = user.uid AND deleted=1 ORDER BY blogpost.postdate DESC";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            
            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setUsername(rs.getString("fullname"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                records.add(blogPost);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/blogjava";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return con;
    }
    
    public void publishPost(int pid){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "UPDATE blogpost SET published = 1 WHERE pid=" + pid;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletePost(int pid){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "DELETE FROM blogpost WHERE pid=" + pid;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void softDeletePost(int pid){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "UPDATE blogpost SET deleted = 1, published = 0 WHERE pid=" + pid;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUserDB(User updUser){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "UPDATE user SET fullname='"+updUser.getFullname()
                +"', username='"+updUser.getUsername()
                +"', password='"+updUser.getPassword()
                +"', role='"+updUser.getRole()
                +"', email='"+updUser.getEmail()
                +"' WHERE uid="+updUser.getUid();
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createUserDB(User newUser){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO user VALUES (NULL, '" 
                +newUser.getFullname()
                +"', '"+newUser.getUsername()
                +"', '"+newUser.getPassword()
                +"', '"+newUser.getRole()
                +"', '"+newUser.getEmail()
                +"')"; 
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restorePost(int pid){
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "UPDATE blogpost SET deleted = 0, published = 1 WHERE pid=" + pid;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
