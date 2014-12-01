<%-- 
    Document   : addpost
    Created on : Nov 18, 2014, 5:22:59 PM
    Author     : adwisatya
--%>
<%@page import= "java.text.SimpleDateFormat"%>
<%@page import= "java.util.Date"%>
<%@page import = "java.sql.*" %>
<%@page import = "java.io.*" %>
<%@include file= "/WEB-INF/jspf/koneksi.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="description" content="Simple Blog">
	<meta name="author" content="Bangsatya">
	
	<!-- Twitter Card -->
	<meta name="twitter:card" content="summary">
	<meta name="twitter:site" content="omfgitsasalmon">
	<meta name="twitter:title" content="Simple Blog">
	<meta name="twitter:description" content="Simple Blog">
	<meta name="twitter:creator" content="Bangsatya Blog">
	<meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">
	
	<meta property="og:type" content="article">
	<meta property="og:title" content="Simple Blog">
	<meta property="og:description" content="Deskripsi Blog">
	<meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
	<meta property="og:site_name" content="Simple Blog">
	
	<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
	<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
	
	<!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<title>Simple Blog of Bangsatya | Add User</title>
</head>
<body>
<div class="wrapper">
<jsp:include page="header.jsp"/>
<%
	String usrC=" ";
	int typeC=4;
	Cookie[] cookies = null;
	cookies = request.getCookies();
	if (cookies!=null){
		for (Cookie c:cookies){
			if (c.getName().equals("LogName")){
				usrC=c.getValue();
				for (Cookie c2:cookies){
				    if (c2.getName().equals("LogType")){
							typeC=Integer.parseInt(c2.getValue());
						}
				}
			}
		}
	}
%>
<article class="art simple post" style="margin-top:120px;">
	<div class="art-body" style="margin-top:70px">
        <div class="art-body-inner">
       	<%
       	if (typeC==1) {
       	%>
		You can't add new user
       	<% } else { %>
        <h2>Add New User</h2>
        <div id="contact-area">
        <form method="post">
			<label>Nama: </label><input type="text" id="user_name" name="user_name">
			<br/>
			<label>Username: </label><input type="text" id="user_username" name="user_username">
			<br/>
			<label>Password: </label><input type="password" id="user_password" name="user_password">
			<br/>
			<label>Email: </label><input type="text" id="user_email" name="user_email">
			<br/>
			<label>Status</label>
				<select id="user_status" name="user_status">
					<option value="3"> Admin </option>
					<option value="1"> Owner </option>
					<option value="2"> Editor </option>
				</select>
			<br/>
			<input type="submit" class="submit-button" value="Tambah">
		</form>
		<%
			String user_name	=	 request.getParameter("user_name");
			String user_username = request.getParameter("user_username");
			String user_password = request.getParameter("user_password");
			String user_status = request.getParameter("user_status");
			String user_email	=	 request.getParameter("user_email");


			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			int updateQuery= 0 ;
			if(user_name != null && user_username != null && user_password != null  && user_email != null && user_status != null ){
				try{
					Connection connection = null;
					Statement statement = null;
					ResultSet rs = null;
					Class.forName(xDRIVER);
					connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
					statement = connection.createStatement();
					
					String query = "insert into users(username,password,nama,email,status,created) VALUES(\'"+user_username+"\',\'"+user_password+"\',\'"+user_name+"\',\'"+user_email+"\',\'"+user_status+"\',\'"+sdf.format(date)+"\')";
					updateQuery = statement.executeUpdate(query);
					
					if(updateQuery != 0){
						out.print("Penambahan user sukses");
						statement.close();
						connection.close();
					}

				}catch(SQLException ex){
					out.println(ex);
					out.println("Koneksi Bermasalah");
				}finally{

				}
			}			
		%>
		</div>
		<% } %>
	</div>
</div>
</article>

<jsp:include page="footer.jsp"/>
</div>
</body>
</html>
