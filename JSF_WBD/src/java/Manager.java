/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@ManagedBean
@ApplicationScoped
public class Manager {

    Connection con = null;
    Statement stmt = null;

    
    /**
     * Creates a new instance of DataManager
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Manager() throws ClassNotFoundException, SQLException {
        SetupDB();
    }
    
    private void SetupDB() throws ClassNotFoundException, SQLException{
        String host = "jdbc:mysql://localhost:3306/simple_blog_java?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = (Connection) DriverManager.getConnection(host, user, pwd);
        stmt = (Statement) con.createStatement();        
    }
    public ArrayList<Post> getPublishedPosts() throws SQLException{
        ArrayList<Post> result;
        result = new ArrayList<>();
        String q = "SELECT * FROM tb_post WHERE isPublished = true ORDER BY pdate DESC;";
        ResultSet rs = stmt.executeQuery(q);
        while (rs.next()) {
            Post mPost = new Post();
            mPost.Judul = rs.getString("ptitle");
            mPost.Konten = rs.getString("pcontent");
            mPost.Pid = rs.getInt("pid");
            mPost.Tanggal = rs.getDate("pdate");
            result.add(mPost);
        }
        return result;
    }
    public ArrayList<Post> getUnPublishedPosts() throws SQLException{
    ArrayList<Post> result;
    result = new ArrayList<>();
    String q = "SELECT * FROM tb_post WHERE isPublished = false;";
    ResultSet rs = stmt.executeQuery(q);
    while (rs.next()) {
        Post mPost = new Post();
        mPost.Judul = rs.getString("ptitle");
        mPost.Konten = rs.getString("pcontent");
        mPost.Pid = rs.getInt("pid");
        mPost.Tanggal = rs.getDate("pdate");
    
        result.add(mPost);
        }
        return result;
    }
    public Post getPost(int pid) throws SQLException{
        String q = "SELECT * FROM tb_post WHERE pid="+pid+";";
        ResultSet rs = stmt.executeQuery(q);
        Post mPost = new Post();
        mPost.Judul = rs.getString("ptitle");
        mPost.Konten = rs.getString("pcontent");
        mPost.Pid = rs.getInt("pid");
        mPost.Tanggal = rs.getDate("pdate");
        return mPost;
    }
    public void deletePost(int pid) throws SQLException, IOException{
        String q = "DELETE FROM tb_post WHERE pid = "+pid+";";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(q);
        int i = ps.executeUpdate(q);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("home.xhtml");
    }
    public void publishPost(int pid) throws SQLException, IOException{
        String q = "UPDATE tb_post SET isPublished = '1' WHERE pid = "+pid+";";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(q);
        int i = ps.executeUpdate(q);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("home.xhtml");
    }
    
}
