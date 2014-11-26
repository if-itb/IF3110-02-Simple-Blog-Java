// Bagian dari DataHandler class

//Method untuk mengidentifikasi record post
//pilih/set data (yg nanti akan dihapus)

	public Post findPostById(int id) throws SQLException { }

	Post selectedPst = new Post();

	getDBConnection();

	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	query = "SELECT * FROM post_table WHERE post_id = " + id;
	System.out.println("\nExecuting: " + query);

	rset = stmt.executeQuery(query);

	while (rset.next()) {
		selectedPst.setPostId(new Integer(rset.getInt("post_id")));
		selectedPst.setTitle(rset.getString("title"));
		selectedPst.setDate(rset.getString("date"));
		selectedPst.setContent(rset.getString("content"));
	}

	return selectedPst;

