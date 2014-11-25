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

<?php 
	//establish connection to database
	$link = mysqli_connect('localhost', 'kevhnmay94', "", 'simpleblog');
	$id = mysqli_real_escape_string($link, $_GET['id']);
	
	//retrieve data from database (info_post)
	$query = "SELECT * FROM `posts` WHERE id = $id";
	if(!($results = mysqli_query($link, $query)))
	{
		die('Error ' . mysqli_errno($link));
	}
	$result = $results->fetch_assoc();
	$retrieved = $result['date'];
	$date = DateTime::createFromFormat('Y-m-d', $retrieved);
	
	//retrieve data from database (info_comment)
	$query1 = "SELECT * FROM `comments` WHERE pid = $id";
	if(!($results1 = mysqli_query($link, $query1)))
	{
		die('Error ' . mysqli_errno($link));
	}
?>

<title>Simple Blog | <?php echo $result['title']; ?></title>


</head>

<body class="default" onload="loadpost(<?php echo $result['id'] ?>)">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.php"><h1>Kevhn's<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time"><?php echo $date->format('l\, j F Y'); ?></time>
            <h2 class="art-title"><?php echo $result['title']; ?></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>

    <div class="art-body">
        <div class="art-body-inner" style="padding-top: 0px;">
            <p><?php echo nl2br($result['content']); ?></p>

            
            <h2>Komentar</h2>

            <div id="contact-area">
                <form method="post" action="addcomment.php" name="form_comment" id="form_comment" onsubmit="return false">
					<input type="hidden" name="pid" value=<?php echo $id; ?> id="pid">
				
                    <label for="nama">Nama:</label>
                    <input type="text" name="nama" id="nama" required>
        
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email" required>
                    <div id='errormsg'></div>
                    
                    <label for="komentar"><br>Komentar:</label><br>
                    <textarea name="komentar" rows="20" cols="20" id="komentar" required></textarea>

                    <input type="submit" name="submit" value="Kirim" class="submit-button" onclick="validatecomment()">
                </form>
            </div>
			<ul class="art-list-body">
				
				<div id="ajaxcontent">
				
				</div>
				
            </ul>
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

<?php 
	//close connection to server
	mysqli_close($link);
?>

<script type="text/javascript" src="assets/js/fittext.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>
<script type="text/javascript" src="assets/js/getdatabase.js"></script>
<script type="text/javascript">
  var ga_ua = '{{! TODO: ADD GOOGLE ANALYTICS UA HERE }}';

  (function(g,h,o,s,t,z){g.GoogleAnalyticsObject=s;g[s]||(g[s]=
      function(){(g[s].q=g[s].q||[]).push(arguments)});g[s].s=+new Date;
      t=h.createElement(o);z=h.getElementsByTagName(o)[0];
      t.src='//www.google-analytics.com/analytics.js';
      z.parentNode.insertBefore(t,z)}(window,document,'script','ga'));
      ga('create',ga_ua);ga('send','pageview');
</script>

</body>
</html>