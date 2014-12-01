package mysql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "connect", eager = true) 
@ApplicationScoped
public class Connect {
	private String dbname = "wbd2";

    public Connect() throws SQLException{
        makeConnection();
    } 

    private Connection c;

    public  Connection makeConnection() throws SQLException {
        if (c == null) {
        new Driver();
        // buat koneksi
        c = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost/"+dbname,
                    "root",
                    "");
        }
        return c;
    } 
}
