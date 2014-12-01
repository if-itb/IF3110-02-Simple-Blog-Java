<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:composite="http://java.sun.com/jsf/composite">
   <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" type="text/css" href="assets/style.css"></link>
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"></link>
        <link href='http://fonts.googleapis.com/css?family=Dawning+of+a+New+Day' rel='stylesheet' type='text/css'></link>
        <link href='http://fonts.googleapis.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'></link>
        <title>The Simplest Blog | Add User</title>
        <script type="text/javascript">
        function Validatemail() {
            var x = document.forms["myForm"]["Email"].value;
            var atpos = x.indexOf("@");
            var dotpos = x.lastIndexOf(".");
            if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {
                alert("Alamat e-mail tidak valid");
                return false;
            } else {
                return true;
                //addcomment();
                
            }
        }
        </script>
    </head>
    <body class="default">
        <div class="wrapper">
            <nav class="nav">
                <div id="loginsc">
                    <h:form rendered="#{Login.isLogin()}">
                        <h:outputLabel value="Halo #{Login.username}"></h:outputLabel>
                        <h:commandButton action="#{Login.logout()}" value="logout" style="margin-left: 6px; border: none; border-style: none; outline: none; background-color: #acaba9; font-family: 'Quicksand', sans-serif; height: 16px; font-weight: 700; font-color: #fff; cursor: pointer; color:#ffffff;"></h:commandButton>
                    </h:form>
                </div>
                
                <div id="nav-logo">
                    <a id="logo" href="index.jsp"><h1>The Simplest Blog</h1></a>
                </div>
                
                <div id="menu">
                    <ul class="nav-primary">
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="about.jsp">About</a></li>
                        <li><a href="about.jsp">Contact</a></li>
                    <f:subview id="manajemen-user" rendered="#{Login.isAdmin()}"><li><a href="page2.xhtml">User-Management</a></li></f:subview>
                    <f:subview id="draft" rendered="#{Login.isEditor() or Login.isAdmin()}"><li><a href="unpublished.jsp">Draft</a></li></f:subview>
                    <f:subview id="tambah-post" rendered="#{Login.isOwner() or Login.isAdmin()}"><li><a href="new.jsp">Create Post</a></li></f:subview>
                    </ul>
                </div>
            </nav>
            
            <article class="art simple post">
            
            <header class="art-header">
                <div class="art-header-inner">
                    <h2 class="art-title">Add User</h2>
                </div>
            </header>

            <div class="art-body">
                <div class="art-body-inner">
                    <div id="contact-area">
                    <h:form id="myForm">
                            <h:outputLabel value="Username :"></h:outputLabel>
                            <h:inputText binding="#{user}"></h:inputText>
                            
                            <h:outputLabel value="Password :"></h:outputLabel>
                            <h:inputSecret binding="#{pass}"></h:inputSecret>
                            
                            <h:outputLabel value="Role :"></h:outputLabel>
                            <h:selectOneMenu value="#{Login.role}" styleClass="select" binding="#{menu}">
                            <f:selectItem itemValue="owner" itemLabel="Owner" />
                            <f:selectItem itemValue="editor" itemLabel="Editor" />
                            <f:selectItem itemValue="admin" itemLabel="Admin" />			
                            </h:selectOneMenu>
                            <br></br>
                            <h:outputLabel value="Name :"></h:outputLabel>
                            <h:inputText binding="#{nama}"></h:inputText>
                            
                            <h:outputLabel value="Email :"></h:outputLabel>
                            <h:inputText id="Email" binding="#{em}"></h:inputText>
                            
                            <h:commandButton onclick="Validatemail()" action="#{Register.register(user.value, pass.value, menu.value, nama.value, em.value)}" value="submit" styleClass="submit-button"></h:commandButton>
                        </h:form>
                    </div>

                <h:outputLabel rendered="#{Register.isFailedRegistration()}">username does exist</h:outputLabel>
                </div>
            </div>
            </article>
        </div>
        <footer id="footerSlideContainer">
            <div id="footerSlideLink">
                <div id="footerSlideContent">
                    <div id="footerSlideText"><a href="">Back to top</a></div>
                </div>
                </div>
            </div>   
        </footer>
    </body>
</html>
</f:view>

