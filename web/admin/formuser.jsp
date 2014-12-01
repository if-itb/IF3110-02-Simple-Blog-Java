<%-- 
    Document   : formuser
    Created on : Nov 25, 2014, 2:31:12 AM
    Author     : akhfa
--%>

<%@page import="source.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en-US">

<head>

<title>Add | Update User</title>

<link rel="stylesheet" type="text/css" href="../assets/css/formuser.css" />
</head>

<body>
<div class="container">

    <form method="POST" id="signup" action="../ButtonControllerServlet">

        <div class="header">
        
            <h3>Add | Update User</h3>

        </div>
        
        <div class="sep"></div>

        <div class="inputs">
            <% 
                User user = new User();
                
                if(request.getParameter("id").equals("|add|"))
                {
                    out.println("<input type='text' placeholder='Username' name='username' autofocus />");
                    
                    out.println("<input type='password' placeholder='Password' name='password' autofocus />");
                    
                    out.println("<input type='password' placeholder='Repeat Password' name='repeatpassword' autofocus />");
                    
                    out.println("<input type='text' placeholder='Nama' name='nama' autofocus />");

                    out.println("<input type='text' placeholder='Email' name='email' autofocus />");

                    out.println("<input type='text' placeholder='Role' name='role'/>");

                    out.println("<input type='submit' id='submit' name='addButton' value='Add Now'>");
                }
                else
                {
                    user.setUsername(request.getParameter("id"));
                    user.getOneUser();
                    
                    out.println("<input type='text' value='"+user.getUsername()+"' name='username' readonly autofocus />");
                    
                    out.println("<input type='password' value='"+user.getPassword()+"' placeholder='Password' name='password' autofocus />");
                    
                    out.println("<input type='password' value='"+user.getPassword()+"' placeholder='Repeat Password' name='repeatpassword' autofocus />");
                    
                    out.println("<input type='text' value='"+user.getNama()+"' name='nama' autofocus />");

                    out.println("<input type='text' value='"+user.getEmail()+"' name='email' autofocus />");

                    out.println("<input type='text' value='"+user.getRole()+"' name='role'/>");

                    out.println("<input type='submit' id='submit' name='editButton' value='Edit Now'>");
                    
                    out.println("<form action='../ButtonControllerServlet' method='POST'>"
                                        + "<input type='submit' id='submit' name='delUser' value='Delete User'>"
                                    + "</form>");
                }                
            %>
        </div>

    </form>

</div>

</body>

