<%-- 
    Document   : edit_post
    Created on : Nov 21, 2014, 1:23:30 AM
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
<jsp:param name="pageTitle" value="Simple Post | Edit Post"/> 
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
                     <h:form>
                         <h:outputLabel value="Judul:" for="Judul" />
                         <h:inputText id="Judul" value="#{editPostBean.editedPost.judul}" />
                         <h:outputLabel value="Tanggal:" for="Tanggal" />
                         <h:inputText id="Tanggal" value="#{editPostBean.editedPost.tanggal}">
                             <f:convertDateTime pattern="dd-MM-yyyy" />
                        </h:inputText>
                         <h:outputLabel value="Konten:" for="Konten" />
                         <h:inputTextarea id="Konten" cols="20" rows="20" value="#{editPostBean.editedPost.konten}" />
                         <h:commandButton action="#{editPostBean.updatePost}" styleClass="submit-button" type="submit" value="Simpan" onclick="return validatePost(form)" />
                     </h:form>
                    
                </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

