<%-- 
    Document   : header.jsp
    Created on : Nov 25, 2014, 9:50:13 PM
    Author     : muntahailmi
--%>
<%
	String usrC=" ";
	int typeC=4;
	Cookie[] cookies = null;
	cookies = request.getCookies();
	if (cookies!=null){
		for (Cookie c:cookies){
			if (c.getName().equals("LogName")){
				usrC=c.getValue();
				for (Cookie c2:cookies){
				    if (c2.getName().equals("LogType")){
							typeC=Integer.parseInt(c2.getValue());
						}
				}
			}
		}
	}
%>

<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple-Blog<span>-of-</span>Bang-Satya-Ilmi-Ojan</h1></a>
    <ul class="nav-primary">
    <%
    	if (typeC==4){
			out.println("Halo, Guest");
			if (request.getParameter("errormessage")!=null) {
		        out.println("<br/>Username/password anda salah");
		    }
			%>
			<form method="post" action="login_handler.jsp">
		        <div>
		        <input type="text" name="username" id="username" value="" placeholder="username">
		        </div>
		        <div>
		        <input type="password" name="password" id="password" value="" placeholder="password">
		        <input type="submit" name="submit" value="Login" class="submit-button">
		    	</div>
		    </form>
			<%
    	} else {
    		if (typeC==1){
    			out.println("Halo, Owner "+usrC);
        		%>
	            <li></li><li><a href="handler/logout.jsp">Logout</a></li>
		        <br/>
		        <li><a href="new_post.jsp">+ Tambah Post</a></li>
	        	<%
    		} else if (typeC==2){
    			out.println("Halo, Editor "+usrC);
        		%>
                <li></li><li><a href="handler/logout.jsp">Logout</a></li>
		        <br/>
		        <li><a href="postAdm.jsp">+ Unpublished Post</a></li>
            	<%
    		} else if (typeC==3){
    			out.println("Halo, Admin "+usrC);
        		%>
                <li></li><li><a href="handler/logout.jsp">Logout</a></li>
		        <br/>
		        <li><a href="add_user.jsp">+ Tambah User</a></li>
		        <li><a href="userAdm.jsp">+ Manage User</a></li>
		        <br/>
		        <li><a href="new_post.jsp">+ Tambah Post</a></li>
		        <li><a href="postAdm.jsp">+ Unpublished Post</a></li>
            	<%
    		}
    	}
    %>
        <%--<br/>
        <li><a href="new_post.jsp">+ Tambah Post</a></li>
        <li><a href="admin.jsp">+ Unpublished Post</a></li> --%>
    </ul>
    <a name="top"></a>
</nav>