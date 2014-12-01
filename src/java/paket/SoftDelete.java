/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class SoftDelete {

    private String idpost, judul, konten, tanggal, status, del_stat;
    private ArrayList<SoftDelete> sdel = new ArrayList<SoftDelete>();
    
    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDel_stat() {
        return del_stat;
    }

    public void setDel_stat(String del_stat) {
        this.del_stat = del_stat;
    }
    /**
     * Creates a new instance of SoftDelete
     */
    public SoftDelete() {
    }
    
    public ArrayList<SoftDelete> ambilPostList(){
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
                       
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM post";
            ResultSet rs = stmnt.executeQuery(sqlStr);
            
            while (rs.next()){
                SoftDelete view = new SoftDelete();
                view.setIdpost(rs.getString("id_post"));
                view.setJudul(rs.getString("judul"));
                view.setKonten(rs.getString("konten"));
                view.setTanggal(rs.getString("tanggal_post"));
                view.setStatus(rs.getString("status"));
                view.setDel_stat(rs.getString("del_stat"));
                if (!"0".equals(view.getDel_stat().toString())){
                    sdel.add(view);
                } 
            }
            
        } catch (SQLException e){
            
        }
        return sdel;
    }
    
    public void clear(){
        sdel.clear();
    }
    
    public void restorePost(){
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
                       
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "UPDATE post SET del_stat=0 WHERE id_post=" +idpost;
            ResultSet rs = stmnt.executeQuery(sqlStr);
            
        } catch (SQLException e){
            
        }
    }
}
