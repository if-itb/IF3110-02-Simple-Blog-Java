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
import java.util.ArrayList;
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
    private final String userSQL = "root2";
    private final String passSQL = "akhfa";
    private final String urlSQL = "localhost";
    private final String databaseName = "blog";
    
    /**
     * Konstruktor ini digunakan jika ingin menggunakan fungsi fungsi yang ada
     * tanpa menginisialisasi usernya
     */
    public User()
    {
        
    }
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
    
    public String getRole() throws SQLException
    {
        if(role == null)
        {
            //Load role dari database
            System.out.println("Masuk if");
            KoneksiDatabase.setUser(userSQL);
            KoneksiDatabase.setPassword(passSQL);
            KoneksiDatabase.setDatabase(urlSQL,databaseName);

            Connection koneksi = KoneksiDatabase.getKoneksi();
            Statement statement = koneksi.createStatement();
            String query = "SELECT username, role FROM user WHERE username = '" + username +"'";
            System.out.println(query);
            
            ResultSet result = statement.executeQuery(query);
            if(result.next())
            {
                role = result.getString("role");
            }
            System.out.println("role = " + role);
        }
        return role;
    }
    
    /**
     * Method untuk me-list semua user yang terdaftar di database
     * @return
     * @throws SQLException 
     */
    public ArrayList<dataUser> getAllUser() throws SQLException
    {
        ArrayList<dataUser> listUser = new ArrayList<>();
        
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "SELECT username, nama, email, role FROM user";
        System.out.println(query);
        
        ResultSet result = statement.executeQuery(query);
        while(result.next())
        {
            dataUser user = new dataUser();
            user.username = result.getString("username");
            user.nama = result.getString("nama");
            user.email = result.getString("email");
            user.role = result.getString("role");
            listUser.add(user);
        }
        for (dataUser user:listUser)
        {
            System.out.println(user.username);
            System.out.println(user.nama);
            System.out.println(user.email);
            System.out.println(user.role);
        }
        result.close();
        statement.close();
        return listUser;
    }
    
     /**
     * Prosedur untuk mendapatkan 1 user untuk di load ke form update user.
     * username harus diset terlebih dahulu dengan prosedure user.setUsername("username").
     * Mengcopy nama, password, email, role dari database ke attribute berdasarkan username yang di set.
     * @throws SQLException 
     */
    public void getOneUser() throws SQLException
    {
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "SELECT password, nama, email, role FROM user WHERE username = '" + username +"'";
        System.out.println(query);
        
        ResultSet result = statement.executeQuery(query);
        if(result.next())
        {
            this.nama = result.getString("nama");
            this.password = result.getString("password");
            this.email = result.getString("email");
            this.role = result.getString("role");
        }
        result.close();
        statement.close();
    }
    
    /**
     * Fungsi untuk mengupdate user berdasarkan username
     * Semua attribut yang akan di update harus diset terlebih dahulu menggunakan setter.
     * @throws SQLException 
     */
    public void updateUser() throws SQLException
    {
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "UPDATE user SET password = ?, "
                                        + "nama = ?,"
                                        + "email = ?,"
                                        + "role = ?"
                        + "WHERE username = ?";
        try (PreparedStatement preStat = koneksi.prepareStatement(query)) {
            preStat.setString(1, password);
            preStat.setString(2, nama);
            preStat.setString(3, email);
            preStat.setString(4, role);
            preStat.setString(5, username);
            preStat.executeUpdate();
        }
    }
    
    /**
     * Fungsi untuk menghapus 1 user dengan username tertentu.
     * username harus diset terlebih dahulu dengan prosedure user.setUsername("username").
     * @throws SQLException 
     */
    public void deleteUser() throws SQLException
    {
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        
        Connection koneksi = KoneksiDatabase.getKoneksi();
        Statement statement = koneksi.createStatement();
        String query = "DELETE FROM user WHERE username = '" + username +"'";
        System.out.println(query);
        
        try (PreparedStatement preStat = koneksi.prepareStatement(query)) {
            preStat.executeUpdate();
            preStat.close();
        }
    }
    /**
     * Fungsi untuk user melakukan login dengan menggunakan username dan password 
     * yang telah di set sebelumnya
     * @return True jika login berhasil, dan false jika gagal
     * @throws java.sql.SQLException
     */
    public boolean successLogin() throws SQLException
    {
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        
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
        KoneksiDatabase.setUser(userSQL);
        KoneksiDatabase.setPassword(passSQL);
        KoneksiDatabase.setDatabase(urlSQL,databaseName);
        Connection koneksi = KoneksiDatabase.getKoneksi();
        String query = "INSERT INTO user (username,password,nama,email,role) VALUES (?, ?, ?, ?, ?)";
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
     * Memasukkan user ke dalam database dengan userdatabase dan passwordDatabase yang custom
     * User di create terlebih dahulu dengan constructor dengan parameter (username, password, dan role).
     * @throws SQLException 
     */
    public void masukDatabase(String _userDatabase, String _passwordDatabase) throws SQLException
    {
        KoneksiDatabase.setUser(_userDatabase);
        KoneksiDatabase.setPassword(_passwordDatabase);
        KoneksiDatabase.setDatabase("localhost","blog");
        Connection koneksi = KoneksiDatabase.getKoneksi();
        String query = "INSERT INTO user (username,password,nama,email,role) VALUES (?, ?, ?, ?, ?)";
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
            User pertama = new User("akhfa3","akhfa2");
            if(pertama.successLogin())
            {
                System.out.println("sukses login");
            }
            else
            {
                System.out.println("gagal login");
            }
            
//            User kedua = new User("akhfa5", "akhfa", "namaAkhfa","akhmadfakhoni@gmail.com","editor");
//            kedua.masukDatabase();
//            kedua.getAllUser();
            
            //Test delete
//            User usertest = new User();
//            usertest.setUsername("akhfa4");
//            usertest.deleteUser();
            
            //Test getOneUser
            User usertest = new User();
            usertest.setUsername("akhfa2");
            usertest.getOneUser();
            System.out.println("Sebelum update user");
            System.out.println(usertest.getUsername());
            System.out.println(usertest.getNama());
            System.out.println(usertest.getPassword());
            System.out.println(usertest.getEmail());
            System.out.println(usertest.getRole());
            
            //Test updateUser (Test getOneUser harus tetap dijalankan
            usertest.setNama("namaakhfa2");
            usertest.setRole("owner");
            usertest.updateUser();
            System.out.println("\nSetelah updateUser");
            System.out.println(usertest.getUsername());
            System.out.println(usertest.getNama());
            System.out.println(usertest.getPassword());
            System.out.println(usertest.getEmail());
            System.out.println(usertest.getRole());
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
