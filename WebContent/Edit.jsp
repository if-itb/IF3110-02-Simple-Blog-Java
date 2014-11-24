<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Simple Blog | Edit Post</title>
<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
</head>
<body class="default">
<%
String judul = null;
String isi = null;
String tanggal = null;
		try {
            String connectionURL = "jdbc:mysql://localhost/db_simpleblog";
            Connection conn = null; 
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = DriverManager.getConnection(connectionURL, "root", "");
            if(!conn.isClosed()) {
            	Statement stmt = conn.createStatement ();
                ResultSet rset = stmt.executeQuery ("SELECT * FROM post WHERE id="+request.getParameter("id"));
				if(rset.next()) {
					judul = rset.getString("judul");
					isi = rset.getString("konten");
					Date date = rset.getDate("tanggal");
					DateFormat df = new SimpleDateFormat("dd/M/yyyy");
					tanggal = df.format(date);
				}
				rset.close();
				stmt.close();
            }
            conn.close();
        }catch(Exception ex){
            out.println("Unable to connect to database"+ex);
        }   
%>

<f:view>
<div class="wrapper">
<nav class="nav">
    <a style="border:none;" id="logo" href="Home.jsp"><h1>Simple<span>-</span>Blog</h1></a>
    <ul class="nav-primary">
        <li><a href="new_post.html">+ Tambah Post</a></li>
    </ul>
</nav>

<article class="art simple post">    
    <h2 class="art-title" style="margin-bottom:40px">-</h2>
    <div class="art-body">
        <div class="art-body-inner">
            <h2>Edit Post</h2>

            <div id="contact-area">
                <form method="post" action="Edit">
                	<input type="hidden" id="OldDate" value="<% out.println(tanggal); %>">
                	<input type="hidden" name="Id" value="<% out.println(request.getParameter("id")); %>">
                    <label for="Judul">Judul:</label>
                    <input type="text" name="Judul" id="Judul" value="<% out.println(judul);%>">

                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="Tanggal" id="Tanggal" value="<% out.println(tanggal);%>">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="Konten" rows="20" cols="20" id="Konten"> <% out.println(isi);%></textarea>

                    <input type="submit" name="submit" value="Edit" class="submit-button" onclick="return validateTanggal(getElementById('OldDate').value, getElementById('Tanggal').value)">
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
</f:view>
<script type="text/javascript">
function validateTanggal(old_date, tanggal) 
      {
        // Tanggal lama
        oldDate = old_date.split('/');
        var dd = parseInt(oldDate[0],10);
        var mm = parseInt(oldDate[1],10);
        var yyyy = parseInt(oldDate[2],10);

        // Tanggal yg diinput
        date = tanggal.split('/');
        var hari = parseInt(date[0],10);
        var bulan = parseInt(date[1],10);
        var tahun = parseInt(date[2],10);

        if(tahun>yyyy)
        {
            return true;
        }
        else
        {
            if(tahun===yyyy)
            {
                if(bulan>mm)
                {
                    return true;
                }
                else
                {
                    if(bulan===mm)
                    {
                        if((hari>dd) || (hari===dd))
                        {
                            return true;
                        }
                        else
                        {
                            alert("Tanggal yang anda masukkan sudah lewat.\nSilahkan masukkan tanggal yang sesuai.");
                            return false;
                        }
                    }
                    else
                    { 
                        alert("Tanggal yang anda masukkan sudah lewat.\nSilahkan masukkan tanggal yang sesuai.");
                        return false;
                    }
                }
            }
            else
            {
                alert("Tanggal yang anda masukkan sudah lewat.\nSilahkan masukkan tanggal yang sesuai.");
                return false;
            }
        }
    }
</script>
</body>
</html>