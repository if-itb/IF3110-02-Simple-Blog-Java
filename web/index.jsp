<%-- 
    Document   : index
    Created on : Nov 25, 2014, 3:06:33 PM
    Author     : user
--%>

<%@page import="java.util.*"%>
<%@page import="data.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>Simple Blog</title>


</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="publish.jsp">View Unpublish Post</a></li>
        <li><a href="add_post.jsp">+ Tambah Post</a></li>
    </ul>
</nav>
<%
    Connector connector = new Connector();
    ArrayList<Post> listpost;
    listpost = connector.getPost(1);
    connector.closeConnection();
%>
<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
              <%
                int i = 0;
                while(i < listpost.size()){
              %>
                    <li class="art-list-item">
                        <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id=<%=listpost.get(i).getPostID()%>"><%=listpost.get(i).getTitle()%></a></h2>
                            <div class="art-list-time"><%=listpost.get(i).getDate()%></div>
                        </div>
                        <p><%=listpost.get(i).getContent()%> &hellip;</p>
                        <p>
                            <a href="edit.jsp?id=<%=listpost.get(i).getPostID()%>">Edit</a> | <a href="javascript:confirmDelete(<%out.print(listpost.get(i).getPostID());%>)">Hapus</a>
                        </p>
                    </li>
            <% 
                    i++;
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
        <a class="rss-link" href="#rss">RSS</a> /
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


<script type="text/javascript" src="assets/js/fittext.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>
<script type="text/javascript">
  var ga_ua = '{{! TODO: ADD GOOGLE ANALYTICS UA HERE }}';

  (function(g,h,o,s,t,z){g.GoogleAnalyticsObject=s;g[s]||(g[s]=
      function(){(g[s].q=g[s].q||[]).push(arguments)});g[s].s=+new Date;
      t=h.createElement(o);z=h.getElementsByTagName(o)[0];
      t.src='//www.google-analytics.com/analytics.js';
      z.parentNode.insertBefore(t,z)}(window,document,'script','ga'));
      ga('create',ga_ua);ga('send','pageview');
</script>
<!--
<%
              /*  out.println("<script>");
out.println("function confirmDelete(id) {");
    out.println("var x;");
    out.print("if (confirm(");out.print('"');out.print("Apakah Anda yakin ingin menghapus post ini?");out.print('"');out.println(" == true) {");
        Connector con = new Connector();
        int id = Integer.parseInt(request.getParameter("id"));
        con.deletePost(id);
        con.closeConnection();
    out.println("} else {");
    out.println('}');
out.println('}');
out.println("</script>"); */ %>
</body>
</html>
