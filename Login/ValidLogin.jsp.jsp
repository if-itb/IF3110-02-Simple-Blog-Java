<!--userLogged(welcome page if login success)-->


<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="ExamplePackage.UserBean"
   %>
   <% 
    response.setHeader("Cache-Control","no-store,must-revalidate"); 
    response.setHeader("Pragma","no-cache"); 
    response.setDateHeader ("Expires", -1); 
    new java.util.Date();
    if(session.getAttribute("currentSessionUser")!=null)
    {
    %>
    
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
         <SCRIPT type="text/javascript">
function noBack() { 
   window.history.forward(); 
   }
</SCRIPT>
      </head>
   
<BODY onload="noBack();">
<a href="LogoutServlet">Logout</a>
         <center>
            <% UserBean currentUser = (UserBean)(session.getAttribute("currentSessionUser"));%>
         
            Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
         </center>

      </body>
   
   </html>

<%}
else
response.sendRedirect("LoginPage.jsp");%>