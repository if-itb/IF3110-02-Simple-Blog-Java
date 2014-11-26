/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class Post {
    int id_post;
    String judul;
    String tanggal;
    String konten;
    boolean status;
    Connection con;
    Statement ps;
    ResultSet rs;
    String SQL_Str;

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String setDBP(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from post where id_post=" + id;
        rs = ps.executeQuery(SQL_Str);
        rs.next();
        id_post = rs.getInt(1);
        judul = rs.getString(2);
        tanggal = rs.getString(3);
        konten = rs.getString(4);
        status = rs.getBoolean(5);
        con.close();
        return "post";
    }
    
    public String setDBE(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from post where id_post=" + id;
        rs = ps.executeQuery(SQL_Str);
        rs.next();
        id_post = rs.getInt(1);
        judul = rs.getString(2);
        tanggal = rs.getString(3);
        konten = rs.getString(4);
        status = rs.getBoolean(5);
        con.close();
        return "edit";
    }
    
    public void initPost () {
        judul = "";
        tanggal = "";
        konten = "";
    }
    
    public String insertPost() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "INSERT INTO post(judul, tanggal, konten, status) VALUES ('"+judul+"','"+tanggal+"','"+konten+"',false)";
        ps.executeUpdate(SQL_Str);
        con.close();
        return "insert";
    }
    
    public String editPost() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "UPDATE post SET judul='"+judul+"', tanggal='"+tanggal+"',konten='"+konten+"' WHERE id_post=" + id_post;
        ps.executeUpdate(SQL_Str);
        con.close();
        return "edit";
    }
    
    public void hapusPost(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "DELETE FROM post WHERE id_post=" + id;
        ps.executeUpdate(SQL_Str);
        con.close();
    }
}
