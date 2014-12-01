<%-- 
    Document   : view_singular_post
    Created on : Nov 25, 2014, 4:28:40 PM
    Author     : M. Reza Irvanda
--%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Singular Post</title>
    </head>
    
    <body class="default">
        <div class="wrapper">
            <nav class="nav">
            <a style="border:none;" id="logo" href="index.xhtml"><h1>Simple<span>-</span>Blog</h1></a>
            <ul class="nav-primary">
                    <li><a href="new_post.php">+ Tambah Post</a></li>
            </ul>
            </nav>
        </div>
        <%
         Cookie cookie = null;
         Cookie[] cookies = null;
         String nilaiNama ="";
         String nilaiEmail ="";
            
         cookies = request.getCookies();
         if(cookies.length == 0){
             //do nothing
         }
         else{
//             cookie = cookies[0];
//             nilaiNama = cookie.getValue().toString();
             for(Cookie tmp : cookies){
                if(tmp.getName().equalsIgnoreCase("username")){
                    nilaiNama = tmp.getValue().toString();
                    break;
                }
             }
         }
         %>
       <sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver" user="root" password="root" url="jdbc:mysql://localhost/simpleblog-java"/>
        <sql:query var="results" dataSource="${db_source}">
            SELECT * FROM posts WHERE id=<%= request.getParameter("post") %> ;
        </sql:query>
            
        <sql:query var="results_komentar" dataSource="${db_source}">
            SELECT * FROM komentar WHERE id_post=<%= request.getParameter("post") %> ORDER BY id DESC ;
        </sql:query>
            
        <sql:query var="results_user" dataSource="${db_source}">
            SELECT username,email FROM users WHERE username="<%= nilaiNama %>" LIMIT 1 ;
        </sql:query>
            
            <c:set var="email" value="${results_user.rows[0].email}" />

<article class="art simple post">
    <c:forEach var="row" items="${results.rows}">
        <header class="art-header" style="margin : auto auto auto auto;">
            <time class="art-time"><c:out value="${row.tanggal}"/></time>
            <h2 class="art-title"> <c:out value="${row.judul}"/> </h2>
            <p class="art-subtitle"> <c:out value="${row.published}"/></p>
            <p class="art-subtitle"> <c:out value="${row.deleted}"/></p>
            <p class="art-subtitle"> <c:out value="${row.penulis}"/></p>   
    </header>

    <div class="art-body">
        <div class="art-body-inner">
            <hr class="featured-article" />
                <c:out value="${row.konten}"/>
            <hr />
    </c:forEach>        
            <h2>Komentar</h2>

            <div id="contact-area">
                <font id="error" color='white' >Ada kesalahan! </font> 
                <form>
                    <label for="Nama">Nama:</label>
                    <input type="text" name="Nama" id="Nama" value="<%= nilaiNama %>">
                    <label for="Email">Email:</label>
                    <input type="text" name="Email" id="Email" value="<c:out value="${email}"/>">   
                    <label for="Komentar">Komentar:</label><br>
                    <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
                 </form>
                <button onclick="validate()"> Submit</button>
                
            </div>


            <ul class="art-list-body"> 
                <li class="art-list-item">
                    <div id="komentar-baru" class="art-list-item-title-and-time">
                        <h2 id="nama-komentar-baru" class="art-list-title"> </h2>
                        <div id="tanggal-komentar-baru" class="art-list-time"> </div>
                    </div>
                    <p id="konten-komentar-baru"></p>
                </li>
                <c:forEach var="komentar" items="${results_komentar.rows}" >
            <li class="art-list-item">
                    <div class="art-list-item-title-and-time">
                        <h2 class="art-list-title"><c:out value="${komentar.nama}"/></h2>
                        <div class="art-list-time"><c:out value="${komentar.tanggal}"/></div>
                    </div>
                    <p><c:out value="${komentar.konten}"/></p>
            </li>

            
                </c:forEach>
            </ul>
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

<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/fittext.js"></script>
<script type="text/javascript" src="assets/js/app.js"></script>
<script type="text/javascript" src="assets/js/respond.min.js"></script>
<script type="text/javascript">
var xmlhttp;
function validate(){

    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange= myfunction;
    if(testRegex(document.getElementById("Email").value) && document.getElementById("Nama").value!=null && document.getElementById("Komentar").value!=null ){
        xmlhttp.open("POST","insert_comment.jsp?post=<%= request.getParameter("post")%>",true);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        var sendString = "Nama="+document.getElementById('Nama').value+"&"+"Komentar="+document.getElementById('Komentar').value+"&"+"Email="+document.getElementById('Email').value;
        xmlhttp.send(sendString);
        console.log("udah dikirim");
         
         
    return true;
    }
    else{
        console.log("salah");
        document.getElementById("error").setAttribute("color","red");
        return false;
    }
}

function myfunction()
{
    
    if(xmlhttp.readyState==4 && xmlhttp.status==200){
         
          document.getElementById("tanggal-komentar-baru").innerHTML="<%= new String((new java.util.Date().getYear()+1900)+"-"+(new java.util.Date().getMonth()+1)+"-"+(new java.util.Date()).getDate()) %>";
          console.log("harusnya udah diganti");
          document.getElementById("konten-komentar-baru").innerHTML=document.getElementById("Komentar").value;
          console.log("harusnya udah diganti");
          document.getElementById("nama-komentar-baru").innerHTML=document.getElementById("Nama").value;
          console.log("harusnya udah diganti");
          document.getElementById("Nama").value="";
          console.log("harusnya udah diganti");
          document.getElementById("Email").value="";
          console.log("harusnya udah diganti");
          document.getElementById("Komentar").value="";
          console.log("harusnya udah diganti");
          document.getElementById("error").setAttribute("color","white");
        console.log("harusnya udah diganti");
         
    }
    
}


function testRegex(contohString){
        var re = new RegExp("[a-z_0-9\\-]+@[a-z_0-9\-]+\\.[a-z]+");
        return re.test(contohString);
}
</script>       

</article>
</html>

            
            
