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

<title>Simple Blog | Tambah Post</title>

<script>
	function validasiTanggal()
	{
		var date;
		
		date = document.getElementById("Tanggal").value;
		
		if ((new Date(date).getTime()+ 24*60*60*1000) > new Date().getTime() )
		{
			return true;
		}
		else
		{
			alert("Tanggal yang anda masukkan tidak valid!");
			return false;
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
            if (statusUser.equals("O") || statusUser.equals("A"))
            {
                out.println("User: ");
                out.println(loginUser);
                out.println("<br>Role: ");
                if (statusUser.equals("A"))
                {
                    out.println("Admin");
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
        }
    %>
</div>
    
<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="#">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">

    <div class="art-body">
        <div class="art-body-inner">
            <h2>Tambah Post</h2>
            <div id="contact-area">
                <form method="post" onsubmit="return validasiTanggal();" action="new_post">
                    <label for="Judul">Judul:</label>
                    <input type="text" name="Judul" id="Judul" placeholder="Judul">

                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="Tanggal" id="Tanggal" placeholder="yyyy-mm-dd">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="Konten" rows="20" cols="20" id="Konten" placeholder="Isi Artikel"></textarea>

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

</body>
</html>