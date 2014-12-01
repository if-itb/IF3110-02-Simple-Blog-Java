<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="Deskripsi Blog">
<meta name="author" content="Judul Blog">

<!-- Twitter Card -->
<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="omfgitsasalmon">
<meta name="twitter:title" content="Simple Blog">
<meta name="twitter:description" content="Deskripsi Blog">
<meta name="twitter:creator" content="Simple Blog">
<meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">

<meta property="og:type" content="article">
<meta property="og:title" content="Simple Blog">
<meta property="og:description" content="Deskripsi Blog">
<meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
<meta property="og:site_name" content="Simple Blog">

<link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<jsp:include page ="/manajemen_user"/>

<%  
    Cookie[] cookies = request.getCookies();
    String loginUser = "";
    String statusUser ="";
    boolean foundCookie = false;
    
    for (int i = 0;i<cookies.length;i++)
    {
        Cookie c = cookies[i];
        if (c.getName().equals("username"))
        {
            loginUser = c.getValue();
            foundCookie = true;
        }
        if (c.getName().equals("status"))
        {
            statusUser = c.getValue();
            foundCookie = true;
        }
    }
%>

<title>Simple Blog</title>

<script>
	function deleteUser(delID)
	{
                var confDialog = confirm("Are you sure want to delete this user?");
                if (confDialog)
		{
			window.location = 'delete_user?delete=' + delID; 
		}
	}
</script>

</head>

<body class="default">
<div class="wrapper">

<div style="text-align: right; z-index: 9 ; margin-right: 20px; margin-top:10px">
    <%
        if (foundCookie)
        {
            if (statusUser.equals("A"))
            {
                out.println("User: ");
                out.println(loginUser);
                out.println("<br>Role: ");
                out.println("Admin<br>");
                %>
                    <a href ="#">Manajemen User</a>
                    <br>
                    <a href ="publish_post.jsp">Publish Post</a>
                    <br>
                    <a href ="deleted_post.jsp">Deleted Post</a>
                    <%
            }
            else
            {
                out.println("You are not admin!");
            }
        }
    %>    
</div>
    
<nav class="nav">
    <a style="border:none;" id="logo" href="index.jsp"><h1>Simple<span>-</span>Blog</h1></a>
</nav>

<br>
    <%if (request.getParameter("edit")!= null)
    {
       if (request.getParameter("edit").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Edit Success</span><%
       }
    }%>
    
    <%if (request.getParameter("create")!= null)
    {
       if (request.getParameter("create").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Create Success</span><%
       }
    }%>
    <%if (request.getParameter("delete")!= null)
    {
       if (request.getParameter("delete").equals("success"))
       {
            %><span class="label label-success" style ="margin-left:5%;">Delete Success</span><%
       }
    }%>
    
<table class ="table table-condensed" id = "table-user">
    <tr>
        <td> # </td>
        <td> Username </td>
        <td> Status </td>
        <td> Aksi </td>
    </tr>
    <%
        List<String[]> userData = (List<String[]>)request.getAttribute("queryResult");
        int rowCounter = 1;
        for (String[] record : userData)
        {
            out.println("<tr>");
            out.println("<td>"+rowCounter+"</td>");
            int colCounter = 1;
            for (String data : record)
            {
                if (colCounter != 2)
                {
                    out.println("<td>");
                    if (colCounter == 3)
                    {
                        if (data.equals("A"))
                            out.println("Admin");
                        else if (data.equals("O"))
                            out.println("Owner");
                        else if (data.equals("E"))
                            out.println("Editor");
                    }
                    else
                        out.println(data);
                    out.println("</td>");
                }
                
                colCounter++;
            }
            out.println("<td>");
        %> 
        <a href="edit_user.jsp?user=<%out.println(userData.get(rowCounter-1)[0]);%>&status=<%out.println((userData.get(rowCounter-1))[2]);%>">Edit</a> | <a href="javascript:void(0);" onclick='deleteUser(<%out.println("\""+userData.get(rowCounter-1)[0]+"\"");%>)'>Delete</a>
                
        <%
            out.println("</td>");
            out.println("</tr>");
            rowCounter++;
        }
        %>
</table>

<br>
<a href ="new_user.jsp" style ="margin-left:5%;"> Create New User </a>

</div>

<footer class="footer">
    <div class="back-to-top"><a href="">Back to top</a></div>
    <!-- <div class="footer-nav"><p></p></div> -->
    <div class="psi">&Psi;</div>
    <aside class="offsite-links">
        Asisten IF3110 /
        <a class="rss-link" href="#rss">RSS</a> /
        <br>
        <a class="twitter-link" href="http://twitter.com/YoGiiSinaga">Yogi</a> /
        <a class="twitter-link" href="http://twitter.com/sonnylazuardi">Sonny</a> /
        <a class="twitter-link" href="http://twitter.com/fathanpranaya">Fathan</a> /
        <br>
        <a class="twitter-link" href="#">Renusa</a> /
        <a class="twitter-link" href="#">Kelvin</a> /
        <a class="twitter-link" href="#">Yanuar</a> /
        
    </aside>
</footer>

</div>

</body>
</html>