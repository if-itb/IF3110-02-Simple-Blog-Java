<%-- 
    Document   : loadcomments
    Created on : Nov 24, 2014, 9:39:46 PM
    Author     : Sakurai
--%>

<%
    if (request.getParameter("ID") != null) {
        out.println("<li class=\"art-list-item\"><div class=\"art-list-item-title-and-time\"><h2 class=\"art-list-title\">$Nama</h2><div class=\"art-list-time\">$Tanggal</div></div><p>$Komentar</p></li>");
    } 
    
    
%>
