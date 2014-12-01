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

<jsp:include page ="/publish_post"/>

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
	function publishPost(pubID)
	{
		var confDialog = confirm("Are you sure want to publish this post?");
		if (confDialog)
		{
			window.location = 'publish_post?publish=' + pubID; 
		}
	}
</script>

</head>

<body class="default">
<div class="wrapper">
    
    
<div style="text-align: right; z-index: 9 ; margin-right: 20px; margin-top:10px">
     
     <%
        if (request.getParameter("invalid") != null)
        {
            if (request.getParameter("invalid").equals("yes"))
            {
                out.println("<br> Invalid username or password.");
            }
        }
        else
        {
            out.println("");
        }
        
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
                    <a href ="#">Publish Post</a>
                    <br>
                    <a href ="deleted_post.jsp">Deleted Post</a>
                    <%
            }
            else if (statusUser.equals("O"))
            {
                out.println("You are not Admin or Editor");
            }
            else if (statusUser.equals("E"))
            {
                out.println("Editor<br>");
            }
        }
     %>
            
        </span>
    </form>
</div>

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <%
        if (statusUser.equals("A") || statusUser.equals("O"))
        {
           %> 
           <ul class="nav-primary">
                <li><a href="new_post.jsp">+ Tambah Post</a></li>
           </ul>
           <%
        }
    %>
    
</nav>

<br>
    <%if (request.getParameter("edit")!= null)
    {
       if (request.getParameter("edit").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Edit Success</span><%
       }
    }%>
    
    <%if (request.getParameter("create")!= null)
    {
       if (request.getParameter("create").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Create Success</span><%
       }
    }%>
    <%if (request.getParameter("delete")!= null)
    {
       if (request.getParameter("delete").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Delete Success</span><%
       }
    }%>
    
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
                                <h2 class="art-list-title"><%out.println(record[1]);%></h2>
                                <div class="art-list-time"><%out.println(record[2]);%></div>
                            </div>
                                <p> <%out.println(record[3]); %> </p>
                                <p>
                                    <a href="edit_post.jsp?id=<%out.println(record[0]);%>">Edit</a> | <a href="javascript:void(0);" onclick ="publishPost(<%out.println(record[0]);%>);">Publish</a>
                                </p>
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