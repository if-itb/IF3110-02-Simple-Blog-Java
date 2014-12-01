package wbd.tubesII;

/**
 *
 * @author Asep Saepudin
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection con;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";	
    private static final String M_CONN_STRING = "jdbc:mysql://localhost:3306/wbdhebat";
    
    public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            processException(e);
            return null;
        }
        return con;
    }
    
    public static void processException(SQLException e) {
	System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }
    
}
