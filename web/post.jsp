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


<jsp:include page ="/post"/>

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

<title>Simple Blog | Apa itu Simple Blog?</title>

<script>
    function updateComment(cookieExist)
    {
            if (!cookieExist)
            {
                if (!validateEmail())
                {
                        alert("Email yang anda masukkan tidak valid!");
                        return false;
                }
            }   
            var id, name, email, comment;
            id = <% out.println(request.getParameter("id"));%>
            
            comment = document.getElementById("Komentar").value;
            name = document.getElementById("Nama").value;
            email = document.getElementById("Email").value;
            
            
            var xmlhttp;

            if (window.XMLHttpRequest)
            {
                    xmlhttp = new XMLHttpRequest();
            }

            xmlhttp.onreadystatechange = function()
            {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {	
                            document.getElementById("Comments").innerHTML=xmlhttp.responseText;
                    }
            }

            xmlhttp.open("POST","komentar",true);

            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            var sentString = ("ID="+id+"&Name="+name+"&Email="+email+"&Comment="+comment);

            xmlhttp.send(sentString);
            return false;
    }   

    function loadComment()
    {
            var xmlhttp;

            if (window.XMLHttpRequest)
            {
                    xmlhttp = new XMLHttpRequest();
            }

            xmlhttp.onreadystatechange = function()
            {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {	
                            document.getElementById("Comments").innerHTML=xmlhttp.responseText;
                    }
            }
            
            xmlhttp.open("GET","komentar?id="+ <%out.println(request.getParameter("id"));%>,true);
            xmlhttp.send();
    }	

    function validateEmail()
    {
            var email = document.getElementById("Email").value;
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
    }
	
</script>

</head>

<body class="default" onload = "loadComment();">
<div class="wrapper">
    <div style="text-align: right; z-index: 9 ; margin-right: 20px; margin-top:10px;">
        <%
            String[] data_Post = (String[]) request.getAttribute("queryPost");
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
                    <a href ="deleted_post.jsp">Deleted Post</a>
                    <%
                }
                else if (statusUser.equals("O"))
                {
                    out.println("Owner");
                }
                else if (statusUser.equals("E"))
                {
                    out.println("Editor");
                }
            }
        %>
    </div>
<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.jsp">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time"><%out.println(data_Post[2]);%></time>
            <h2 class="art-title"><%out.println(data_Post[1]);%></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
            <p><%out.println(data_Post[3].replaceAll("\n","<br>"));%></p>
            <hr />
            
            <h2>Komentar</h2>
                
            <div id="contact-area">
                <form method="post" onsubmit="return updateComment(<%out.println(foundCookie);%>);">
                    <%
                        if (!foundCookie)
                        {
                    %>
                    
                    <label for="Nama">Nama:</label>
                    <input type="text" name="Nama" id="Nama">
                    
                    <label for="Email">Email:</label>
                    <input type="text" name="Email" id="Email">
                    <%}else{%>
                    <input type="hidden" name="Nama" value="<% out.println(loginUser);%>" id="Nama">
                    <input type="hidden" name="Email" value="<%out.println(loginUser+"yahoo.com");%>" id="Email">
                    
                    <%}%>
                    <label for="Komentar">Komentar:</label>
                    <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>

                    <input type="submit" name="submit" value="Kirim" class="submit-button">
                </form>
            </div>
            
            <div id = "Comments"> </div>
            
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