<%-- 
    Document   : login
    Created on : Nov 24, 2014, 10:49:41 PM
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
<jsp:param name="pageTitle" value="Simple Blog | Login"/> 
</jsp:include>

<% if ((session.getAttribute("email") != null) || (session.getAttribute("nama") != null) || (session.getAttribute("role") != null)) {
    response.sendRedirect("index.jsp");
} %>

  <article class="art simple post">
        <h2 class="art-title" style="margin-bottom:70px"></h2>
        <div class="art-body">
            <div class="art-body-inner">
                <h2>Login</h2>

                <div id="contact-area">
                <form method="post" action="login">
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email" placeholder="Email">

                    <label for="password">Password:</label>
                    <input type="password" name="password" id="Tanggal" placeholder="Password">

                    <input type="submit" name="submit" value="Login" class="submit-button">
                </form>
            </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

