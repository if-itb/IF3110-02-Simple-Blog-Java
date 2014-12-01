/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author TOSHIBA
 */

public class ManUser implements Serializable{

    String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
    String uName = "root";
    String pass = "";
    
    String usrname;
    String passwrd;
    String nama_usr;
    String email_usr;
    String role;

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getNama_usr() {
        return nama_usr;
    }

    public void setNama_usr(String nama_usr) {
        this.nama_usr = nama_usr;
    }

    public String getEmail_usr() {
        return email_usr;
    }

    public void setEmail_usr(String email_usr) {
        this.email_usr = email_usr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public static class User{
        String usrname;
        String passwrd;
        String nama_usr;
        String email_usr;

        public String getEmail_usr() {
            return email_usr;
        }

        public void setEmail_usr(String email_usr) {
            this.email_usr = email_usr;
        }
        String role;

        public User(String usrname, String passwrd, String nama_usr, String email_usr, String role) {
            this.usrname = usrname;
            this.passwrd = passwrd;
            this.nama_usr = nama_usr;
            this.email_usr = email_usr;
            this.role = role;
        }
        
        public String getUsrname() {
            return usrname;
        }

        public void setUsrname(String usrname) {
            this.usrname = usrname;
        }

        public String getPasswrd() {
            return passwrd;
        }

        public void setPasswrd(String passwrd) {
            this.passwrd = passwrd;
        }

        public String getNama_usr() {
            return nama_usr;
        }

        public void setNama_usr(String nama_usr) {
            this.nama_usr = nama_usr;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
        
        
        
    }
    private ArrayList<User> users;
    
    public ArrayList<User> getUsers() throws SQLException{
            
        Connection conn = null;
        Statement stmnt = null;
        
        ArrayList<User> users = new ArrayList<User>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("Unable to load Driver");
                
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM `usr`";
            ResultSet rSet = stmnt.executeQuery(sqlStr);                    
                    while (rSet.next()){
                            
                            String usrname = rSet.getString("usrname");
                            String nama_usr = rSet.getString("nama_usr");
                            String email_usr = rSet.getString("email_usr");
                            String role = rSet.getString("role");
                            User user = new User(usrname, passwrd, nama_usr, email_usr, role);
                            if (!"admin".equals(role)){
                                users.add(user);
                            }
                            
                            System.out.println(users);
                    }
        } catch (SQLException ex) {
            // Step 1: Create a database "Connection" object
            ex.printStackTrace();
            System.out.println("Unable to connect to database");
            
        }
        return users;
    }
    
    	public String addAction() {
	    
//		User user = new User(this.usrname, this.passwrd, 
//			this.nama_usr, this.email_usr, this.role);
//		
//		users.add(user);
                Connection conn = null;
        Statement stmnt = null;
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "INSERT INTO `usr` (`usrname`, `passwrd`, `nama_usr`, `email_usr`, `role`) VALUES ( '" + usrname + "', '" + passwrd + "', '" + nama_usr + "', '" + email_usr + "', '" + role + "')";
            
           stmnt.executeUpdate(sqlStr);
           //clean field
           setUsrname("");
           setEmail_usr("");
           setNama_usr("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Unable to connect to database");
            System.out.print(ex.toString());
        }finally {
            System.out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmnt != null) {
                    stmnt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }		
		return null;
    }
        
    public String delAction(User user) {
	    
//		User user = new User(this.usrname, this.passwrd, 
//			this.nama_usr, this.email_usr, this.role);
//		
//		users.add(user);
                Connection conn = null;
        Statement stmnt = null;
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "DELETE FROM `usr` WHERE `usrname`='"+user.usrname+"'";
            
           stmnt.executeUpdate(sqlStr);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Unable to connect to database");
            System.out.print(ex.toString());
        }finally {
            System.out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmnt != null) {
                    stmnt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }		
//	users.remove(user);	
        return null;
    }
    
    
}
    
    
