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


</head>

<body class="default">
<div class="wrapper">

<div style="text-align: right; z-index: 9 ; margin-right: 20px; margin-top:10px">
    <%
        if (foundCookie)
        {
            if (statusUser.equals("A"))
            {
                out.println("User: ");
                out.println(loginUser);
                out.println("<br>Role: ");
                out.println("Admin");
            }
            else
            {
                out.println("You are not admin!");
            }
        }
    %>    
</div>
    
<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
</nav>

<article class="art simple post">
    <div class="art-body">
        <div class="art-body-inner">
            <div id="contact-area">
                <form method="post" action="edit_user?Username=<%
                            out.print(request.getParameter("user"));
                        %>">
                    <label>Username:</label>
                    <span class="label label-default"> 
                        <%
                            out.print(request.getParameter("user"));
                        %>
                    </span>
                    
                    <br> <br>
                    <label>Status:</label>
                    <input type="text" name="Status" id="Status" value="<%
                                String user_status = request.getParameter("status");
                                if (user_status.equals("A"))
                                    out.println("Admin");
                                else if (user_status.equals("O"))
                                    out.println("Owner");
                                else if (user_status.equals("E"))
                                    out.println("Editor");%>" placeholder="Status">

                    <input type="submit" name="submit" value="Edit" class="submit-button">
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

</body>
</html>