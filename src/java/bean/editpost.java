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
@ManagedBean(name="editpost")
@SessionScoped
public class editpost 
{
    private String judul;
    @ManagedProperty(value="#{user.author}")
    private String author;
    private Date tanggal;
    private String konten;
    @ManagedProperty(value="#{user.status}")
    private String status;

    public String getAuthor() {
        return author;
    }

    public String getJudul() {
        return judul;
    }

    public String getKonten() {
        return konten;
    }

    public String getStatus() {
        return status;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    public editpost() 
    {
    }
    
    public void changepost()
    {
        System.out.println("edit post");
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "SELECT * FROM post WHERE (judul, author, tanggal, konten, status) = (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(2, judul);
            ps.setString(3, author);
            ps.setDate(4, (java.sql.Date) tanggal);
            ps.setString(5, konten);
            ps.setString(6, status);
            int executeUpdate = ps.executeUpdate();
            if(executeUpdate > 0)
            {
                System.out.println("update succesful");
            }
            else
            {
                System.out.println("update failure");
            }
            judul = ps.getResultSet().getNString("judul");
            konten = ps.getResultSet().getNString("konten");
            tanggal = ps.getResultSet().getDate("tanggal");
            con.close();
        }
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
}
