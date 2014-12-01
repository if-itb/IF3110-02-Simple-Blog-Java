<%-- 
    Document   : edit_post
    Created on : Nov 24, 2014, 2:10:26 PM
    Author     : tegar
--%>


<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
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

<title>Simple Blog | Edit Post</title>


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
    
    
    <h2 class="art-title" style="margin-bottom:40px">-</h2>

    <div class="art-body">
        <div class="art-body-inner">
            <h2>Edit Post</h2>

            <div id="contact-area">
                <form name="PostForm" method="post" onSubmit="return validateDate()" action="EditPostHandling">
                    <label for="Judul">Judul:</label>
                    <%
                            Connection con = null;
                            try 
                            {
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
                            String sql="SELECT * FROM post WHERE post_id="+request.getParameter("post_id");
                            System.out.println(sql);
                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery(sql);    
                            while (rs.next())
                            {
                                out.println("<input type=\"text\" name=\"Judul\" id=\"Judul\" value=\""+rs.getString("judul")+"\">");
                                out.println("<label for=\"Tanggal\">Tanggal:</label>");                                
                                out.println("<input type=\"date\" name=\"Tanggal\" id=\"Tanggal\" value="+rs.getDate("tanggal")+">");
                                out.println("<label for=\"Konten\">Konten:</label><br>");
                                out.println("<input type=hidden name=Post_Id value=\""+request.getParameter("post_id")+"\">");
                                out.println("<input type=hidden name=Role value=\""+request.getParameter("role")+"\">");
                                out.println("<textarea name=\"Konten\" rows=\"20\" cols=\"20\" id=\"Konten\">"+rs.getString("konten")+"</textarea>");                                
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
                    <input type="submit" name="submit" value="Simpan" class="submit-button">
                </form>
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

<script type="text/javascript" src="assets/js/fittext.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>

<!-- Date Validation using JS -->
<script>
    function validateDate()
    {
        var inputdate = document.forms["PostForm"]["Tanggal"].value;
        var currentdate = new Date();
        var nowdd = currentdate.getDate();
        var nowmm = currentdate.getMonth()+1;
        var nowyyyy = currentdate.getFullYear();
        var partdate = inputdate.split('-');
        var inputyyyy = partdate[0];
        var inputmm = partdate[1];
        var inputdd = partdate[2];
        if (nowyyyy<inputyyyy)
        {
            return true;
        }        
        else if (nowyyyy>inputyyyy)
        {
            alert("Tanggal tidak valid");
            return false;
        }
        else
        {
            if (nowmm<inputmm)
            {
                return true;
            }
            else if (nowmm>inputmm)
            {
                alert("Tanggal tidak valid");
                return false;
            }
            else
            {
                if (nowdd<=inputdd)
                {
                    return true;
                }
                else  if (nowdd>inputdd)
                {
                    alert("Tanggal tidak valid");
                    return false;
                }
            }
        }
    }  
</script>


</body>
</html>