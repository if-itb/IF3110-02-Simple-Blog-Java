package wbd.tubesII;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asep Saepudin
 */
public class CommentDAO {
    private static Connection currentCon = null;
    private static ResultSet rs = null;
    
    public static boolean save(Comment comment) {
        PreparedStatement statement = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("INSERT INTO `komentar` "
                    + "(`name`, `email`, `tanggal`, `komentar`, `post_id`) VALUES "
                    + "(?, ?, ?, ?, ?)");
            statement.setString(1, comment.getName());
            statement.setString(2, comment.getEmail());
            statement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getTanggal()));
            statement.setString(4, comment.getKomentar());
            statement.setString(5, String.valueOf(comment.getId()));
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
    
    public static ArrayList<Comment> get(int id) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.prepareStatement("SELECT * FROM `komentar` WHERE `post_id`=" + id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setEmail(rs.getString("email"));
                comment.setName(rs.getString("name"));
                comment.setId(Integer.valueOf(rs.getString("post_id")));
                comment.setKomentar(rs.getString("komentar"));
                comment.setTanggal(rs.getDate("tanggal"));
                comments.add(comment);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return comments;
    }
}
