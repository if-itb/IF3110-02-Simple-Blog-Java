/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Database.DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinyu
 */
public class Users {
    private static Users instance;
    
    private Users() {
        
    }
    
    public static Users getInstance() {
        if (instance==null){
            instance = new Users();
        }
        return instance;
    }
    
    public ArrayList<User> getAllUser() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        
        ResultSet result = null;
	try {
            result = databaseAccess.selectAllRecords("user", null);
            System.out.println("Berhasil melakukan seleksi pada database");
        } catch (SQLException e1) {
            System.out.println("Gagal melkaukan seleksi pada database");
            System.out.println(e1);
            e1.printStackTrace();
	}
        
        ArrayList<User> userList = new ArrayList<User>();
        try{
            while (result.next()) {
                int idUser = result.getInt("Id_User");
                String username = result.getString("Username");
                String password = result.getString("Password");
                String email = result.getString("Email");
                String role = result.getString("Role");
                User user = new User(idUser,username,password,email);
                user.setRole(role);
                userList.add(user);
            }
        }
        catch(SQLException e2) {
            e2.printStackTrace();
        }
        
        return userList;
    }
    
    public User findUser(int userId) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        
        String condition = "Id_User="+userId;
	ResultSet result = null;
	try {
            result = databaseAccess.selectAllRecords("user", condition);
        } catch (SQLException e1) {
            System.out.println(e1);
            e1.printStackTrace();
	}
        if (result!=null){
        try{
            if (result.next()) {
                int id = result.getInt("Id_User");
                String username = result.getString("Username");
                String password = result.getString("Password");
                String email = result.getString("Email");
                User user = new User(id,username,password,email);
                user.setRole(result.getString("Role"));
                return user;
            }
        }
        catch(SQLException e2) {
            e2.printStackTrace();
        }
        }
        return null;
    }
    
    public User validateUser(String username,String password) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        try {
            databaseAccess.openConnection();
            System.out.println("Berhasil membuka koneksi");
	} catch (SQLException e) {
            System.out.println("Gagal membuka koneksi");
            System.out.println(e);
        }
        
        String condition = "Username='"+username+"' AND Password='"+password+"'";
	ResultSet result = null;
	try {
            result = databaseAccess.selectAllRecords("user", condition);
            System.out.println("Berhasil melakukan seleksi pada database");
        } catch (SQLException e1) {
            System.out.println("Gagal melkaukan seleksi pada database");
            System.out.println(e1);
            e1.printStackTrace();
	}
        try{
            if (result.next()) {
                int idUser = result.getInt("Id_User");
                String email = result.getString("Email");
                User user = new User(idUser,username,password,email);
                user.setRole(result.getString("Role"));
                databaseAccess.closeConnection();
                return user;
            }
        }
        catch(SQLException e2) {
            e2.printStackTrace();
        }
        
        return null;
        
    }
    
    public void addUser(User user) {
        ArrayList<String> columns = new ArrayList<String>(Arrays.asList("Username","Password","Role"));
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("'"+user.getUsername()+
                "'","'"+user.getPassword()+"'","'"+user.getRole()+"'"));
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.insertRecords("user",columns,values);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUser(User user) {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        
        String table = "user";
        ArrayList<String> columns = new ArrayList<String>(Arrays.asList("Username","Password","Role"));
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("'"+user.getUsername()+"'",
                "'"+user.getPassword()+"'","'"+user.getRole()+"'"));
        String condition = "Id_User="+user.getId();
        try {
            dbManager.updateRecords(table,columns,values,condition);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteUserByUsername(User user) {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        String condition = "username='"+user.getUsername()+"'";
        String tableName = "user";
        try {
            dbManager.deleteRecords(tableName,condition);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
