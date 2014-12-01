<%-- 
    Document   : edit
    Created on : Nov 25, 2014, 12:18:41 AM
    Author     : adwisatya
--%>

<%@page import="User.userPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "Post.Post" %>
<%@page import= "User.User" %>
<%@page import= "Post.PostBean" %>
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

<title>Simple Blog of Bangsatya | New Post</title>


</head>

<body class="default">
<div class="wrapper">
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

<article class="art simple post" style="margin-top:120px;">
        <div class="art-body" style="margin-top:70px">
        <div class="art-body-inner">
        	<%
        	if (typeC==4||typeC==2) {
        	%>
			You can't add new post
        	<% } else { %>
            <h2>New Post</h2>
            <div id="contact-area">
                <form method="post" action="handler/newPost.jsp">
                    <label for="Judul">Judul</label>
                    <input type="text" name="judul" id="Judul" value="">
		
                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="tanggal" id="Tanggal" value="">
                    <input type="hidden" name="owner" id="owner" value="<%=usrC %>">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="konten" rows="20" cols="20" id="Konten"></textarea>
					<input type="hidden" name="nomor" value="">
                    <input type="submit" name="submit" value="Post" class="submit-button" onclick="return cekInputan();">
                </form>
            </div>
			 <% } %>
        </div>
    </div>

</article>

<jsp:include page="footer.jsp"/>
</div>
<script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
    document.getElementById("Tanggal").value= yyyy + "-" + mm + "-" + dd;
</script>
<script>

function cekInputan(){
    var judul = document.getElementById('Judul').value;
    var tanggal = document.getElementById('Tanggal').value;
    var konten = document.getElementById('Konten').value;
    var dataString = 'judul='+ judul + '&tanggal=' + tanggal + '&konten=' + konten;
    
        if(judul=='' || tanggal =='' || konten =='')
        {
            alert('Silahkan lengkapi form yang tersedia');
			return false;
        }
        else
        {
            if(bandingkanTangal(tanggal)){

            }else{
                alert('Tanggal tidak benar. Silahkan masukkan tanggal lain.');
                return false;
			}
		}
}
function bandingkanTangal(input){
    var date1   =   new Date();
    var date2   =   new Date(input);

	if(date1.getFullYear()>date2.getFullYear()){
		return false;
	}else{
		if(date1.getMonth()>date2.getMonth()){
			return false;
		}else{
			if(date1.getDate()>date2.getDate()){
				return false;
			}else{
				return true;
			}
		}
	}
}
</script>
</body>
</html>