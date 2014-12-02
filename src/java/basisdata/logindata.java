/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author steve
 */
@Named(value = "logindata")
@SessionScoped
public class logindata {
    private String role;
    private String nama;
    private String namaAsli;
    private String email;
    private String password;
    private final String url = "jdbc:postgresql://localhost:5432/simpleblog";
    private final String databaseUser = "postgresql";
    private final String databasePassword = "persib";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaAsli() {
        return namaAsli;
    }

    public void setNamaAsli(String namaAsli) {
        this.namaAsli = namaAsli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    /**
     * Creates a new instance of logindata
     */
    public logindata() {     
    }
    
    // Mengembalikan ID dari role user tertentu dalam bentuk integer
    public int ConvertRoleToID() {
        int role_id = 9999;
        
        switch (role) {
            case "Owner":
                role_id = 0;
                break;
            case "Editor":
                role_id = 1;
                break;
            case "Admin":
                role_id = 2;
                break;
            case "Guest":
                role_id = 3;
                break;
        }
        
        return role_id;
    }
    
    public String register() {
        Statement statement;
        // Buat container perintah query ke koneksi drive
        try ( 
            // Konek ke drive jdbc postgresql
            Connection connection = DriverManager.getConnection(url,databaseUser,databasePassword)) {
            // Buat container perintah query ke koneksi drive
            statement = connection.createStatement();
            // Insert data nama, email, dan role ke basis data
            int role_id = ConvertRoleToID(); // Konversi role dari string ke integer
            if (role_id != 9999) { // ID role terbentuk
                String insertDataQuery = "INSERT INTO user(username,realname,role,email) VALUES (" + nama + "," + namaAsli + "," + role_id + "," + email;
                statement.executeUpdate(insertDataQuery);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(logindata.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "registered";
    }
    
    public String login() throws SQLException{ 
        // Penanda apakah username dan password valid atau tidak
        boolean ValidAccount = false;
        
        Statement statement;
        ResultSet record;
        // Buat container perintah query ke koneksi drive
        try ( // Konek ke drive jdbc postgresql
                Connection connection = DriverManager.getConnection(url,databaseUser,databasePassword)) {
            // Buat container perintah query ke koneksi drive
            statement = connection.createStatement();
            //  Search data username dan role pada database untuk validasi data yang dimasukkan
            record = statement.executeQuery("SELECT * FROM user");
            while (record.next()) {
                String value_username = record.getString("username");
                String value_password = record.getString("password");
                
                // data yang dimasukkan dengan di database sesuai
                if (value_username.equals(nama)) {
                    if (value_password.equals(password)) {
                        ValidAccount = true;
                    }
                }
            }
        }
        statement.close();
        record.close();
        
        if (ValidAccount) { // Akun valid, asumsi user terkait sudah pernah login sebelumnya
            return "successLogin";
        }
        else {
            return "failedLogin";
        }
    }
}
