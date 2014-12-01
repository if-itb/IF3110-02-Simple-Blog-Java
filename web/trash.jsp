<!-- 
    Document   : index
    Created on : Nov 24, 2014, 4:14:48 PM
    Author     : adwisatya
-->

<%@page import= "User.userPaket" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "Post.Post" %>
<%@page import= "User.User" %>
<%@page import= "Post.PostBean" %>
<!DOCTYPE html>
<html>
<head>

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

<title>Simple Blog of Bangsatya | Trash</title>
</head>

<body class="default">
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

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          <% if (typeC!=3){ %>
				<li class="art-list-item">
					<div class="art-list-item-title-and-time">
        			</div>
        			You can't see deleted post
				</li>	
			<% } else {
				PostBean pBean =  new PostBean();
				for(int i=0;i<pBean.listManyPost().size();i++){
					if(pBean.listManyPost().get(i).getStatus() == 3){
			%>
			<li class="art-list-item">
				<div class="art-list-item-title-and-time">
					<h2 class="art-list-title"><a href="show_post.jsp?id=<% out.println(pBean.listManyPost().get(i).getId()); %>"><% out.println(pBean.listManyPost().get(i).getJudul()); %></a></h2>
					<div class="art-list-time"><% out.println(pBean.listManyPost().get(i).getTanggal()); %></div>
					<div class="art-list-owner">Owner:&nbsp;<% out.println(pBean.listManyPost().get(i).getOwner()); %></div>
				</div>
				<p><% out.println(pBean.listManyPost().get(i).getKonten()); %></p>
				<p>
				<% if (typeC!=4){
				%>
					<a href="handler/recovPost.jsp?id=<% out.println(pBean.listManyPost().get(i).getId()); %>">Recover</a>
					 | <a href="edit.jsp?id=<% out.println(pBean.listManyPost().get(i).getId()); %>">Edit</a>
					 | <a href="#" onclick="return ConfirmDelete(<% out.println(pBean.listManyPost().get(i).getId()); %>);">Hapus</a>
				<% } %>
				</p>
				
			</li>	
			<%
					}
				}
			}
			%>
          </ul>
        </nav>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</div>
<script>
    function ConfirmDelete(nomor)
    {
      var x = confirm("Apakah Anda yakin menghapus post ini?");
      if (x)
          return window.location.assign('handler/delPost.jsp?id='+nomor);
      else
        return false;
    }
</script>

</body>
</html>
