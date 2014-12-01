<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

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

<title>Simple Blog | Tambah Post</title>


</head>

<body class="default">
<div class="wrapper">

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">
    
    
    <h2 class="art-title" style="margin-bottom:40px">-</h2>

    <div class="art-body">
        <div class="art-body-inner">
            <h2>Tambah Post</h2>

            <div id="contact-area">
                <form method="post" action="insert.jsp" name="formPost">
                    <label for="Judul">Judul:</label>
                    <input type="text" name="Judul" id="Judul">

                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="Tanggal" id="Tanggal">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="Konten" rows="20" cols="20" id="Konten"></textarea>

                    <input type="submit" name="submit" value="Simpan" class="submit-button" onclick="return validasiTanggal(document.formPost.Tanggal)">
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

<script language="javascript" type="text/javascript">
function cek(inputText) {
}
function validasiTanggal(inputText) {
  var formatTanggal = /^(0?[1-9]|[12][0-9]|3[01])[\-](0?[1-9]|1[012])[\-]\d{4}$/;
  if(inputText.value.match(formatTanggal)) {
    document.formPost.Tanggal.focus();
    
    var dSplit = inputText.value.split('-');
    var dd = parseInt(dSplit[0]);
    var mm = parseInt(dSplit[1]);
    var yy = parseInt(dSplit[2]);
  
    var jumHari = [31,28,31,30,31,30,31,31,30,31,30,31];
    if(mm==1 || mm>2) {
      if(dd>jumHari[mm-1]) {
        alert('Format tanggal salah! dd-mm-yyyy');
        document.formPost.Tanggal.focus();
        return false;
      }
    } 
    if (mm == 2) {
        var yyKabisat = false;
        if (((yy%4==0) || (yy%100==0)) && !(yy%400==0)) {
            yyKabisat = true;
        }
        if ((yyKabisat==false) && (dd>=29)) {
            alert('Format tanggal salah! dd-mm-yyyy');
            document.formPost.Tanggal.focus();
            return false;
        }
        if ((yyKabisat==true) && (dd>29)) {
            alert('Format tanggal salah! dd-mm-yyyy');
            document.formPost.Tanggal.focus();
            return false;
        }
    }
  //periksa apakah input lebih besar atau sama
    var dSplit = inputText.value.split('-');
    var dd = parseInt(dSplit[0]);
    var mm = parseInt(dSplit[1]);
    var yy = parseInt(dSplit[2]);

    var today = new Date();
    var ddT = today.getDate();
    var mmT = today.getMonth() + 1;
    var yyT = today.getFullYear();

    var inputToDate = new Date(yy,mm,dd);
    var todayToDate = new Date(yyT, mmT, ddT);
    if (inputToDate >= todayToDate) {
        return true;
    }
    else {
        alert('Masukan tanggal harus lebih besar atau sama dengan tanggal hari ini');
        document.formPost.Tanggal.focus();
        return false;
    }
  }else {
    alert('Format tanggal salah! dd-mm-yyyy');
    document.formPost.Tanggal.focus();
    return false;
  }
}
</script>    
<script type="text/javascript" src="assets/js/fittext.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>
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