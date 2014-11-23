/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akhfa
 */
public class User {
    private String username;
    private String password;
    private String nama;
    private String email;
    private String role;
    
    /**
     * Membuat user baru untuk keperluan login
     * @param _username Username dari user
     * @param _password Password dari user
     */
    public User(String _username, String _password)
    {
        username = _username;
        password = _password;
    }
    
    /**
     * Untuk membuat user baru, maka attribute dari user diisi semua terlebih dulu
     * @param _username Username dari user
     * @param _password Password dari user
     * @param _nama Nama user
     * @param _email Email user
     * @param _role Role dari user
     */
    public User(String _username, String _password, String _nama, String _email, String _role){
        username = _username;
        password = _password;
        nama = _nama;
        email = _email;
        role = _role;
    }
    
    public void setUsername(String _username)
    {
        username = _username;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setPassword(String _password)
    {
        password = _password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setNama(String _nama)
    {
        nama = _nama;
    }
    
    public String getNama()
    {
        return nama;
    }
    
    public void setEmail(String _email)
    {
        email = _email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setRole(String _role)
    {
        role = _role;
    }
    
    public String getRole()
    {
        return role;
    }
    
    /**
     * Fungsi untuk user melakukan login dengan menggunakan username dan password 
     * yang telah di set sebelumnya
     * @return True jika login berhasil, dan false jika gagal
     * @throws java.sql.SQLException
     */
    public boolean successLogin() throws SQLException
    {
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "SELECT username, password FROM user WHERE username = '" + username +"'";
        System.out.println(query);
        
        ResultSet result = statement.executeQuery(query);
        while(result.next())
        {
            System.out.println("User = " + result.getString(1));
            System.out.println("password = " + result.getString(2));
            if(username.equalsIgnoreCase(result.getString(1)) && password.equals(result.getString(2)))
            {
                result.close();
                statement.close();
//                koneksi.close();
                return true;
            }
        }
        result.close();
        statement.close();
//        koneksi.close();
        return false;
    }
    
    /**
     * Fungsi untuk mengecek apakah login sukses atau tidak 
     * dengan user database dan password database yang custom
     * @param _userDatabase
     * @param _passwordDatabase
     * @return
     * @throws SQLException 
     */
    public boolean successLogin(String _userDatabase, String _passwordDatabase) throws SQLException
    {
        KoneksiDatabase.setUser(_userDatabase);
        KoneksiDatabase.setPassword(_passwordDatabase);
        KoneksiDatabase.setDatabase("localhost","blog");
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "SELECT username, password FROM user WHERE username = '" + username +"'";
        System.out.println(query);
        
        ResultSet result = statement.executeQuery(query);
        while(result.next())
        {
            System.out.println("User = " + result.getString(1));
            System.out.println("password = " + result.getString(2));
            if(username.equalsIgnoreCase(result.getString(1)) && password.equals(result.getString(2)))
            {
                result.close();
                statement.close();
//                koneksi.close();
                return true;
            }
        }
        result.close();
        statement.close();
//        koneksi.close();
        return false;
    }
    
    /**
     * Fungsi untuk mengecek apakah login sukses atau tidak 
     * dengan user, password, domain, dan nama database yang custom
     * @param _userDatabase
     * @param _passwordDatabase
     * @param _domain
     * @param _namaDatabase
     * @return
     * @throws SQLException 
     */
    public boolean successLogin(String _userDatabase, String _passwordDatabase, String _domain, String _namaDatabase) throws SQLException
    {
        KoneksiDatabase.setUser(_userDatabase);
        KoneksiDatabase.setPassword(_passwordDatabase);
        KoneksiDatabase.setDatabase(_domain,_namaDatabase);
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "SELECT username, password FROM user WHERE username = '" + username +"'";
        System.out.println(query);
        
        ResultSet result = statement.executeQuery(query);
        while(result.next())
        {
            System.out.println("User = " + result.getString(1));
            System.out.println("password = " + result.getString(2));
            if(username.equalsIgnoreCase(result.getString(1)) && password.equals(result.getString(2)))
            {
                result.close();
                statement.close();
//                koneksi.close();
                return true;
            }
        }
        result.close();
        statement.close();
//        koneksi.close();
        return false;
    }
    
    /**
     * Memasukkan user ke dalam database. 
     * User di create terlebih dahulu dengan constructor dengan parameter (username, password, dan role).
     * @throws SQLException 
     */
    public void masukDatabase() throws SQLException
    {
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        KoneksiDatabase.setDatabase("localhost","blog");
        Connection koneksi = KoneksiDatabase.getKoneksi();
        String query = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preStat = koneksi.prepareStatement(query)) {
            preStat.setString(1, username);
            preStat.setString(2, password);
            preStat.setString(3, nama);
            preStat.setString(4, email);
            preStat.setString(5, role);
            
            preStat.executeUpdate();
            preStat.close();
        }
//        koneksi.close();
    }
    /**
     * Testing untuk user
     * @param args 
     */
    public static void main(String[] args) {
        try {
            User pertama = new User("akhfa","akhfa");
            if(pertama.successLogin())
            {
                System.out.println("sukses login");
            }
            else
            {
                System.out.println("gagal login");
            }
            
            User kedua = new User("akhfa3", "akhfa2", "namaAkhfa","akhmadfakhoni@gmail.com","admin");
            kedua.masukDatabase();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
