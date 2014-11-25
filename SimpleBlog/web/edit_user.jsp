<%-- 
    Document   : edit_user
    Created on : Nov 25, 2014, 1:14:48 AM
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
<jsp:param name="pageTitle" value="Simple Blog | Edit User"/> 
</jsp:include>

<% if (!((session.getAttribute("role") != null) && (session.getAttribute("role").equals("admin")))) {
    response.sendRedirect("index.jsp");
} %>

  <article class="art simple post">
        <h2 class="art-title" style="margin-bottom:70px"></h2>
        <div class="art-body">
            <div class="art-body-inner">
                <h2>Edit User</h2>

                <div id="contact-area">
                     <h:form>
                         <h:outputLabel value="Email:" for="Email" />
                         <h:inputText id="Email" value="#{editUserBean.editedUser.email}" />
                         <h:outputLabel value="Password:" for="Password" />
                         <h:inputText id="Password" value="#{editUserBean.editedUser.password}" />
                         <h:outputLabel value="Nama:" for="Nama" />
                         <h:inputText id="Nama" value="#{editUserBean.editedUser.nama}" />
                         <h:outputLabel value="Role:" for="Role" />
                         <h:selectOneMenu id="Role" value="#{editUserBean.editedUser.role}">
                                <f:selectItem itemValue="admin" itemLabel="Admin" />
                                <f:selectItem itemValue="editor" itemLabel="Editor" />
                                <f:selectItem itemValue="owner" itemLabel="Owner" />
                        </h:selectOneMenu><br>
                         <h:commandButton action="#{editUserBean.updateUser}" styleClass="submit-button" type="submit" value="Simpan" />
                     </h:form>
                    
                </div>
            </div>
        </div>

    </article>

<jsp:include page="footer.jsp"/>
</f:view>

