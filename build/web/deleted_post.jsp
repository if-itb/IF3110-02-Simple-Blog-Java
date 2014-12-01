<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.List"%>
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

<jsp:include page ="/deleted_post"/>

<%  
    Cookie[] cookies = request.getCookies();
    String loginUser = "";
    String statusUser ="";
    boolean foundCookie = false;
    
    for (int i = 0;i<cookies.length;i++)
    {
        Cookie c = cookies[i];
        if (c.getName().equals("username"))
        {
            loginUser = c.getValue();
            foundCookie = true;
        }
        if (c.getName().equals("status"))
        {
            statusUser = c.getValue();
            foundCookie = true;
        }
    }
%>

<title>Simple Blog</title>

<script>
	function restorePost(delID)
	{
		var confDialog = confirm("Are you sure want to restore this post?");
		if (confDialog)
		{
			window.location = 'deleted_post?restore=' + delID; 
		}
	}
</script>

</head>

<body class="default">
<div class="wrapper">
    
<div style="text-align: right; z-index: 9 ; margin-right: 20px; margin-top:10px">
    
     
     <%
        if (foundCookie)
        {
            out.println("User: ");
            out.println(loginUser);
            out.println("<br>Role: ");
            if (statusUser.equals("A"))
            {
                out.println("Admin<br>");
                
                    %>
                    <a href ="manajemen_user.jsp">Manajemen User</a>
                    <br>
                    <a href ="publish_post.jsp">Publish Post</a>
                    <br>
                    <a href ="#">Deleted Post</a>
                    <%
            }
            else 
            {
                out.println("You are not admin!");
            }
        }
     %>
            
        </span>
    </form>
</div>

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <%
        if (statusUser.equals("A"))
        {
           %> 
           <ul class="nav-primary">
                <li><a href="new_post.jsp">+ Tambah Post</a></li>
           </ul>
           <%
        }
    %>
    
</nav>
    
<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
            <%
                List<String[]> postData = (List<String[]>)request.getAttribute("queryPost");
                for(String[] record : postData)
                {
                    %>
                        <li class="art-list-item">
                            <div class="art-list-item-title-and-time">
                                <h2 class="art-list-title"><a href="post.jsp?id=<%out.println(record[0]);%>"> <%out.println(record[1]);%></a></h2>
                                <div class="art-list-time"><%out.println(record[2]);%></div>
                            </div>
                                <p> <%out.println(record[3]); %> </p>
                                <%if (foundCookie) {%>
                                <p>
                                    <a href="javascript:void(0);" onclick ="restorePost(<%out.println(record[0]);%>);">Restore</a>
                                </p>
                                <%}%>
                        </li>
                    <%;
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

</body>
</html>