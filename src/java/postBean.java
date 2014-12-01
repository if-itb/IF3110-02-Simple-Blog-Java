/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Fahziar
 */
@ManagedBean
@SessionScoped
public class postBean {

    /**
     * Creates a new instance of postBean
     */
    public postBean() {
        
    }
    
    
    private int id;
    private String judul;
    private String konten;
    private String date;
    private String author;
    private boolean deleted;
    private boolean published;

    /**
     * @return the judul
     */
    public String getJudul() {
        return judul;
    }

    /**
     * @param judul the judul to set
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * @return the konten
     */
    public String getKonten() {
        return konten;
    }

    /**
     * @param konten the konten to set
     */
    public void setKonten(String konten) {
        this.konten = konten;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the published
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public void delete()
    {
        System.out.println("Delete id:" + this.id);
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("UPDATE `posts` SET `deleted`=1 WHERE id=?");
            
            if (preparedStatement.executeUpdate() != 0)
            {
                this.deleted = true;
            }
                       
                
        } catch (Exception e)
        {
            System.out.println("Class UserManagementArrayBean: Failed to delete post");
        } finally {
           Close(resultSet, preparedStatement, connect);
        } 
    }
    
    private void Close(ResultSet r, PreparedStatement p, Connection c)
    {
        if (r != null)
        {
            try{
                r.close();
            } catch (Exception e)
            {
            }
        }
        
        if (p != null)
        {
            try{
                p.close();
            } catch (Exception e)
            {
            }
        }
        if (c != null)
        {
            try{
                c.close();
            } catch (Exception e)
            {
                
            }
        }
    }
    
    
}
