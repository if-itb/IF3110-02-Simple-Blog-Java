/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author A 46 CB i3
 */
@ManagedBean(name = "listPost", eager = true)
@RequestScoped
public class ListPost {

    // attribute
    private ArrayList<Post> post;
    
    // default constructor
    public ListPost() {
        post = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from postdata");
            while(rs.next() == true) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setJudul(rs.getString(2));
                p.setTanggal(rs.getString(3));
                p.setKonten(rs.getString(4));
                p.setStatus(rs.getString(5));
                post.add(p);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // function
    public void delete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM postdata WHERE id_post=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // getter
    public ArrayList<Post> getPost() {
        return post;
    }
}
