package Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author luthfi
 */
public class MySQL {
    private String host;
	private String user;
	private String pass;
	private String db_name;
	private Connection connection;
	private Statement statement;
	private String where = "";

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Where(String col, String comp) {
		if (where == "") {
			where += "WHERE " + col + "'" + comp + "'";
		} else {
			where += " AND " + col + "'" + comp + "'";
		}
	}

	public ResultSet Select(String Table) {
		ResultSet result = null;
		try {
			if (where == "") {
				result = statement.executeQuery("SELECT * FROM `" + Table + "`");
			} else {
				result = statement.executeQuery("SELECT * FROM `" + Table + "` " + where);
				where = "";
			}
		} catch (Exception e) {

		}
		return result;
	}

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

	public void Delete(String Table) {
		try {
			if (where == "") {
				statement.executeUpdate("DELETE FROM `" + Table + "`");
			} else {
				statement.executeUpdate("DELETE FROM `" + Table + "` " + where);
				where = "";
			}
		} catch (Exception e) {

		}
	}

	public int Update(String Table, String Col[], String Val[]) {
                int state = 0;
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
				if (where == "") {
					state = statement.executeUpdate("UPDATE `" + Table + "` SET " + set);
				} else {
					state = statement.executeUpdate("UPDATE `" + Table + "` SET " + set + " " + where);
					where = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
                return state;
	}

	public static void main(String[] args) {
		MySQL data = new MySQL();
	}
}