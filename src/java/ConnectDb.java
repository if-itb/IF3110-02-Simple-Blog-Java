/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author TOSHIBA
 */
@Named(value = "connectDb")
@Dependent
public class ConnectDb {
    
    private Connection conn;
    String output2;
    
    /**
     * Creates a new instance of ConnectDb
     */
    public ConnectDb() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("Unable to load Driver");
                
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
        } catch (SQLException ex) {
            // Step 1: Create a database "Connection" object
            ex.printStackTrace();
            output2 = output2 + ex.toString();
            System.out.println("Unable to connect to database");
            
        }
    }
    
}
