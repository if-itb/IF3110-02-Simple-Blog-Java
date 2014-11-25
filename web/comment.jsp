<%-- 
    Document   : comment
    Created on : Nov 25, 2014, 7:26:32 PM
    Author     : SPM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
        <div>Post content here</div>
	<h2>Komentar</h2>
	<div id="contact-area"></div>
	<form method="post" onsubmit="">
	    <label for="Nama">Nama:</label>
	    <input type="text" name="Nama" id="Nama"></input>
	    <label>Email:</label>
	    <input type="text" name="Email" id="Email"> </input>
	    <label for="Komentar">Komentar:</label><br></br>
	    <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
	    <input type="reset" name="submit" value="Kirim" class="submit-button" onclick=""></input>
	</form>
	
	<div id="ajaaxed">
	    
	</div>
    </body>
</html>
