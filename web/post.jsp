<%-- 
    Document   : post
    Created on : Nov 26, 2014, 5:20:45 PM
    Author     : SPM
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.*"%>
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

<link rel="stylesheet" type="text/css" href="./assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<% 
    String post_id;
    post_id = request.getParameter("id"); 
    int tempID = Integer.parseInt(post_id);
    
    Connector C = new Connector();
    Post P = C.getPost(tempID, true);
    
    ArrayList<Comment> commentList = new ArrayList<Comment>();
    commentList = C.getComment(tempID);
    
%>

<title>Simple Blog | </title>


</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time"> <% out.println(P.getDate());  %> </time>
            <h2 class="art-title"> <% out.println(P.getTitle());  %></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
            
	    <% out.println(P.getContent()); %>

            <hr />
            
            <h2>Komentar</h2>

            <div id="contact-area">
                <!--form name="frm" method="post" onSubmit=" return SubmitComment(<%//out.println(P.getPostID()); %>); return false;"-->
		<!--?post_ID=" + post_ID + "&author=" + author + "&email=" + email + "&comment=" + comment-->
                <form name="frm" method="post" action="Comment.jsp">
                    <label for="Nama">Nama:</label>
                    <input type="hidden" name="Userid" id="Userid" value="<%= P.getPostID() %>">
		    <input type="text" name="Nama" id="Nama">
        
                    <label for="Email">Email:</label>
                    <input type="text" name="Email" id="Email">
                    
                    <label for="Komentar">Komentar:</label><br>
                    <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>

                    <input type="submit" name="submit" value="Kirim" class="submit-button" onclick="checkValid(frm.Email));">
                </form>
            </div>

	    <div id = "yangmaudiajaks">
            <ul class="art-list-body">
		
		<% 
		if (commentList == null){
		    out.println("<li class=\"art-list-item\"><div class=\"art-list-item-title-and-time\"><h2 class=\"art-list-title\">No comments</h2> </div></li>");
		}
		else {
		    for (Comment comm : commentList){
			out.println("<li class=\"art-list-item\">");
			out.println("<div class=\"art-list-item-title-and-time\">");
			out.println("<h2 class=\"art-list-title\">");
			out.println("<a href \"mailto: " + comm.getEmail() + "\">");
			if (comm.getUserID() != 0)
			    out.println(C.getUsernameByID (comm.getUserID()) );
			else 
			    out.println("Guest");
			out.println("</a></h2>");

			out.println("<div class=\"art-list-time\">" + comm.getDate() + "</div></div>");
			out.println("<p>" + comm.getContent());
			out.println("</li>");
		    }
		}
		%>    
            </ul>		
	    </div>

        </div>
    </div>

</article>

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

<script type="text/javascript" src="./assets/js/comment.js"></script>
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

</body>
</html>