<%-- 
    Document   : restore_post
    Created on : Nov 25, 2014, 5:20:48 PM
    Author     : A46CB
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Post"%>
<%@page import="managedbean.restorePostBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<jsp:include page="header.jsp"> 
<jsp:param name="pageTitle" value="Simple Blog | Restore Post"/> 
</jsp:include>

<% if (!((session.getAttribute("role") != null) && (session.getAttribute("role").equals("admin")))) {
    response.sendRedirect("index.jsp");
} %>

    <div id="home">
        <div class="posts">
            <nav class="art-list">
              <ul class="art-list-body">
                  <c:forEach var="deletedpost" items="${restorePostBean.listDeletedPosts}">
                      <li class="art-list-item">
                        <div class="art-list-item-title-and-time">
                            <h2 class="art-list-title"><a href="post.jsp?id=${deletedpost.id}">${deletedpost.judul}</a></h2>
                            <div class="art-list-time">${deletedpost.tanggal}</div>
                            <!-- <div class="art-list-time"><span style="color:#F40034;">&#10029;</span> Featured</div> -->
                        </div>
                        <p>${deletedpost.konten}</p>
                        <p>
                            <a href="restore_post?id=${deletedpost.id}" >Restore</a>
                        </p>
                        
                    </li>
                  </c:forEach>
              </ul>
            </nav>
        </div>
    </div>

<jsp:include page="footer.jsp"/>
</f:view>
