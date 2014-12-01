/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@RequestScoped
public class addPost {
    
    private String tanggal, konten, judul;
    private String username;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public void ambilTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.tanggal = dateFormat.format(date);
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String addPost() throws IOException{
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        CookieCheck cookie = new CookieCheck();
        username = cookie.getUsrName();
        
        System.out.print(username);
        System.out.print("username sebelah sini <<");
        System.out.print(tanggal);
        System.out.print(judul);
        System.out.print(konten);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "INSERT INTO `post`(`judul`,`konten`,`tanggal_post`,`usrname`) VALUE('" +judul +"', '" +konten +"', '" +tanggal +"', '" +username +"')";
            stmnt.executeUpdate(sqlStr);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            
        } catch (SQLException e){
            
        }
        return null;
    }
    
}
