<%-- 
    Document   : post
    Created on : Nov 26, 2014, 2:10:55 PM
    Author     : KBK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<title>Simple Blog | Apa itu Simple Blog?</title>


</head>





<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.php"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.php">+ Tambah Post</a></li>
    </ul>
</nav>

<?php while($row=mysql_fetch_array($result)): ?>
	<article class="art simple post">
		
		<header class="art-header">
			<div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
				<time class="art-time"><?php echo date('d F Y', strtotime($row['tanggal'])); ?></time>
				<h2 class="art-title"><?php echo $row['judul']; ?></h2>
				<p class="art-subtitle"></p>
			</div>
		</header>

		<div class="art-body">
                    <div class="art-body-inner">

                        <p><?php echo $row['konten']; ?></p>

                        <hr />

                        <h2>Komentar</h2>

                        <div id="contact-area">
                                <form method="post" action="#">
                                        <label for="nama">Nama:</label>
                                        <input type="text" name="nama" id="nama">

                                        <label for="email">Email:</label>
                                        <input type="text" name="email" id="email">

                                        <label for="komentar">Komentar:</label><br>
                                        <textarea name="komentar" rows="20" cols="20" id="komentar"></textarea>

                                        <input type="submit" name="submit" value="Kirim" class="submit-button">
                                </form>
                        </div>	
                    </div>
		</div>

	</article>
<?php endwhile; ?>
	
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


<script type="text/javascript">
	(function() {
		var httpRequest;
		document.getElementById("loadKomentarButton").onclick = function(){
			makeRequest('loadcomments.php');
		};
		
		function makeRequest(url) {
			if(window.XMLHttpRequest){
				httpRequest = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				try {
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch(e) {
					try {
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					}
					catch(e){
						
					}
				}
			}
		
		
			if (!httpRequest){
				alert("Tidak dapat membuat XMLHTTP instance");
				return false;
			}
			
			httpRequest.onreadystatechange = loadContents;
			httpRequest.open('GET', url);
			httpRequest.send();
		
		}
		
		function loadContents() {
			if (httpRequest.readyState === 4) {
				if (httpRequest.status === 200) {
					alert(httpRequest.responseText);
				} else {
					alert('Ada masalah dengan request');
				}
			}
		}
		
	})();
</script>

</body>
</html>
