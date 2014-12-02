//Bagian dari index.jsp

//Menambahkan link ke 'page' delete data


	while (rset.next ()) {
		out.println(
		rset.getString("title") +
		rset.getString("date") +
		rset.getString("content") +
		<a href=\"edit.jsp?pstid=" + rset.getInt(1) +"\">Edit</a>
		<a href=\"delete_action.jsp?pstid=" + rset.getInt(1) + "\">Delete</a>");
		);
	}

