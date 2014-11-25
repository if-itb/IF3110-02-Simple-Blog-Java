/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author A 46 CB i3
 */
@ManagedBean
@RequestScoped
public class AddPost {
    
    // default constructor
    public AddPost() {
    }
    
    // function
    public void add(String judul, String tanggal, String konten) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO postdata (judul, tanggal, konten, status) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, judul);
            preparedStatement.setString(2, tanggal);
            preparedStatement.setString(3, konten);
            preparedStatement.setString(4, "unpublished");
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
