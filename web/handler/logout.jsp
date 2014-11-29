<%-- 
    Document   : addCookie
    Created on : Nov 25, 2014, 9:50:13 PM
    Author     : muntahailmi
--%>
<%
	Cookie Cusr=new Cookie("LogName","");
	Cookie CType=new Cookie("LogType","");
	Cusr.setMaxAge(0);
	CType.setMaxAge(0);
	Cusr.setPath("/");
	CType.setPath("/");
	response.addCookie(Cusr);
	response.addCookie(CType);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../index.jsp");
	
%>
