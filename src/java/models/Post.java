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
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import services.DBConnector;

@ManagedBean(name = "post")
@SessionScoped
public class Post implements Serializable {

    private final String tablename = "post";
    private boolean isNewRecord;
    private int id;
    private String judul;
    private String tanggal;
    private String konten;
    private boolean published;
    private int userId;
    private ArrayList<Comment> comments;

    public void clearAttributes(){
        this.setId(0);
        this.setJudul(null);
        this.setTanggal(null);
        this.setKonten(null);
        this.setPublished(false);
    }
    
    public boolean loadComments() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            this.comments = new ArrayList<>();
            String query = "SELECT * FROM comment WHERE post_id=" + id;
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
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
            if (result.next()) {
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

            if (this.isNewRecord) {
                String query
                        = "INSERT IGNORE INTO " + tablename
                        + "(judul,tanggal,konten,user_id,published)"
                        + "VALUES('" + this.getJudul() + "','" + this.getTanggal() + "','" + this.getKonten() + "','" + this.getUserId() + "','" + (this.getPublished()?1:0) + "')";
                System.out.println(query);
                st.executeUpdate(query);
            } else {
                String query = "UPDATE " + tablename
                        + " SET judul='" + this.getJudul() + "',tanggal='" + this.getTanggal() + "',konten='" + this.getKonten() + "',user_id='" + this.getUserId() + "',published=" +(this.getPublished()?1:0)
                        + " WHERE id=" + this.getId();
                System.out.println(query);
                st.executeUpdate(query);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();
            String query = "DELETE FROM " + tablename
                    + "  WHERE id=" + this.id;
            System.out.println(query);
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
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

    public boolean getPublished() {
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
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM comment WHERE post_id=" + this.id + " ORDER BY created_at";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);
            this.comments = new ArrayList<>();
            while (result.next()) {
                Comment comment = new Comment();
                comment.setId(result.getInt("id"));
                comment.setEmail(result.getString("email"));
                comment.setCreatedAt(result.getTimestamp("created_at").toString());
                comment.setKonten(result.getString("konten"));
                comment.setNama(result.getString("nama"));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String showUpdate() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        return req.getContextPath() + "/faces/post/update.xhtml";
    }

    public boolean getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

}
