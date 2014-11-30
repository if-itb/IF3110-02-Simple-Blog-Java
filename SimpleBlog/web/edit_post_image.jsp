<%-- 
    Document   : edit_post_image
    Created on : Nov 30, 2014, 4:14:55 PM
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
<jsp:param name="pageTitle" value="Simple Blog | Edit Post"/> 
</jsp:include>

<% if ((session.getAttribute("email") == null) && (session.getAttribute("nama") == null) && (session.getAttribute("role") == null)) {
    response.sendRedirect("index.jsp");
} %>

  <article class="art simple post">
        <h2 class="art-title" style="margin-bottom:70px"></h2>
        <div class="art-body">
            <div class="art-body-inner">
                <h2>Edit Post</h2>

                <div id="contact-area">
                <form method="post" action="edit_post" enctype="multipart/form-data">
                    <input type="hidden" name="id" id="id" value="${editPostBean.editedPost.id}">
                    <label for="Judul">Judul:</label>
                    <input type="text" name="Judul" id="Judul" value="${editPostBean.editedPost.judul}">

                    <label for="Tanggal">Tanggal:</label>
                    <input type="text" name="Tanggal" id="Tanggal" value="${editPostBean.editedPost.formatedTanggal}">
                    
                    <label for="Konten">Konten:</label><br>
                    <textarea name="Konten" rows="20" cols="20" id="Konten" placeholder="Konten post">${editPostBean.editedPost.konten}</textarea>
                    
                    <label for="file">Image:</label>
                    <input type="file" name="file" />
                    <label></label>
                    <div class="img">${editPostBean.editedPost.imageHTML}</div>
                    <input type="submit" name="submit" value="Simpan" class="submit-button" onclick="return validatePost()">
                </form>
            </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

