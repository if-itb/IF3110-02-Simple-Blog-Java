<!DOCTYPE html>
<html>
<head>
<?php setlocale(LC_ALL, 'INDONESIA'); ?>
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
    <a style="border:none;" id="logo" href="index.php"><h1>Kevhn's<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<div id="home">
    <div class="posts">
        <nav class="art-list">
          <ul class="art-list-body">
          	<form name="post" method="post">
          		<input type="hidden" name="id" value="">
          	</form>
          	<?php 
          		//establish connection to database
          		$con = mysqli_connect('localhost', 'kevhnmay94', "", 'simpleblog');
				
				//retrieve posts from database
				$results = mysqli_query($con, "SELECT * FROM `posts` ORDER BY `date` DESC");
				
				
				//print them to the screen
				foreach ($results as $result) {
					
				//reverse date format
				$retrieved = $result['date'];
				$date = DateTime::createFromFormat('Y-m-d', $retrieved);
				echo"
				<li class=\"art-list-item\">
	                <div class=\"art-list-item-title-and-time\">
	                    <h2 class=\"art-list-title\"><a href=\"post.php?id=".$result['id']."\">" . $result['title'] . "</a></h2>
	                    <div class=\"art-list-time\">" . $date->format('l\, j F Y') . "</div>
	                </div>
	                <p>" . nl2br($result['content']) . "</p>
	                <p class=\"editable\" id=\"" . $result['id'] . "\">
	                  <a href=\"editPost.php?id=".$result['id']."\">Edit</a> | <a onclick=\"hapus(this.id)\" id=\"d" . $result['id'] . "\">Hapus</a>
	                </p>
           		</li>";
				}
				
				//close connection to database
				mysqli_close($con);
          	?>
          	
          	
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
<script type="text/javascript" src="assets/js/post.js"></script>
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
