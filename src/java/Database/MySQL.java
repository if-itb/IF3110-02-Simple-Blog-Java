package Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Java Class for MySQL Syntax and Connection
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah
 */
public class MySQL {
    private String host;
    private String user;
    private String pass;
    private String db_name;
    private Connection connection;
    private Statement statement;
    private String where = "";

    /**
     * Create a new instance of MySQL Connection
     */
    public MySQL() {
        host = "localhost";
        user = "root";
        pass = "chmod777";
        db_name = "wbd2";
    }

    /**
     * Create "WHERE" section on query
     * @param col Checked Column
     * @param comp Column Value
     */
    public void Where(String col, String comp) {
	if ("".equals(where)) {
            where += "WHERE " + col + "'" + comp + "'";
	} else {
            where += " AND " + col + "'" + comp + "'";
	}
    }

    /**
     * Create a table from "SELECT" query
     * @param Table Name of table
     * @return Table created from "SELECT" SQL Syntax
     */
    public ResultSet Select(String Table) {
        openConnection();
        ResultSet result = null;
	try {
            if ("".equals(where)) {
		result = statement.executeQuery("SELECT * FROM `" + Table + "`");
            } else {
		result = statement.executeQuery("SELECT * FROM `" + Table + "` " + where);
		where = "";
            }
	} catch (Exception e) {}
        closeConnection();
        return result;
    }

    /**
     * Execute "INSERT" query
     * @param Table Name of table
     * @param Col Array of column
     * @param Val Array of value
     * @return Result of executed query
     */
    public int Insert(String Table, String Col[], String Val[]) {
	openConnection();
        String ColName = "(";
	int i = 1;
	for (String col : Col) {
            if (i < Col.length) {
		ColName += col + ",";
            } else {
		ColName += col + ")";
            }
            i++;
	}
	String Values = "(";
	i = 1;
	for (String val : Val) {
            if (i < Col.length) {
		Values += "'" + val + "',";
            } else {
		Values += "'" + val + "')";
            }
            i++;
	}
	try {
            int query = statement.executeUpdate("INSERT INTO `" + Table + "` " + ColName + " VALUES " + Values);
            closeConnection();
            return query;
	} catch (Exception e) {
            return 0;
	}
    }

    /**
     * Delete tuples from table
     * @param Table Name of table
     * @return query status
     */
    public int Delete(String Table) {
	openConnection();
        try {
            int query;
            if ("".equals(where)) {
		query = statement.executeUpdate("DELETE FROM `" + Table + "`");
            } else {
		query = statement.executeUpdate("DELETE FROM `" + Table + "` " + where);
		where = "";
            }
            closeConnection();
            return query;
	} catch (Exception e) {
            return 0;
	}
    }

    /**
     * Execute "UPDATE" query
     * @param Table Name of table
     * @param Col Array of column
     * @param Val Array of Value
     * @return query status
     */
    public int Update(String Table, String Col[], String Val[]) {
	openConnection();
        try {
            int query;
            String set = "";
            if (Col.length == Val.length) {
		for (int i = 0; i < Col.length; i++) {
                    if (i < Col.length - 1) {
                    	set += Col[i] + "='" + Val[i] + "',";
                    } else {
			set += Col[i] + "='" + Val[i] + "'";
                    }
		}
		if ("".equals(where)) {
                    query = statement.executeUpdate("UPDATE `" + Table + "` SET " + set);
		} else {
                    query = statement.executeUpdate("UPDATE `" + Table + "` SET " + set + " " + where);
                    where = "";
		}
                closeConnection();
                return query;
            } else {
                return 0;
            }
	} catch (Exception e) {
           return 0;
        }
    }

    /**
     * Open connection to database
     */
    private void openConnection() {
        try {
            String connectionURL = "jdbc:mysql://" + host + "/" + db_name;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(connectionURL, user, pass);
            statement = (Statement) connection.createStatement();
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {}
    }
    
    /**
     * Close connection to database
     */
    private void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {}
    }
}
