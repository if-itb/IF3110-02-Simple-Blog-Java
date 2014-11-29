<%-- 
    Document   : addCookie
    Created on : Nov 25, 2014, 9:50:13 PM
    Author     : muntahailmi
--%>
<%
	Cookie Cusr=new Cookie("LogName",request.getParameter("username"));
	Cookie CType=new Cookie("LogType",request.getParameter("type"));
	Cusr.setMaxAge(60*60*24);
	CType.setMaxAge(60*60*24);
	Cusr.setPath("/");
	CType.setPath("/");
	response.addCookie(Cusr);
	response.addCookie(CType);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "../index.jsp");
	
%>
