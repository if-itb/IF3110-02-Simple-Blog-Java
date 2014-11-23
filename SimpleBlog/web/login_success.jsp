<%-- 
    Document   : login_success
    Created on : Nov 21, 2014, 12:12:08 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <h:form>
                <h2>Hello <h:outputText value="#{login_bean.username}"/>, you are successfully login. </h2>
            </h:form>
        </f:view>
    </body>
</html>
