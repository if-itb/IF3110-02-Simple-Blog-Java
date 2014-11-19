package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.io.*;

public final class addpost_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');

String xDRIVER = "com.mysql.jdbc.Driver";
String xUSERNAME = "root";
String xPASSWORD = "";
String xSTRING = "jdbc:mysql://localhost:3306/simpleblogii";
Class.forName(xDRIVER);


      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Add Post</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form method=\"post\">\n");
      out.write("\t\t\t<input type=\"text\" id=\"user_name\" name=\"user_name\">\n");
      out.write("\t\t\t<br/>\n");
      out.write("\t\t\t<input type=\"text\" id=\"user_username\" name=\"user_password\">\n");
      out.write("\t\t\t<br/>\n");
      out.write("\t\t\t<input type=\"text\" id=\"user_password\" name=\"user_password\">\n");
      out.write("\t\t\t<br/>\n");
      out.write("\t\t\t<input type=\"text\" id=\"user_email\" name=\"user_email\">\n");
      out.write("\t\t\t<br/>\n");
      out.write("\t\t\t<input type=\"text\" id=\"user_status\" name=\"user_status\">\n");
      out.write("\t\t\t<br/>\n");
      out.write("\t\t\n");
      out.write("\t\t\t<input type=\"submit\" value=\"Tambah\">\n");
      out.write("\t\t</form>\n");
      out.write("\t\t");

			String user_name	=	 request.getParameter("user_name");
			String user_username = request.getParameter("user_username");
			String user_password = request.getParameter("user_password");
			String user_status = request.getParameter("user_status");
			String user_email	=	 request.getParameter("user_email");
			Connection conn = null;
			PreparedStatement ps = null;
			Class.forName(xDRIVER).newInstance();
			int updateQuery= 0 ;
			if(user_name != null && user_username != null && user_password != null  && user_email != null && user_status != null ){
				try{
					out.println("dsda");
					conn = DriverManager.getConnection(xSTRING, xUSERNAME, xPASSWORD);
					String query = "Insert into users(?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(query);
					/*
					ps.setString(4,user_name);
					ps.setString(2,user_username);
					ps.setString(3,user_password);
					ps.setString(5,user_email);
					ps.setString(6,user_status);
					*/
					ps.setString(1,"2");
					ps.setString(2,"adwisatya");
					ps.setString(3,"adwisatya");
					ps.setString(4,"Aryya Dwisatya W");
					ps.setString(5,"a.dwisaty4@yahoo.com");
					ps.setString(6,"1");
					ps.setString(7,"20101101");
					updateQuery = ps.executeUpdate();
					
					if(updateQuery != 0){
						System.out.print("sukses");
					}else{
						
						System.out.print("gagal");
					}
		
      out.write("\n");
      out.write("\t\tInserted sukses\n");
      out.write("\t\t");

			}catch(SQLException ex){

			out.println("Koneksi Bermasalah");
			out.println("Gagal tambah");
			}finally{
			ps.close();
			conn.close();
			}}			
		
      out.write("\t\t\n");
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
