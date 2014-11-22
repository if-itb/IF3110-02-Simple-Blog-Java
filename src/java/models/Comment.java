/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import services.DBConnector;

public class Comment {
    private final String tablename = "comment";
    
    private int id;
    private int postId;
    private String nama;
    private String createdAt;
    private String konten;
    private String email;
    
    public boolean load() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE id=" + id;
            ResultSet result = st.executeQuery(query);
            if (result.next()){
                this.setId(result.getInt("id"));
                this.setPostId(result.getInt("post_id"));
                this.setNama(result.getString("nama"));
                this.setCreatedAt(result.getString("created_at"));
                this.setKonten(result.getString("konten"));
                this.setEmail(result.getString("email"));
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
                    + "(post_id,nama,created_at,konten,email)"
                    + "VALUES('" + this.getPostId() + "','" + this.getNama() + "','"+this.getCreatedAt()+"','"+this.getKonten()+"','"+this.getEmail()+"')";
            st.executeUpdate(query);
            query = "UPDATE " + tablename
                    + "SET post_id='"+this.getPostId()+"',nama='"+this.getNama()+"',created_at='"+this.getCreatedAt()+"',konten='"+this.getKonten()+"',email='"+this.getEmail()+"'"
                    + "WHERE id="+this.getId();
            st.executeQuery(query); 
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(){
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query
                    = "DELETE FROM "+tablename
                    + "WHERE id="+this.getId();
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
