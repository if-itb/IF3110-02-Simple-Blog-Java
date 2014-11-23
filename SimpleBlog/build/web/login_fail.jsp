<%-- 
    Document   : login_fail
    Created on : Nov 21, 2014, 12:11:48 PM
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
            <h:form id="login_form">
                <h2>Login Please:</h2>
                <table width="250" border="0" cellspacing="0" cellpadding="2">
                    <tr>
                        <td colspan="2">
                            Incorrect Username or Password!!<br>
                            <h:message for="username" styleClass=""/><br>
                            <h:message for="password" styleClass=""/>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Username : "/></td>
                        <td><h:inputText id="username" value="#{login_bean.username}" /></td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Password : "/></td>
                        <td><h:inputSecret id="password" value="#{login_bean.password}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <h:commandButton action="{login_bean.checkValidUser}" value="Login" type="submit" />
                        </td>
                    </tr>
                </table>
            </h:form>
        </f:view>
    </body>
</html>
