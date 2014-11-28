<%-- 
    Document   : load_comment
    Created on : Nov 26, 2014, 1:50:44 PM
    Author     : Gilang
--%>


<%@page import="com.gilang.beans.Komentar"%>
<jsp:useBean id="sql" scope="session" class="com.gilang.sql.DBAdapter" />

<ul class="art-list-body">				
	<%	for(Komentar k : sql.getComments(Integer.valueOf(request.getParameter("post_id")))){	  %>
		<li class="art-list-item">
			<div class="art-list-item-title-and-time">
				<h2 class="art-list-title"><a href="post.html"><%= k.getUser_id() %></a></h2>
				<%= k.getEmail() %>
				<div class="art-list-time"><%= k.getDate()%></div>
			</div>
			<p> <%= k.getContent()%></p>
		</li>
	<%	} %>
</ul>
