<%-- 
    Document   : post
    Created on : Nov 20, 2014, 8:54:19 AM
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
<jsp:param name="pageTitle" value="Simple Blog | Post - ${postBean.post.judul}"/> 
</jsp:include>

    <article class="art simple post">


        <header class="art-header">
            <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
                <time class="art-time">${postBean.post.tanggal}</time>
                <h2 class="art-title">${postBean.post.judul}</h2>
                <p class="art-subtitle"></p>
            </div>
        </header>

        <div class="art-body">
            <div class="art-body-inner">
                <!-- <hr class="featured-article" /> -->
                <p>${postBean.post.konten}</p>


                <hr />

                <h2>Komentar</h2>

                <div id="contact-area">
                    <% if ((session.getAttribute("email") != null) && (session.getAttribute("nama") != null)) { %>
                    <form method="post" action="#" onsubmit="return altAddComment()">
                        <input type="hidden" id="Nama" name="Nama" value="<%= session.getAttribute("nama") %>" >
                        <input type="hidden" id="Email" name="Email" value="<%= session.getAttribute("email") %>" >
                    <% } else { %>
                    <form method="post" action="#" onsubmit="return addComment()">
                        <label for="Nama">Nama:</label>
                        <input type="text" name="Nama" id="Nama">
                        <span id="namaerror" class=""></span><br>

                        <label for="Email">Email:</label>
                        <input type="text" name="Email" id="Email">
                        <span id="emailerror" class=""></span><br>
                        <% } %>
                        <input type="hidden" id="id_post" name="id_post" value="${postBean.post.id}" > 
                        <label for="Komentar">Komentar:</label>
                        <textarea name="Komentar" rows="20" cols="20" id="Komentar"></textarea>
                        <span id="komentarerror" class=""></span><br>

                        <input type="submit" name="submit" value="Kirim" class="submit-button">
                    </form>
                </div>

                <ul class="art-list-body" id="comments-area">

                </ul>
            </div>
        </div>


    </article>


<jsp:include page="footer.jsp"/>
</f:view>

