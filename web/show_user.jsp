<%-- 
    Document   : show_user
    Created on : Nov 20, 2014, 6:45:17 PM
    Author     : muntahailmi
--%>
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
	<title>Simple Blog of Bangsatya | View User</title>
</head>
<body>
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

<div id="home">
    <div class="posts">
        <nav class="art-list">
          	<ul class="art-list-body">
	          <% if (typeC!=3){ %>
					<li class="art-list-item">
						<div class="art-list-item-title-and-time">
	        			</div>
	        			You can't see any user
					</li>	
				<% } else {%>
				<table border=1 style="align:center; margin: 0 auto; padding-top: 50px;" >
					<%
					try{
						String id = request.getParameter("id");
						Connection connection = null;
						Statement statement = null;
						ResultSet rs = null;
						Class.forName(xDRIVER);
						connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
						statement = connection.createStatement();
						String Data = "select * from users where no = '"+id+"'";
						rs = statement.executeQuery(Data);
						
						while(rs.next()){
					%>
					<tr>
						<td colspan="2" align="center">
							<h4><%=rs.getString("nama")%></h4>
						</td>
					</tr>
					<tr>
						<td>
							Nama : <%=rs.getString("nama")%>
						</td>
					</tr>
					<tr>
						<td>
							E-mail : <%=rs.getString("email")%>
						</td>
					</tr>
					<tr>
						<td>
							Status : <%=rs.getString("status").equals("3")?"Admin":
										rs.getString("status").equals("1")?"Owner":
										rs.getString("status").equals("2")?"Editor":"Guest"%>
						</td>
					</tr>
					<tr>
						<td>
							Username : <%=rs.getString("username")%>
						</td>
					</tr>
					<tr>
						<td>
							Password : <span style='color:#000000; background:#000000' onmouseover="style.color='#ffffff'" onmouseout="style.color='#000000'"><%=rs.getString("password")%></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<a href="update_user.jsp?id=<%=rs.getString("no")%>">Edit</a> | <a href="#" onclick="return ConfirmDelete(<%=rs.getString("username")%>);">Hapus</a>
						</td>
					</tr>
					<%
						}
						rs.close();
						statement.close();
						connection.close();
					}catch(Exception ex){
						out.println("Gagal tersambung. Terdapat kesalahan.");
					}	
					%>
				</table>
				<% } %>
			</ul>
        </nav>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</div>

<script>
    function ConfirmDelete(username)
    {
      var x = confirm("Apakah Anda yakin menghapus post ini?");
      if (x)
          return window.location.assign('handler/delete_user.jsp?user_username='+username);
      else
        return false;
    }
</script>
</body>
</html>
