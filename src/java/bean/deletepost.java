/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.bean.SessionScoped;
import javax.faces.bean. ManagedProperty ;

/**
 *
 * @author Andarias Silvanus
 */
@ManagedBean(name="deletepost")
@SessionScoped
public class deletepost 
{
    private String judul;
    @ManagedProperty(value="#{user.author}")
    private String author;
    
    public deletepost() 
    {
        judul = "";
    }

    public String getAuthor() {
        return author;
    }

    public String getJudul() {
        return judul;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public void delete()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "DELETE FROM post WHERE (judul, author) = (?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(2, judul);
            ps.setString(3, author);
            int executeUpdate = ps.executeUpdate();
            if(executeUpdate > 0)
            {
                System.out.println("update succesful");
            }
            else
            {
                System.out.println("update failure");
            }
            con.close();
        } 
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
}
