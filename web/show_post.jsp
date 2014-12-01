<%-- 
    Document   : post
    Created on : Nov 25, 2014, 1:51:36 PM
    Author     : adwisatya
--%>
<%@page import= "User.userPaket"%>
<%@page contentType= "text/html" pageEncoding="UTF-8"%>
<%@page import = "Post.Post" %>
<%@page import= "User.User" %>
<%@page import= "Komentar.Komentar" %>
<%@page import= "Post.PostBean" %>
<%@page import= "java.text.SimpleDateFormat" %>
<%@page import= "java.util.Date" %>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.ArrayList" %>
<%@page import= "javax.faces.bean.ManagedBean" %>
<%@page import= "javax.faces.bean.RequestScoped" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="Simple Blog">
<meta name="author" content="Bangsatya">

<!-- Twitter Card -->
<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="omfgitsasalmon">
<meta name="twitter:title" content="Simple Blog">
<meta name="twitter:description" content="Simple Blog">
<meta name="twitter:creator" content="Bangsatya Blog">
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
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	PostBean pBean =  new PostBean();
	pBean.ViewPost(request.getParameter("id"));
%>
<title>Simple Blog of Bangsatya | <% out.println(pBean.getJudul()); %></title>
</head>
<jsp:include page="header.jsp"/>
<%
	String usrC=" ";
	int typeC=4;
	Cookie[] cookies = null;
	cookies = request.getCookies();
	if (cookies!=null){
		for (Cookie c:cookies){
			if (c.getName().equals("LogName")){
				usrC=c.getValue();
				for (Cookie c2:cookies){
				    if (c2.getName().equals("LogType")){
							typeC=Integer.parseInt(c2.getValue());
						}
				}
			}
		}
	}
%>
<article class="art simple post">
    <header class="art-header">
        <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
            <time class="art-time"><%=pBean.getTanggal()%></time>
            <h2 class="art-title"><%=pBean.getJudul()%></h2>
            <p class="art-subtitle"></p>
        </div>
    </header>
	<div class="art-body">
        <div class="art-body-inner">
            <% out.println(pBean.getKonten()); %>
            <hr />
			<% if (typeC!=4){ %>
			<p>
			  <a href="edit.jsp?id=<% out.println(pBean.getId()); %>">Edit</a> | <a href="handler/delPost.jsp?id=<% out.println(pBean.getId()); %>">Hapus</a>
			</p>
			<% } %>
            
			<div id="Komentar">
			</div>
			<div id="formKomentar">
			</div>
			<hr/>
			<div id="contact-area">
				<form method="post" onsubmit="return SubmitComment(this);">
					<label for="Nama">Nama:</label>
					<input type="text" id="pNama" name="nama">
					
					<label for="Email">Email:</label>
					<input type="text" id="pEmail" name="email" >
					<label for="Komentar">Komentar:</label><br>
					<textarea id="pPesan" name="pesan" cols="20" rows="20"></textarea>
					<input type="hidden" id="pTanggal" name="tanggal" value="<% out.println(sdf.format(date)); %>">
					<input type="hidden" id="pId" name="id" value="<% out.println(pBean.getId()); %>">
					<input type="button" class="submit-button" name="postKomentar" value="Kirim" onclick="return cekEmail();">
				</form>
			</div>
			
			<span id="terbaru">
			</span>
			<hr/>
			<!-- bagian komentar -->
			<% 
				String Driver = "com.mysql.jdbc.Driver";
				String DbUser = "root";
				String DbPass = "";
				String DbName = "Tubes2WBD";
				String DbLoc1 = "jdbc:mysql://localhost:3306/";
				String DbLoc2 = DbLoc1+DbName;
				Connection conn = null;
				Statement st = null;
				ResultSet rs = null;

				try {
					Class.forName(Driver).newInstance();
					conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
					st=conn.createStatement();
					rs=st.executeQuery("SELECT * FROM comment WHERE Parent ='"+request.getParameter("id")+"';");
					while(rs.next()){
						out.println("<li class=\"art-list-item\">");
						out.println("	<div class=\"art-list-item-title-and-time\">");
						out.println("		<h2 class=\"art-list-title\"><a href=\"mailto:"+rs.getString("email")+"\">"+rs.getString("Name")+"</a></h2>");
						out.println("		<div class=\"art-list-time\">"+rs.getString("Time")+"<br>");
						out.println("		</div>");
						out.println("	</div>");
						out.println("	<p>"+rs.getString("Content")+"</p>");
						out.println("</li>");
					}
				} catch(Exception e){
					throw e;
				} finally{
					try {
						rs.close();
						st.close();
						conn.close();
					} catch (SQLException e) {
						throw e;
					}
				}					
			%>
			
        </div>
    </div>
</article>

<jsp:include page="footer.jsp"/>
</div>
<script>
function cekEmail() {
	var x = document.getElementById("pEmail").value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Email address salah");
        return false;
    }else{
		PostKomentar();
	}
}
function PostKomentar(){
	var isinama = document.getElementById("pNama").value;
	var isiemail = document.getElementById("pEmail").value;
	var isipesan = document.getElementById("pPesan").value;
	var isiid = document.getElementById("pId").value;
	var isitanggal	=	document.getElementById("pTanggal").value;
	var xmlhttp=GetXmlHttpObject();
	if(xmlhttp==null){
		alert("Silahkan gunakan browser yang mendukung AJAX");
		return;
	}	
	var url	=	"post_komentar.jsp";
    var param="ID="+isiid+"&Name="+isinama+"&Email="+isiemail+"&Content="+isipesan;
	document.getElementById("terbaru").innerHTML = "Sedang memproses komentar";
	//var message = "<div id=unit-komentar align=center><br>" + isinama + "<br>" + isitanggal + "<br>" + isipesan + "<hr></div>";
	
	var message="<li class=\"art-list-item\">";
	message=message+"	<div class=\"art-list-item-title-and-time\">";
	message=message+"		<h2 class=\"art-list-title\"><a href=\"mailto:"+isiemail+"\">"+isinama+"</a></h2>";
	message=message+"		<div class=\"art-list-time\">"+isitanggal+"<br>";
	message=message+"		</div>";
	message=message+"	</div>";
	message=message+"	<p>"+isipesan+"</p>";
	message=message+"</li>";
	
	document.getElementById("terbaru").innerHTML = message;
    xmlhttp.open("POST",url,true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.setRequestHeader("Content-length", param.length);
    xmlhttp.setRequestHeader("Connection", "close");
    xmlhttp.send(param);
}

function GetXmlHttpObject() {
    var xmlhttp=null;
    try {
        // Firefox, Opera 8.0+, Safari
        xmlhttp=new XMLHttpRequest();
    }
    catch (e) {
        // Internet Explorer
        try {
            xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlhttp;
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
