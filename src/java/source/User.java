/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author akhfa
 */
public class User {
    private String username;
    private String password;
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
     * @param _role Role dari user
     */
    public User(String _username, String _password, String _role){
        username = _username;
        password = _password;
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
    
    public void setRole(String _role)
    {
        role = _role;
    }
    
    public String getRole()
    {
        return role;
    }
    
    /**
     * Fungsi untuk user melakukan login dengan menggunakan username dan password yang telah di set sebelumnya
     * @return True jika login berhasil, dan false jika gagal
     * @throws java.sql.SQLException
     */
    public boolean successLogin() throws SQLException
    {
        KoneksiDatabase.setUser("root");
        KoneksiDatabase.setPassword("akhfa");
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        //String query = "SELECT username, password FROM user WHERE "
        return true;
    }
}
