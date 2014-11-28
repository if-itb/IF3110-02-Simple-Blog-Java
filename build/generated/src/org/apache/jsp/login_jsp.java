package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
  
    Cookie cookie = null;
    String usrC=null;
    String passC=null;
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if(cookies!=null)
    {
        //out.println("Yay, cookies! "+cookies.length);
        /*for(int i=0; i<cookies.length; i++)
        {
            cookie = cookies[i];
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            //out.println(cookie.getName()+" "+cookie.getValue()+" <br/>");
        }*/
        cookie = cookies[cookies.length-1];
        usrC = cookie.getName();
        passC = cookie.getValue();
        out.println("Selamat datang kembali "+usrC);
    }
    //else out.println("No cookies :(");

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            if(usrC!=null && passC!=null)
            {
        
      out.write("\n");
      out.write("        <form method=\"post\" action=\"index.jsp\" id=\"CookiedLogin\">\n");
      out.write("            <input type=\"hidden\" name=\"username\" id=\"username\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${usrC}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\t\n");
      out.write("            <input type=\"hidden\" name=\"password\" id=\"password\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${passC}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("        </form>\n");
      out.write("        <script>\n");
      out.write("            //var s1=\"Selamat datang kembali\";\n");
      out.write("            //var s2= ");
      out.print( usrC );
      out.write(";\n");
      out.write("            //alert(s2);\n");
      out.write("            //s1.concat(s2);\n");
      out.write("            document.getElementById(\"CookiedLogin\").submit();\n");
      out.write("        </script>\n");
      out.write("        ");
 } else {
      out.write("\n");
      out.write("        <h3>Silakan login</h3><br>\n");
      out.write("        <form method=\"post\" action=\"login_handler.jsp\">\n");
      out.write("            <label for=\"username\">Username</label>\n");
      out.write("            <input type=\"text\" name=\"username\" id=\"username\" value=\"\">\t\n");
      out.write("            <br>\n");
      out.write("            <label for=\"Password\">Password</label>\n");
      out.write("            <input type=\"password\" name=\"password\" id=\"password\" value=\"\">\n");
      out.write("            <br>\n");
      out.write("            <input type=\"submit\" name=\"submit\" value=\"Login\" class=\"submit-button\">\n");
      out.write("        </form>\n");
      out.write("        ");
 if(request.getParameter("errormessage")!=null)
            {
                out.println("Username dan password Anda salah");
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
