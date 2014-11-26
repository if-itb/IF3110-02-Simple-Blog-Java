//Delete
//DataHandler.java

//Method untuk men-delete data


public class DataHandler {
	public String deletePostById(int id) throws SQLException { }

	getDBConnection();

	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	sqlString = "DELETE FROM post_table WHERE post_id = " + id;
	System.out.println("\nExecuting: " + sqlString);

	stmt.execute(sqlString);

	return "success";

}
