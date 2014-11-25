<%-- 
    Document   : publish_post
    Created on : Nov 24, 2014, 10:35:28 PM
    Author     : A46CB
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Post"%>
<%@page import="managedbean.publishPostBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<jsp:include page="header.jsp"> 
<jsp:param name="pageTitle" value="Simple Blog | Publish Post"/> 
</jsp:include>

<% if (!((session.getAttribute("role") != null) && ((session.getAttribute("role").equals("admin")) || (session.getAttribute("role").equals("editor"))))) {
    response.sendRedirect("index.jsp");
} %>

    <div id="home">
        <div class="posts">
            <nav class="art-list">
              <ul class="art-list-body">
                  <c:forEach var="post" items="${publishPostBean.listUnpublishedPosts}">
                      <li class="art-list-item">
                        <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id=${post.id}">${post.judul}</a></h2>
                            <div class="art-list-time">${post.tanggal}</div>
                            <!-- <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div> -->
                        </div>
                        <p>${post.konten}</p>
                        <p>
                            <a href="edit_post.jsp?id=${post.id}">Edit</a> | <a href="publish_post?id=${post.id}" >Publish</a>
                        </p>
                        
                    </li>
                  </c:forEach>
              </ul>
            </nav>
        </div>
    </div>

<jsp:include page="footer.jsp"/>
</f:view>
