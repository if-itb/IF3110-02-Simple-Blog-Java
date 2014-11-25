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
	try {
            host = "localhost";
            user = "luthfi";
            pass = "underground";
            db_name = "wbd2";
            String connectionURL = "jdbc:mysql://" + host + "/" + db_name;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(connectionURL, user, pass);
            statement = (Statement) connection.createStatement();
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {}
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
        ResultSet result = null;
	try {
            if ("".equals(where)) {
		result = statement.executeQuery("SELECT * FROM `" + Table + "`");
            } else {
		result = statement.executeQuery("SELECT * FROM `" + Table + "` " + where);
		where = "";
            }
	} catch (Exception e) {}
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
            return statement.executeUpdate("INSERT INTO `" + Table + "` " + ColName + " VALUES " + Values);
	} catch (Exception e) {
            return 0;
	}
    }

    /**
     * Delete tuples from table
     * @param Table Name of table
     */
    public void Delete(String Table) {
	try {
            if ("".equals(where)) {
		statement.executeUpdate("DELETE FROM `" + Table + "`");
            } else {
		statement.executeUpdate("DELETE FROM `" + Table + "` " + where);
		where = "";
            }
	} catch (Exception e) {
	}
    }

    /**
     * Execute "UPDATE" query
     * @param Table Name of table
     * @param Col Array of column
     * @param Val Array of Value
     */
    public void Update(String Table, String Col[], String Val[]) {
	try {
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
                    statement.executeUpdate("UPDATE `" + Table + "` SET " + set);
		} else {
                    statement.executeUpdate("UPDATE `" + Table + "` SET " + set + " " + where);
                    where = "";
		}
            }
	} catch (Exception e) {}
    }

    public static void main(String[] args) {
	MySQL data = new MySQL();
    }
}
