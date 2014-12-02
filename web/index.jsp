<!DOCTYPE html>

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
        <li><a href="login.jsp">Login</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">	
            <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id=INSERT ID HERE">INSERT JUDUL HERE</a></h2>
                            <div class="art-list-time">INSERT DATE HERE</div>
                    </div>
                    <p>Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet</p>
                    <p>	<a href="edit.php?id=<?php echo $row['id'] ?>">Edit</a> </p>
                    <p>
                            <form method="post" action="delete">
                                    <input type="hidden" id="id" name="id" value="INSERT ID BLOG HERE">
                                    <input type="submit" class="a" value="Hapus" onclick="return confirm('Apakah Anda akan menghapus entri blog ini?')">
                            </form>
                    </p>
            </li>
            <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id=<?php echo $row['id'] ?>">INSERT JUDUL HERE</a></h2>
                            <div class="art-list-time">INSERT DATE HERE</div>
                    </div>
                    <p>Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet</p>
                    <p>	<a href="edit.php?id=<?php echo $row['id'] ?>">Edit</a> </p>
                    <p>
                            <form method="post" action="delete.php">
                                    <input type="hidden" id="id" name="id" value="INSERT ID BLOG HERE">
                                    <input type="submit" class="a" value="Hapus" onclick="return confirm('Apakah Anda akan menghapus entri blog ini?')">
                            </form>
                    </p>
            </li>
			

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
