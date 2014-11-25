<%-- 
    Document   : deleteNonAdmin
    Created on : Nov 25, 2014, 11:29:57 PM
    Author     : M. Reza Irvanda
--%>

<sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver" user="root" password="" url="jdbc:mysql://localhost/simpleblog-java"/>
<sql:update var="results" dataSource="${db_source}">
            UPDATE `simpleblog-java`.`posts` SET `deleted` = '1' WHERE `posts`.`id`<%= request.getParameter("post") %> ;
</sql:update>
            <jsp:forward page="view_post.jsp"/>
