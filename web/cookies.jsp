<%-- 
    Document   : cookies
    Created on : Nov 24, 2014, 4:06:44 PM
    Author     : Teofebano
--%>


<html>
<head>
<title>Setting Cookies</title>
</head>
<body>
<%
   // Create cookies for first and last names.      
   Cookie username = new Cookie("username",session.getAttribute("username").toString());
   Cookie password = new Cookie("password",session.getAttribute("password").toString());
   String user=session.getAttribute("username").toString();
   String role=session.getAttribute("role").toString();
   int user_id= (Integer) session.getAttribute("user_id");

   // Set expiry date after 24 Hrs for both the cookies.
   username.setMaxAge(60*3); 
   password.setMaxAge(60*3); 

   // Add both the cookies in the response header.
   response.addCookie( username );
   response.addCookie( password );
   session.setAttribute("username",user);
   session.setAttribute("role",role);
   session.setAttribute("user_id",user_id);
   if (role == "Owner"){
       response.sendRedirect("home-owner.jsp");
   }
   else if (role == "Editor"){
       response.sendRedirect("home-editor.jsp");
   }
   else if (role == "Admin"){
       response.sendRedirect("home-admin.jsp");
   }
   
%>
</body>
</html>