/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Rakhmatullah Yoga S
 */
public class Komentar extends Post {
    private static String Nama;
    private static String Email;
    private static String Comment;
    private static Timestamp Time;
    
    /**
     * Menambahkan komentar ke dalam basis data
     * @param ID 
     * @return  HTML script untuk memuat komentar yang akan digunakan oleh AJAX
     * @throws java.sql.SQLException 
     */
    public static String LoadComment(int ID) throws SQLException {
        //login database
        String HTMLcode = "";
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("");
        KoneksiDatabase.setDatabase("localhost","simpleblogdb");
        try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            String QueryLoadComment = "SELECT * FROM komentar WHERE ID="+ID+" ORDER BY Waktu ASC";
            ResultSet result = statement.executeQuery(QueryLoadComment);
            while(result.next()) {
                Nama = result.getString("Nama");
                Email = result.getString("Email");
                Comment = result.getString("Komentar");
                Time = result.getTimestamp("Waktu");
                HTMLcode +=
                        "    <li class=\"art-list-item\">\n" +
                        "        <div class=\"art-list-item-title-and-time\">\n" +
                        "            <h2 class=\"art-list-title\">" + Nama + "</h2>\n" +
                        "            <div class=\"art-list-time\">" + Time.toString() + "</div>\n" +
                        "        </div>\n" +
                        "        <p>" + Comment + "</p>\n" +
                        "    </li>";
            }
        }
        return HTMLcode;
    }
    /**
     * 
     * @param nama
     * @param email
     * @param comment
     * @param time
     * @throws SQLException 
     */
    public static void AddComment(String nama, String email, String comment, Timestamp time) throws SQLException {
        String InsertQuery;
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("");
        KoneksiDatabase.setDatabase("localhost","simpleblogdb");
        try (Connection koneksi = KoneksiDatabase.getKoneksi()) {
            Statement statement = koneksi.createStatement();
            InsertQuery = "INSERT INTO komentar (ID, Nama, Email, Komentar) VALUES ('" + Post.idPost + "', '" + nama + "', '" + email + "', '" + comment + "')";
        }
    }
    
    public String GetComment() {
        return Comment;
    }
    public String GetName() {
        return Nama;
    }
    public String GetEmail() {
        return Email;
    }
    public Timestamp GetTime() {
        return Time;
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(Komentar.LoadComment(31));
    }
}
