<?php 

	//create connection to database
	$link = mysqli_connect('localhost', 'kevhnmay94', "", 'simpleblog');
	$id = mysqli_escape_string($link, $_GET['id']);
	
	//Replace all '<br>' with '\n'
	/* $query = "UPDATE `posts` SET `content`= REPLACE (`konten`, '<br>', '\n')";
	if(!mysqli_query($link, $query))
	{
		die('Error : ' . mysqli_errno($link));
	} */
	
	//retrieve data from database
	$query = "SELECT * FROM `posts` WHERE `id` = $id";
	if(!($results = mysqli_query($link, $query)))
	{
		die('Error ' . mysqli_errno($link));
	}
	$results = $results->fetch_assoc();

	//reverse date format
	$retrieved = $results['date'];
	$date = DateTime::createFromFormat('Y-m-d', $retrieved);

	echo"
	<!DOCTYPE html>
	<html>
	<head>
	
	<meta charset=\"utf-8\">
	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">
	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">
	<meta name=\"description\" content=\"Deskripsi Blog\">
	<meta name=\"author\" content=\"Judul Blog\">
	
	<!-- Twitter Card -->
	<meta name=\"twitter:card\" content=\"summary\">
	<meta name=\"twitter:site\" content=\"omfgitsasalmon\">
	<meta name=\"twitter:title\" content=\"Simple Blog\">
	<meta name=\"twitter:description\" content=\"Deskripsi Blog\">
	<meta name=\"twitter:creator\" content=\"Simple Blog\">
	<meta name=\"twitter:image:src\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">
	
	<meta property=\"og:type\" content=\"article\">
	<meta property=\"og:title\" content=\"Simple Blog\">
	<meta property=\"og:description\" content=\"Deskripsi Blog\">
	<meta property=\"og:image\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">
	<meta property=\"og:site_name\" content=\"Simple Blog\">
	
	<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/screen.css\" />
	<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"img/favicon.ico\">
	
	<!--[if lt IE 9]>
	    <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>
	<![endif]-->
	
	<title>Simple Blog | Edit Post</title>
	
	
	</head>
	
	<body class=\"default\">
	<div class=\"wrapper\">
	
	<nav class=\"nav\">
    <a style=\"border:none;\" id=\"logo\" href=\"index.php\"><h1>Kevhn's<span>-</span>Blog</h1></a>
	</nav>
	
	
	<article class=\"art simple post\">
	    
	    
	    <h2 class=\"art-title\" style=\"margin-bottom:40px\">-</h2>
	
	    <div class=\"art-body\">
	        <div class=\"art-body-inner\">
	            <h2 style=\"text-align:center\">Edit Post</h2>
	
	            <div id=\"contact-area\">
	                <form method=\"post\" action=\"insert_database.php\" onsubmit=\"return Validation()\">
	                
	                	<input type=\"hidden\" value=".$id." name=\"id\">
	                
	                    <label for=\"Judul\">Judul:</label>
	                    <input type=\"text\" name=\"Judul\" id=\"Judul\" required=\"\" value=\"" . $results['title'] ."\">
	
	                    <label for=\"Tanggal\">Tanggal:</label>
	                    <input type=\"text\" name=\"Tanggal\" value=\"" . $date->format('d-m-Y') . "\" id=\"Tanggal\" required=\"\" placeholder=\"format: dd-mm-yyyy\">
	                    <div id=\"errormsg\"></div>
	                    
	                    <label for=\"Konten\"><br>Konten:</label><br>
	                    <textarea name=\"Konten\" rows=\"40\" cols=\"20\" id=\"Konten\" required>".$results['content']."</textarea>
	
	                    <input type=\"submit\" name=\"submit\" value=\"Simpan\" class=\"submit-button\">
	                </form>
	            </div>
	        </div>
	    </div>
	
	</article>
	
	<footer class=\"footer\">
	    <div class=\"back-to-top\"><a href=\"\">Back to top</a></div>
	    <!-- <div class=\"footer-nav\"><p></p></div> -->
	    <div class=\"psi\">&Psi;</div>
	    <aside class=\"offsite-links\">
	        Asisten IF3110 /
	        <a class=\"rss-link\" href=\"#rss\">RSS</a> /
	        <br>
	        <a class=\"twitter-link\" href=\"http://twitter.com/YoGiiSinaga\">Yogi</a> /
	        <a class=\"twitter-link\" href=\"http://twitter.com/sonnylazuardi\">Sonny</a> /
	        <a class=\"twitter-link\" href=\"http://twitter.com/fathanpranaya\">Fathan</a> /
	        <br>
	        <a class=\"twitter-link\" href=\"#\">Renusa</a> /
	        <a class=\"twitter-link\" href=\"#\">Kelvin</a> /
	        <a class=\"twitter-link\" href=\"#\">Yanuar</a> /
	        
	    </aside>
	</footer>
	
	</div>
	
	<script type=\"text/javascript\" src=\"assets/js/fittext.js\"></script>
	<script type=\"text/javascript\" src=\"assets/js/app.js\"></script>
	<script type=\"text/javascript\" src=\"assets/js/respond.min.js\"></script>
	<script type=\"text/javascript\" src=\"assets/js/formValidation.js\"></script>
	<script type=\"text/javascript\">
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
	";

	mysqli_close($link);
?>
