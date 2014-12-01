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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Komentar {
    int id_kom;
    int id_post;
    String username;
    String email;
    String waktu;
    String komentar;
    Connection con;
    Statement ps;
    ResultSet rs;
    String SQL_Str;

    public int getId_kom() {
        return id_kom;
    }

    public void setId_kom(int id_kom) {
        this.id_kom = id_kom;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    
     public void insertKomen() throws ClassNotFoundException, SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        waktu = dateFormat.format(date);
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "INSERT INTO komen(id_post, username, email, waktu, komentar) VALUES ('"+id_post+"','"+username+"','"+email+"','"+waktu+"','"+komentar+"')";
        ps.executeUpdate(SQL_Str);
        con.close();
    }
}
