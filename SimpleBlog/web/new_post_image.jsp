<%-- 
    Document   : new_post_image
    Created on : Nov 26, 2014, 6:40:45 PM
    Author     : A46CB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<jsp:include page="header.jsp"> 
<jsp:param name="pageTitle" value="Simple Blog | Add New Post"/> 
</jsp:include>

<% if ((session.getAttribute("role") != null) && ((session.getAttribute("role").equals("owner")) || (session.getAttribute("role").equals("admin")))) {

  } else { 
    response.sendRedirect("index.jsp");
} %>

  <article class="art simple post">
        <h2 class="art-title" style="margin-bottom:70px"></h2>
        <div class="art-body">
            <div class="art-body-inner">
                <h2>Tambah Post</h2>

                <div id="contact-area">
                <form method="post" action="new_post" enctype="multipart/form-data">
                    <label for="Judul">Judul:</label>
                    <input type="text" name="Judul" id="Judul" placeholder="Judul post">

                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="Tanggal" id="Tanggal" placeholder="DD-MM-YYYY">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="Konten" rows="20" cols="20" id="Konten" placeholder="Konten post"></textarea>
                    <label for="file">Image:</label>
                    <input type="file" name="file" />
                    <input type="submit" name="submit" value="Simpan" class="submit-button" onclick="return validatePost()">
                </form>
            </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

