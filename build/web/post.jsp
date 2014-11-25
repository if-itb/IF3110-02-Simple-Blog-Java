<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.Connection"%>
<%@ page language="java" import="java.sql.PreparedStatement"%>
<%@ page language="java" import="java.sql.ResultSet"%>
<%@ page language="java" import="java.sql.SQLException"%>
<%@ page language="java" import="java.sql.DriverManager"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.String"%>

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
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>
    <% 
                    Connection con2 = null;
                    try {
                        //Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/simpleblog_withjava";
                        String user = "root";
                        String password = "";

                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                        con2 = DriverManager.getConnection(url, user, password);
                    }
                    catch(SQLException ex){
                    System.out.println(ex);
                    }

                    // Connection con;
                    // con=DBConnect.GetDBConnect();
		try 
		{
                    String sql=("SELECT * FROM post WHERE post_id="+request.getParameter("post_id"));
                    PreparedStatement ps = con2.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next())
                    {
                        out.println(rs.getString("judul"));
                    }
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
            %>
</title>


</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.jsp">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time">15 Juli 2014</time>
            <br>
            <h2 class="art-title">
                <% 
                    Connection con1 = null;
                    try {
                        //Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/simpleblog_withjava";
                        String user = "root";
                        String password = "";

                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                        con1 = DriverManager.getConnection(url, user, password);
                    }
                    catch(SQLException ex){
                    System.out.println(ex);
                    }

                    // Connection con;
                    // con=DBConnect.GetDBConnect();
		try 
		{
                    String sql=("SELECT * FROM post WHERE post_id="+request.getParameter("post_id"));
                    PreparedStatement ps = con1.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next())
                    {
                        out.println(rs.getString("judul"));
                    }
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
            %>
            </h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
                <% 
                    Connection con = null;
                    try {
                        //Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/simpleblog_withjava";
                        String user = "root";
                        String password = "";

                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

                        con = DriverManager.getConnection(url, user, password);
                    }
                    catch(SQLException ex){
                    System.out.println(ex);
                    }

                    // Connection con;
                    // con=DBConnect.GetDBConnect();
		try 
		{
                    String sql=("SELECT * FROM post WHERE post_id="+request.getParameter("post_id"));
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next())
                    {
                        out.println("<p>"
                        + rs.getString("konten")
                        + "</p>");
                    }
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
            %>
            <hr />
            
            <h2>Komentar</h2>

            <div id="contact-area">
                <form method="post" action="AddCommentHandling">
                    <input type="hidden" name="user_id" value="<%out.print(request.getParameter("user_id"));%>">
                    <input type="hidden" name="post_id" value="<%out.print(request.getParameter("post_id"));%>">
                    
                <% 
		try 
		{
                    String sql="SELECT * FROM user WHERE user_id="+request.getParameter("user_id");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next())
                    {
                        out.println("<label for=\"Nama\">Nama:</label>");
                        out.println("<input type=\"text\" name=\"Nama\" id=\"Nama\" value=\""
                                + rs.getString("nama")
                                + "\">");        
                        out.println("<label for=\"Email\">Email:</label>");
                        out.println("<input type=\"text\" name=\"Email\" id=\"Email\" onchange=\"validateEmail()\" value=\""
                                + rs.getString("email")
                                + "\">");                    
                    }
		}
		catch (SQLException ex)
		{
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
                                    
            %>
                    <label for="Komentar">Komentar:</label><br>
                    <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>

                    <input type="submit" name="submit" value="Kirim" class="submit-button">
                </form>
            </div>
                    
            <div id="komen">
                <ul class="art-list-body">
                    <%         
                        try 
                        {
                            String sql=("SELECT * FROM komentar WHERE post_id="
                                    + "\'"
                                    +request.getParameter("post_id") 
                                    + "\'"
                                    +" ORDER BY komentar_tanggal DESC");
                            /*String sql=("SELECT * FROM post WHERE post_id="+request.getParameter("post_id"));*/
                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery(sql);    
                            while (rs.next())
                            {
                    
                                out.println("<li class='art-list-item' id='komen'>");
                                    out.println("<div class='art-list-item-title-and-time'>");
                                        out.println("<h2 class='art-list-title'> <a href='#'>");
                                            out.println(rs.getString("komentar_nama"));                                                
                                        out.println("</a> </h2>");
                                        out.println("<div class='art-list-time'>");
                                            out.println(rs.getString("komentar_tanggal"));
                                        out.println("</div>");
                                    out.println("</div>");
                                    out.println("<p>");
                                        out.println(rs.getString("komentar_konten"));
                                    out.println("</p>");
                                out.println("</li>");
                            }
                        }
                        catch (SQLException ex)
                        {
                            // handle any errors
                            System.out.println("SQLException: " + ex.getMessage());
                            System.out.println("SQLState: " + ex.getSQLState());
                            System.out.println("VendorError: " + ex.getErrorCode());
                        }
                    %>
                </ul>
            </div>

            <!--<ul class="art-list-body">
                <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                        <h2 class="art-list-title"><a href="post.jsp">Jems</a></h2>
                        <div class="art-list-time">2 menit lalu</div>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis repudiandae quae natus quos alias eos repellendus a obcaecati cupiditate similique quibusdam, atque omnis illum, minus ex dolorem facilis tempora deserunt! &hellip;</p>
                </li>

                <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                        <h2 class="art-list-title"><a href="post.jsp">Kave</a></h2>
                        <div class="art-list-time">1 jam lalu</div>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis repudiandae quae natus quos alias eos repellendus a obcaecati cupiditate similique quibusdam, atque omnis illum, minus ex dolorem facilis tempora deserunt! &hellip;</p>
                </li>
            </ul> -->
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

<!-- Vaildate Email -->
<script>
function validateEmail(){ 
	console.log("validate email"); 
	var email = document.getElementById('Email').value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
	if (email.match(mailformat)){  
		document.getElementById('Komentar').disabled = false;  
	}  
	else{  
		alert("You have entered an invalid email address!");  
		document.getElementById('Komentar').disabled = true;  
	}  
}  
</script>

<script>
    function loadkomentar(){
	var xmlhttp;
	console.log("load komentar");

	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  	xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	   		document.getElementById("komen").innerHTML=xmlhttp.responseText;

	    }
	  }
	xmlhttp.open("POST","AddCommentHandling",true);
		console.log("oper komentar ke servlet");
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var post_id=document.getElementById('post_id').value; 
	var Nama=document.getElementById('Nama').value;	
	var Email=document.getElementById('Email').value;
	var Komentar=document.getElementById('Komentar').value;
		console.log(oper value variabel);
	xmlhttp.send("post_id="+post_id+"&Nama="+Nama+"&Email="+Email+"&Komentar="+Komentar+"");
          return false;

}
</script>

</body>
</html>