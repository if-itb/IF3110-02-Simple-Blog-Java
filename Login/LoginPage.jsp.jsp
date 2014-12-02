<!--LoginPage.jsp(view of Login Page)-->


<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>
<% 
    response.setHeader("Cache-Control","no-store, must-revalidate"); 
    response.setHeader("Pragma","no-cache"); 
    response.setDateHeader ("Expires", -1);
    new java.util.Date();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
      <script>
function clearForms()
{
  var i;
  for (i = 0; (i < document.forms.length); i++) {
    document.forms[i].reset();
  }
}
function validateForm()
{
var x=document.forms["myForm"]["un"].value;
if (x==null || x=="")
  {
  alert("Username must be filled out");
  document.getElementById('un').focus();
  return false;
  }
var y=document.forms["myForm"]["pw"].value;
if (y==null || y=="")
  {
  alert("password must be filled out");
  document.getElementById('pw').focus();
  return false;
  }
}
</script>
     <title>Login Page</title>
   </head>
    <body onLoad="clearForms()" onunload="clearForms()">
    <form action="LoginServlet" onsubmit="return validateForm()" method="post"  name="myForm">
       Please enter your user name       
      <input type="text" name="un" id="un"/><br>      
      Please enter your password
      <input type="text" name="pw" id="pw"/>
      <input type="submit" value="submit">         
      </form>
   </body>
</html>