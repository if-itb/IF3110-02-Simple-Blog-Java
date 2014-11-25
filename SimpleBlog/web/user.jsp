<%-- 
    Document   : user
    Created on : Nov 25, 2014, 1:23:57 AM
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
<jsp:param name="pageTitle" value="Simple Blog | User - ${userBean.user.nama}"/> 
</jsp:include>

<% if (!((session.getAttribute("role") != null) && (session.getAttribute("role").equals("admin")))) {
    response.sendRedirect("index.jsp");
} %>

    <article class="art simple post">

        <header class="art-header" style="height:80px;">
            <div class="art-header-inner" style="margin-top: 0px; opacity: 1;">
                <time class="art-time"></time>
                <h2 class="art-title"></h2>
                <p class="art-subtitle"></p>
            </div>
        </header>
                
        <div class="art-body">
            <div class="art-body-inner">
                <!-- <hr class="featured-article" /> -->
                <p>Email : ${userBean.user.email}</p><br>
                <p>Nama  : ${userBean.user.nama}</p><br>
                <p>Role  : ${userBean.user.role}</p><br>
                


                <hr>
            </div>
        </div>


    </article>


<jsp:include page="footer.jsp"/>
</f:view>

