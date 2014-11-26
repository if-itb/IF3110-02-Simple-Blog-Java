//Unpublished.jsp

//View unpublished list: can do edit & publish

//sesuaikan tampilan



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Blog</title>
<link rel="stylesheet" href="images/style.css" type="text/css" charset="utf-8" />
</head>
<body>

<% 
	category=request.getParameter("category");
	//if(category!=null)
	//category=Integer.parseString(category);

	//rs=stmt.executeQuery("Select * from post_table where category="+category);
	rs=stmt.executeQuery("Select * from post_table where category='unpublished');
	if(rs.next()) {
		title=rs.getString("title");
		date=rs.getString("date");
		content=rs.getString("content");
		category=rs.getString("category");
		<a href=\"edit.jsp?pstid=" + rset.getInt(1) +"\">Edit</a>
		<a href=\"GoPublish.jsp?pstid=" + rset.getInt(1) + "\">Publish</a>");
	}
%>