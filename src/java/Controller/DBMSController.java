/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class DBMSController {
    private Connection c;
    private Statement stmt;
    
    
    public DBMSController(String DatabaseName,String Username,String Password){
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+DatabaseName,Username,Password);
            stmt = c.createStatement();
            c.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBMSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet ExecuteSQL(String Query){
        ResultSet ret = null;
        try {
            ret = stmt.executeQuery(Query);
        } catch (SQLException ex) {
            Logger.getLogger(DBMSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int ExecuteUpdate(String Query){
        int ret = -1;
        try {
            ret = stmt.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(DBMSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public void CloseConnection(){
        try {
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
