package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import User.userPaket;
import Post.Post;
import User.User;
import Post.PostBean;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!-- \r\n");
      out.write("    Document   : index\r\n");
      out.write("    Created on : Nov 24, 2014, 4:14:48 PM\r\n");
      out.write("    Author     : adwisatya\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\r\n");
      out.write("<meta name=\"description\" content=\"Simple Blog\">\r\n");
      out.write("<meta name=\"author\" content=\"Bangsatya\">\r\n");
      out.write("\r\n");
      out.write("<!-- Twitter Card -->\r\n");
      out.write("<meta name=\"twitter:card\" content=\"summary\">\r\n");
      out.write("<meta name=\"twitter:site\" content=\"omfgitsasalmon\">\r\n");
      out.write("<meta name=\"twitter:title\" content=\"Simple Blog\">\r\n");
      out.write("<meta name=\"twitter:description\" content=\"Simple Blog\">\r\n");
      out.write("<meta name=\"twitter:creator\" content=\"Bangsatya Blog\">\r\n");
      out.write("<meta name=\"twitter:image:src\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">\r\n");
      out.write("\r\n");
      out.write("<meta property=\"og:type\" content=\"article\">\r\n");
      out.write("<meta property=\"og:title\" content=\"Simple Blog\">\r\n");
      out.write("<meta property=\"og:description\" content=\"Deskripsi Blog\">\r\n");
      out.write("<meta property=\"og:image\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">\r\n");
      out.write("<meta property=\"og:site_name\" content=\"Simple Blog\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/screen.css\" />\r\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"img/favicon.ico\">\r\n");
      out.write("\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("\r\n");
      out.write("<title>Simple Blog of Bangsatya</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"default\">\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write('\r');
      out.write('\n');

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"home\">\r\n");
      out.write("    <div class=\"posts\">\r\n");
      out.write("        <nav class=\"art-list\">\r\n");
      out.write("          <ul class=\"art-list-body\">\r\n");
      out.write("\t\t\t");

				PostBean pBean =  new PostBean();
				for(int i=0;i<pBean.listManyPost().size();i++){
					if(pBean.listManyPost().get(i).getStatus() != 0){
			
      out.write("\t\r\n");
      out.write("\t\t\t<li class=\"art-list-item\">\r\n");
      out.write("\t\t\t\t<div class=\"art-list-item-title-and-time\">\r\n");
      out.write("\t\t\t\t\t<h2 class=\"art-list-title\"><a href=\"show_post.jsp?id=");
 out.println(pBean.listManyPost().get(i).getId()); 
      out.write('"');
      out.write('>');
 out.println(pBean.listManyPost().get(i).getJudul()); 
      out.write("</a></h2>\r\n");
      out.write("\t\t\t\t\t<div class=\"art-list-time\">");
 out.println(pBean.listManyPost().get(i).getTanggal()); 
      out.write("</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"art-list-owner\">Owner:&nbsp;");
 out.println(pBean.listManyPost().get(i).getOwner()); 
      out.write("</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<p>");
 out.println(pBean.listManyPost().get(i).getKonten()); 
      out.write("</p>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t");
 if (typeC!=4){
				
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href=\"edit.jsp?id=");
 out.println(pBean.listManyPost().get(i).getId()); 
      out.write("\">Edit</a> | <a href=\"#\" onclick=\"return ConfirmDelete(");
 out.println(pBean.listManyPost().get(i).getId()); 
      out.write(");\">Hapus</a>\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</li>\t\r\n");
      out.write("\t\t\t");

					}
				}
			
      out.write("\r\n");
      out.write("          </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("    function ConfirmDelete(nomor)\r\n");
      out.write("    {\r\n");
      out.write("      var x = confirm(\"Apakah Anda yakin menghapus post ini?\");\r\n");
      out.write("      if (x)\r\n");
      out.write("          return window.location.assign('delete.jsp?id='+nomor);\r\n");
      out.write("      else\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
