package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.io.*;

public final class view_002dusers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/jspf/koneksi.jspf");
  }

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');

String xDRIVER = "com.mysql.jdbc.Driver";
String xUSERNAME = "root";
String xPASSWORD = "";
String xSTRING = "jdbc:mysql://localhost:3306/simpleblogii";
Class.forName(xDRIVER);


      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>View Users</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");

		try{
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			Class.forName(xDRIVER);
			connection = DriverManager.getConnection(xSTRING,xUSERNAME,xPASSWORD);
			statement = connection.createStatement();
			String Data = "select * from users";
			rs = statement.executeQuery(Data);
			
			while(rs.next()){
		
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(rs.getString("nama"));
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(rs.getString("username"));
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.print(rs.getString("email"));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\t\t\r\n");
      out.write("\t\t");

			}
			rs.close();
			statement.close();
			connection.close();
		}catch(Exception ex){
			out.println("Gagal tersambung. Terdaapat kesalahan.");
		}	
		
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
