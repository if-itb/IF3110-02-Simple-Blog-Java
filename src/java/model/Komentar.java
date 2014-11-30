/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Afik
 */
@ManagedBean
@RequestScoped
public class Komentar {

    /**
     * Creates a new instance of Komentar
     */
    
    private int PID;
    private int KID;
    private String Nama;
    private String Email;
    private String Tanggal;
    private String Komentar;
    
    public Komentar() {
        PID = 0;
        KID = 0;
        Nama = new String();
        Email = new String();
        Tanggal = new String();
        Komentar = new String();
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

    /**
     * @return the KID
     */
    public int getKID() {
        return KID;
    }

    /**
     * @param KID the KID to set
     */
    public void setKID(int KID) {
        this.KID = KID;
    }

    /**
     * @return the Nama
     */
    public String getNama() {
        return Nama;
    }

    /**
     * @param Nama the Nama to set
     */
    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Tanggal
     */
    public String getTanggal() {
        return Tanggal;
    }

    /**
     * @param Tanggal the Tanggal to set
     */
    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    /**
     * @return the Komentar
     */
    public String getKomentar() {
        return Komentar;
    }

    /**
     * @param Komentar the Komentar to set
     */
    public void setKomentar(String Komentar) {
        this.Komentar = Komentar;
    }
    
    public void AddComment(int PID) throws SQLException, IOException{
        try { 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_blog_2", "root", "");
            Statement sta = conn.createStatement();
            Tanggal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String Sql = "INSERT INTO `komentar`(`PID`, `NAMA`, `EMAIL`, `TANGGAL`, `KOMENTAR`) "
                    + "VALUES ("+PID+",'"+getNama()+"','"+getEmail()+"','"+Tanggal+"','"+getKomentar()+"')";
            int res = sta.executeUpdate(Sql);
            conn.close();
            String to = "View-Post.xhtml?id="+PID;
            //FacesContext.getCurrentInstance().getExternalContext()
                        //.redirect(to);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komentar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Komentar> fetchComment(int PID) throws SQLException{
        try {
            ArrayList<Komentar> commentList= new ArrayList<Komentar>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_blog_2", "root", "");
            Statement sta = conn.createStatement();
            String Sql = "select * from komentar where PID=" + PID;
            System.out.println(Sql);
            ResultSet res = sta.executeQuery(Sql);
            while(res.next()) {
                Komentar k = new Komentar();
                k.setPID(res.getInt(1));
                k.setKID(res.getInt(2));
                k.setKomentar(res.getString(6));
                k.setEmail(res.getString(4));
                k.setNama(res.getString(3));
                k.setTanggal(res.getString(5));
                commentList.add(k);
            }
            conn.close();
            return commentList;
        } catch (ClassNotFoundException ex) {
            if (1==1)throw new SQLException("ggal");
            Logger.getLogger(ListPost.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
