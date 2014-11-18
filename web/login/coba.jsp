
<%@ page import ="java.util.*"%>
<%!
  /** this function converts an array of cookies into a hashtable */
  Hashtable cookieTable(Cookie[] cookies) {
    Hashtable cookieTable = new Hashtable();
    if (cookies != null) {
      for (int i=0; i < cookies.length; i++)
        cookieTable.put(cookies[i].getName(), cookies[i].getValue());
    }
    return cookieTable;
  }
%>
<%
  Cookie myCookie;
  String username = new String();
  Hashtable cookies = cookieTable(request.getCookies());

  String newLogin = request.getParameter("name");
  if (cookies.containsKey("name")) {
    username = (String)cookies.get("name");
  }

  if (newLogin != null) {
    if (newLogin.equals("")) {
      myCookie = new Cookie("name", "");
      myCookie.setMaxAge(0);
      username = null;
    } else {
      myCookie = new Cookie("name", newLogin);
      myCookie.setMaxAge(3600);
      username = newLogin;
    }

    myCookie.setDomain(".somedomain.com");
    response.addCookie(myCookie);
  }
%>

<html>
  <body bgcolor="#ffffff">

  <% if (username == null || username.equals("")) { %>

    You look like a new user! <br />
    <form method="get" action="<%= request.getRequestURI()%>">
      Enter your name to create login : <br />
      <input type="text" size="8" name="name" />
      <input type="submit" value="login" />
    </form>

  <% } else { %>

    Welcome back, <%= username %> <br />
    <%= new java.util.Date() %>

    <form method="get" action="<%= request.getRequestURI()%>">
      Enter your name to create login : <br />
      <input type="hidden" name="name" value=""  />
      <input type="submit" value="logout" />
    </form>

  <% } %>
  </body>
</html>