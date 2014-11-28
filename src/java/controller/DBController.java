package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


public class DBController {
    private final String db_driver = "com.mysql.jdbc.Driver";
    private final String db_server = "jdbc:mysql://localhost:3306/if3110-02-simple-blog-java";
    private final String db_username = "root";
    private final String db_password = "password";
    
    private Connection connection = null;
    private static DBController instance = null;
    
    // constructor is private since it is a singleton class
    private DBController() throws SQLException, ClassNotFoundException {
        // initialize driver
        Class.forName(db_driver);
        
        // initialize connection
        connection = DriverManager.getConnection(db_server, db_username, db_password);
        if (connection == null) {
            throw new SQLException("Can't get database connection");
        }
    }
    
    public static DBController getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }
    
    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps.executeQuery();
    }
    
    public ResultSet executeQuery(String sql, String[] params) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i+1, params[i]);
        }
        return ps.executeQuery();
    }
    
    public int executeUpdate(String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps.executeUpdate();
    }
    
    public int executeUpdate(String sql, String[] params) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i+1, params[i]);
        }
        return ps.executeUpdate();
    }
}
