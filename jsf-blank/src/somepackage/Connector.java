package somepackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Connector {

	private Connection con;
	private String database, user, pass;
	private static String LOCAL = "jdbc:mysql://localhost/";
	
	public Connector(String db, String user, String pass) throws SQLException{
		database=db;
		this.user=user;
		this.pass=pass;
	}
	
	private void makeConnection() throws SQLException {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {}
		
		//get database connection
		con = (Connection) DriverManager.getConnection(LOCAL+database,user,pass);
	}
	
	public void executeUpdate(String update) throws SQLException {
		makeConnection();
		Statement statement = (Statement)con.createStatement();
		statement.executeUpdate(update);
		con.close();
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		makeConnection();
		Statement statement = (Statement)con.createStatement();
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}
	
	public void closeConnection() throws SQLException{
		con.close();
	}
	
}
