<%-- 
    Document   : list_user
    Created on : Nov 25, 2014, 1:33:58 AM
    Author     : A46CB
--%>

<%@page import="java.util.List"%>
<%@page import="entities.User"%>
<%@page import="managedbean.listUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<jsp:include page="header.jsp"/>

<% if (!((session.getAttribute("role") != null) && (session.getAttribute("role").equals("admin")))) {
    response.sendRedirect("index.jsp");
} %>

    <div id="home">
        <div class="posts">
            <nav class="art-list">
              <ul class="art-list-body">
                  <div class="">
                  
                      <br>
                        <a href="new_user.jsp" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> Create User</a>
                        <div style="clear:both;"></div>
                        <hr>
                        <Table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td>Email</td>
                                    <td>Nama</td>
                                    <td>Role</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="user" items="${listUserBean.listUsers}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.nama}</td>
                                    <td>${user.role}</td>
                                    <td>
                                        <center>
                                            <a href="user.jsp?id=${user.id}" class="btn btn-sm btn-success">View</a>
                                            <a href="edit_user.jsp?id=${user.id}" class="btn btn-sm btn-success">Edit</a>
                                            <a href="javascript:void(0)" onclick="deleteUserConfirmation(${user.id})" class="btn btn-sm btn-success">Delete</a>
                                        </center>
                                    </td>
                                </tr>
                             </c:forEach>
                        </Table>
                     
                  </div>
              </ul>
            </nav>
        </div>
    </div>

<jsp:include page="footer.jsp"/>
</f:view>
