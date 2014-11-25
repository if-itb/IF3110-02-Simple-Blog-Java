<%-- 
    Document   : showunpublishedposts
    Created on : Nov 24, 2014, 2:10:26 PM
    Author     : tegar
--%>




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

<title>Simple Blog</title>


</head>

<body class="default">


<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="login.jsp">+ Login</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
            <!--Showing post from database-->
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
                    String sql=("SELECT * FROM post WHERE status_publish=0");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery(sql);    
                    while (rs.next())
                    {
                        String Publish = new String("Unpublished");
                        out.println(" <li class=\"art-list-item\"> <div class=\"art-list-item-title-and-time\"> <h2 class=\"art-list-title\"><a href=\"post.jsp"
                                + "?"
                                + "role="
                                + session.getAttribute("role")
                                + "&"
                                + "post_id="
                                + rs.getString("post_id")
                                + "\">"
                                + rs.getString("judul")+ ""
                                + "</a></h2> "
                                + "<div class=\"art-list-time\">15 Juli 2014</div> <div class=\"art-list-time\"><span style=\"color:#F40034;\">"
                                + "&#10029;</span> "
                                + Publish
                                + "</div> "
                                + "</div> <p>");
                        String[] words = rs.getString("konten").split(" ");
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < Math.min(30, words.length); i++)
                        {
                            sb.append(words[i] + " ");  
                        }   
                        String First30WordPost = sb.toString();
                        out.println(First30WordPost);
                        out.println(" <p> <a href=\""
                            + "edit_post.jsp??role=2&"
                            + "post_id="
                            + rs.getString("post_id")
                            + "&"
                            + "user_id="
                            + request.getParameter("user_id")
                            + "\">Edit</a> | <a onclick=\"validatedelete(" + rs.getString("post_id") + ")\" href=\"javascript:void(0)\">Hapus</a> | <a href=\"PublishPostHandling?"+"Role="+session.getAttribute("role")+"&Post_Id="+rs.getString("post_id")+"\">Publish</a> </p> </li>");
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

<!-- Vaildate Delete -->
<script>
  function validatedelete()
  {
      console.log("validatedelete");
      var x;
      if (confirm("Apakah Anda yakin menghapus post ini?") === true) {
          
      } else {
        x = "Cancel";
      }
  } 
 </script>
</body>
</html>