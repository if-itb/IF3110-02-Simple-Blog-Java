/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import services.DBConnector;

public class Post implements Serializable {

    private final String tablename = "post";

    private int id;
    private String judul;
    private String tanggal;
    private String konten;
    private boolean published;
    private int userId;
    private ArrayList<Comment> comments;
    
    public boolean loadComments(){
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();
            
            this.comments = new ArrayList<>();
            String query = "SELECT * FROM comment WHERE post_id=" + id;
            ResultSet result = st.executeQuery(query);
            while (result.next()){
                Comment comment = new Comment();
                comment.setId(result.getInt("id"));
                comment.load();
                comments.add(comment);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean load(int id) {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE id=" + id + " LIMIT 1";
            ResultSet result = st.executeQuery(query);
            if (result.next()){
                this.setId(result.getInt("id"));
                this.setJudul(result.getString("judul"));
                this.setTanggal(result.getString("tanggal"));
                this.setKonten(result.getString("konten"));
                this.setPublished(result.getBoolean("published"));
                this.setUserId(result.getInt("user_id"));
                
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query
                    = "INSERT IGNORE INTO " + tablename
                    + "(judul,tanggal,konten,user_id)"
                    + "VALUES('" + this.getJudul() + "','" + this.getTanggal() + "','"+this.getKonten()+"','"+this.getUserId()+"')";
            System.out.println(query);
            st.executeUpdate(query);
            query = "UPDATE " + tablename
                    + " SET judul='"+this.getJudul()+"',tanggal='"+this.getTanggal()+"',konten='"+this.getKonten()+"',user_id='"+this.getUserId()+"'"
                    + " WHERE id="+this.getId();
            System.out.println(query);
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}
