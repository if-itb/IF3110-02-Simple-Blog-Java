<%-- 
    Document   : new_post
    Created on : Nov 20, 2014, 12:43:30 PM
    Author     : A46CB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<jsp:include page="header.jsp"/>

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
                     <h:form>
                         <h:outputLabel value="Judul:" for="Judul" />
                         <h:inputText id="Judul" value="#{newPostBean.newPost.judul}" />
                         <h:outputLabel value="Tanggal:" for="Tanggal" />
                         <h:inputText id="Tanggal" value="#{newPostBean.newPost.tanggal}">
                             <f:convertDateTime pattern="dd-MM-yyyy" />
                        </h:inputText>
                         <h:outputLabel value="Konten:" for="Konten" />
                         <h:inputTextarea id="Konten" cols="20" rows="20" value="#{newPostBean.newPost.konten}" />
                         <h:commandButton action="#{newPostBean.saveNewPost}" styleClass="submit-button" type="submit" value="Simpan" onclick="return validatePost(form)" />
                     </h:form>
                    
                </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

