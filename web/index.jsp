<%-- 
    Document   : index
    Created on : Nov 24, 2014, 4:14:48 PM
    Author     : adwisatya
--%>

<%@page import="User.userPaket"%>
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

<title>Simple Blog of Bangsatya</title>
</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.php"><h1>Simple-Blog<span>-of-</span>Bang-Satya-Ilmi-Ojan</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.jsp">+ Tambah Post</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
			<%
				PostBean pBean =  new PostBean();
				for(int i=0;i<pBean.listManyPost().size();i++){
			%>
					
					
					
					
			<li class="art-list-item">
				<div class="art-list-item-title-and-time">
					<h2 class="art-list-title"><a href="show_post.php?id=<% out.println(pBean.listManyPost().get(i).getId()); %>"><% out.println(pBean.listManyPost().get(i).getJudul()); %></a></h2>
					<div class="art-list-time"><% out.println(pBean.listManyPost().get(i).getTanggal()); %></div>
					<div class="art-list-owner">Owner:&nbsp;<% out.println(pBean.listManyPost().get(i).getOwner()); %></div>
				</div>
				<p><% out.println(pBean.listManyPost().get(i).getKonten()); %></p>
				<p>
				  <a href="edit_post.php?id=<% out.println(pBean.listManyPost().get(i).getId()); %>">Edit</a> | <a href="#" onclick="return ConfirmDelete(<% out.println(pBean.listManyPost().get(i).getId()); %>);">Hapus</a>
				</p>
				
			</li>	
			<%
				}
			%>
          </ul>
        </nav>
    </div>
</div>

<footer class="footer">
    <div class="back-to-top"><a href="">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&Psi;</div>
    <aside class="offsite-links">
        Asisten IF3110 /
        <a class="rss-link" href="rss/rss.php">RSS</a> /
        <br>
        <a class="twitter-link" href="http://twitter.com/YoGiiSinaga">Yogi</a> /
        <a class="twitter-link" href="http://twitter.com/sonnylazuardi">Sonny</a> /
        <a class="twitter-link" href="http://twitter.com/fathanpranaya">Fathan</a> /
        <br>
        <a class="twitter-link" href="#">Renusa</a> /
        <a class="twitter-link" href="#">Kelvin</a> /
        <a class="twitter-link" href="#">Yanuar</a> /
        
    </aside>
</footer>

</div>
<script>
    function ConfirmDelete(nomor)
    {
      var x = confirm("Apakah Anda yakin menghapus post ini?");
      if (x)
          return window.location.assign('delete.php?id='+nomor);
      else
        return false;
    }
</script>

</body>
</html>
