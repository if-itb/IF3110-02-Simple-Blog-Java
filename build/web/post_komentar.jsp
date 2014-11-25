<%-- 
    Document   : post_komentar.jsp
    Created on : Nov 25, 2014, 2:31:32 PM
    Author     : adwisatya
--%>
<%@page import= "Komentar.Komentar" %>

<%
	String Parent	=	"1";
	String Name = "dasdsadas";
	String Email =  request.getParameter("Email");
	String Content = request.getParameter("Content");
	
	Komentar.NewKomentar(Parent, Name, Email, Content);
%>
