<%-- 
    Document   : header
    Created on : Nov 19, 2014, 5:08:57 PM
    Author     : A46CB
--%>

<%
    String email = null;
    String nama = null;
    String role = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                }
                if (cookie.getName().equals("nama")) {
                    nama = cookie.getValue();
                }
                if (cookie.getName().equals("role")) {
                    role = cookie.getValue();
                }
            }
        }
    if ((session.getAttribute("email") != null) || (email != null)) {

        if (session.getAttribute("email") == null) {
            session.setAttribute("email", email);
        }
      } 
    if ((session.getAttribute("nama") != null) || (nama != null)) {

        if (session.getAttribute("nama") == null) {
            session.setAttribute("nama", nama);
        }
      } 
    if ((session.getAttribute("role") != null) || (role != null)) {

        if (session.getAttribute("role") == null) {
            session.setAttribute("role", role);
        }
      } 
  %>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="Deskripsi Blog">
<meta name="author" content="Judul Blog">

<!-- Twitter Card -->
<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="omfgitsasalmon">
<meta name="twitter:title" content="Simple Blog">
<meta name="twitter:description" content="Deskripsi Blog">
<meta name="twitter:creator" content="Simple Blog">
<meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">

<meta property="og:type" content="article">
<meta property="og:title" content="Simple Blog">
<meta property="og:description" content="Deskripsi Blog">
<meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
<meta property="og:site_name" content="Simple Blog">

<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="stylesheet" type="text/css" href="assets/css/custom.css" />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>${param.pageTitle}</title>


</head>

<body class="default">
<div class="wrapper">
<div class="headerblog">
    <div id="logoblog">
    <a style="border:none;"href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <!--<h1>Simple<span>-</span>Blog</h1>-->
    </div>
    <nav class="nav">
        <ul class="nav-primary">
            <li><a href="index.jsp">Home</a></li>
            <% if ((role != null) && ((role.equals("admin")) || (role.equals("owner")))) { %><li>/</li><li><a href="new_post_image.jsp">Tambah Post</a></li><% } %>
            <% if ((role != null) && ((role.equals("admin")) || (role.equals("editor")))) { %><li>/</li><li><a href="publish_post.jsp">Publish Post</a></li><% } %>
            <% if ((role != null) && (role.equals("admin"))) { %><li>/</li><li><a href="restore_post.jsp">Restore Post</a></li><% } %>
            <% if ((role != null) && (role.equals("admin"))) { %><li>/</li><li><a href="list_user.jsp">User</a></li><% } %>
            <% if (email != null) { %><li>/</li><li><a href="logout.jsp">Logout</a></li><% } %>
            <% if ((email == null) && (role == null) && (nama == null)) { %><li>/</li><li><a href="login.jsp">Login</a></li><% } %>
        </ul> 
    </nav>
</div>