/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Afik
 */
public class Post {

    /**
     * Creates a new instance of Post
     */
    private String Judul;
    private String Konten;
    private String Date;
    private String Author;
    private boolean Published;
    @ManagedProperty(value="#{id}")
    private int PID;
    
    public Post() {
        
    }

    /**
     * @return the Judul
     */
    public String getJudul() {
        return Judul;
    }

    /**
     * @param Judul the Judul to set
     */
    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    /**
     * @return the Konten
     */
    public String getKonten() {
        return Konten;
    }

    /**
     * @param Konten the Konten to set
     */
    public void setKonten(String Konten) {
        this.Konten = Konten;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return the Author
     */
    public String getAuthor() {
        return Author;
    }

    /**
     * @param Author the Author to set
     */
    public void setAuthor(String Author) {
        this.Author = Author;
    }

    /**
     * @return the Published
     */
    public boolean isPublished() {
        return Published;
    }

    /**
     * @param Published the Published to set
     */
    public void setPublished(boolean Published) {
        this.Published = Published;
    }

    /**
     * @return the PID
     */
    public int getPID() {
        return PID;
    }

    /**
     * @param PID the PID to set
     */
    public void setPID(int PID) {
        this.PID = PID;
    }
    public Post fetchPost(int id) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_blog_2", "root", "");
            Statement sta = conn.createStatement();
            String Sql = "select * from entries where PID="+id;
            ResultSet rs = sta.executeQuery(Sql);
            rs.next();
            this.setJudul(rs.getString(2));
            this.setDate(rs.getString(3));
            this.setKonten(rs.getString(4));
            this.setPID(rs.getInt(1));
            this.setAuthor(rs.getString(6));
            this.setPublished(rs.getBoolean(5));
            conn.close();
            return this;
        } catch (ClassNotFoundException ex) {
            if (1==1)throw new SQLException("ggal");
            Logger.getLogger(ListPost.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
