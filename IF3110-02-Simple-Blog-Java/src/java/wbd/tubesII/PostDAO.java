package wbd.tubesII;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asep Saepudin
 */
public class PostDAO {
    private static Connection currentCon = null;
    private static ResultSet rs = null;
    
    public static boolean add(Post post) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("INSERT INTO `post` "
                    + "(`judul`, `tanggal`, `konten`, `status`) VALUES "
                    + "(?, ?, ?, ?)");
            statement.setString(1, post.getJudul());
            statement.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getTanggal()));
            statement.setString(3, post.getKonten());
            statement.setString(4, post.getStatus());
            statement.executeUpdate();            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean update(Post post) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("UPDATE `post` "
                    + "SET `judul`=?, `tanggal`=?, `konten`=?, `status`=? "
                    + "WHERE `id`=? ");
            statement.setString(1, post.getJudul());
            statement.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getTanggal()));            
            statement.setString(3, post.getKonten());
            statement.setString(4, post.getStatus());
            statement.setString(5, String.valueOf(post.getId()));
            
            statement.executeUpdate();            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean delete(int id) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("DELETE FROM `post` WHERE `id`=" + id);            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static Post getPost(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Post post = new Post();
        try {            
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `post` WHERE `id`=" + id);            
            rs = statement.executeQuery();
            
            if (!rs.next()) {
                return null;
            } else {                
                post.setId(Integer.valueOf(rs.getString("id")));
                post.setJudul(rs.getString("judul"));
                post.setTanggal(rs.getDate("tanggal"));
//                post.setTanggal(new SimpleDateFormat("MM/dd/yyyy").parse(rs.getString("tanggal")));
                post.setKonten(rs.getString("konten"));
                post.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
//        } catch (ParseException ex) {
//            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }                        
        return post;
    }
    
    public static ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `post`");
            rs = statement.executeQuery();            
            while (rs.next()) {
                posts.add(getPost(rs.getInt("id")));
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public static ArrayList<Post> getAllPublishedPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `post` where `status`='Published'");
            rs = statement.executeQuery();            
            while (rs.next()) {
                posts.add(getPost(rs.getInt("id")));
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public static ArrayList<Post> getAllUnpublishedPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `post` where `status`='Unpublished'");
            rs = statement.executeQuery();            
            while (rs.next()) {
                posts.add(getPost(rs.getInt("id")));
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public static ArrayList<Post> getAllDeletedPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `post` where `status`='Deleted'");
            rs = statement.executeQuery();            
            while (rs.next()) {
                posts.add(getPost(rs.getInt("id")));
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public static boolean publish (int id) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("UPDATE `post` SET `status`='Published' WHERE id=" + id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean softDelete (int id) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("UPDATE `post` SET `status`='Deleted' WHERE id=" + id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean unDelete (int id) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("UPDATE `post` SET `status`='Unpublished' WHERE id=" + id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
