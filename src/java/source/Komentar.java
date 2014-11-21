/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rakhmatullah Yoga S
 */
public class Komentar extends Post {
    private static String Nama;
    private static String Email;
    private static String Content;
    private static Date Tanggal;
    
    /**
     * Menambahkan komentar ke dalam basis data
     * @param ID 
     * @throws java.sql.SQLException 
     */
    public void AddComment(int ID) throws SQLException {
        //login database
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("");
        KoneksiDatabase.setDatabase("localhost","simpleblogdb");
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String QueryAddComment = "";
    }
    
    /**
     * Memuat komentar ke web page
     * @param ID 
     */
    public void LoadComment(int ID) {
        
    }
}
