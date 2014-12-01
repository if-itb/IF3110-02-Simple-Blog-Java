package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import User.userPaket;
import Post.Post;
import User.User;
import Post.PostBean;

public final class new_005fpost_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n");
      out.write("<meta name=\"description\" content=\"Simple Blog\">\n");
      out.write("<meta name=\"author\" content=\"Bangsatya\">\n");
      out.write("\n");
      out.write("<!-- Twitter Card -->\n");
      out.write("<meta name=\"twitter:card\" content=\"summary\">\n");
      out.write("<meta name=\"twitter:site\" content=\"omfgitsasalmon\">\n");
      out.write("<meta name=\"twitter:title\" content=\"Simple Blog\">\n");
      out.write("<meta name=\"twitter:description\" content=\"Simple Blog\">\n");
      out.write("<meta name=\"twitter:creator\" content=\"Bangsatya Blog\">\n");
      out.write("<meta name=\"twitter:image:src\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">\n");
      out.write("\n");
      out.write("<meta property=\"og:type\" content=\"article\">\n");
      out.write("<meta property=\"og:title\" content=\"Simple Blog\">\n");
      out.write("<meta property=\"og:description\" content=\"Deskripsi Blog\">\n");
      out.write("<meta property=\"og:image\" content=\"{{! TODO: ADD GRAVATAR URL HERE }}\">\n");
      out.write("<meta property=\"og:site_name\" content=\"Simple Blog\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/screen.css\" />\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"img/favicon.ico\">\n");
      out.write("\n");
      out.write("<!--[if lt IE 9]>\n");
      out.write("    <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("<title>Simple Blog of Bangsatya | New Post</title>\n");
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body class=\"default\">\n");
      out.write("<div class=\"wrapper\">\n");
      out.write("\n");
      out.write("<nav class=\"nav\" >\n");
      out.write("    <a style=\"border:none;\" id=\"logo\" href=\"index.jsp\"><h1>Simple<span>-</span>Blog</h1></a>\n");
      out.write("    <ul class=\"nav-primary\">\n");
      out.write("        <li><a href=\"edit_post.jsp\">+ New Post</a></li>\n");
      out.write("    </ul>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<article class=\"art simple post\" style=\"margin-top:50px;\">\n");
      out.write("        <div class=\"art-body\" style=\"margin-top:70px\">\n");
      out.write("        <div class=\"art-body-inner\">\n");
      out.write("            <h2>New Post</h2>\n");
      out.write("            <div id=\"contact-area\">\n");
      out.write("                <form method=\"post\" action=\"handler/newPost.jsp\">\n");
      out.write("                    <label for=\"Judul\">Judul</label>\n");
      out.write("                    <input type=\"text\" name=\"judul\" id=\"Judul\" value=\"\">\n");
      out.write("\t\t\n");
      out.write("                    <label for=\"Tanggal\">Tanggal:</label>\n");
      out.write("                    <input type=\"text\" name=\"tanggal\" id=\"Tanggal\" value=\"\">\n");
      out.write("                    <input type=\"hidden\" name=\"owner\" id=\"owner\" value=\"aryya\">\n");
      out.write("                    \n");
      out.write("                    <label for=\"Konten\">Konten:</label><br>\n");
      out.write("                    <textarea name=\"konten\" rows=\"20\" cols=\"20\" id=\"Konten\"></textarea>\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"nomor\" value=\"\">\n");
      out.write("                    <input type=\"submit\" name=\"submit\" value=\"Post\" class=\"submit-button\" onclick=\"return cekInputan();\">\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</article>\n");
      out.write("\n");
      out.write("<footer class=\"footer\">\n");
      out.write("    <div class=\"back-to-top\"><a href=\"\">Back to top</a></div>\n");
      out.write("    <!-- <div class=\"footer-nav\"><p></p></div> -->\n");
      out.write("    <div class=\"psi\">&Psi;</div>\n");
      out.write("    <aside class=\"offsite-links\">\n");
      out.write("        Asisten IF3110 /\n");
      out.write("        <a class=\"rss-link\" href=\"rss/rss.php\">RSS</a> /\n");
      out.write("        <br>\n");
      out.write("        <a class=\"twitter-link\" href=\"http://twitter.com/YoGiiSinaga\">Yogi</a> /\n");
      out.write("        <a class=\"twitter-link\" href=\"http://twitter.com/sonnylazuardi\">Sonny</a> /\n");
      out.write("        <a class=\"twitter-link\" href=\"http://twitter.com/fathanpranaya\">Fathan</a> /\n");
      out.write("        <br>\n");
      out.write("        <a class=\"twitter-link\" href=\"#\">Renusa</a> /\n");
      out.write("        <a class=\"twitter-link\" href=\"#\">Kelvin</a> /\n");
      out.write("        <a class=\"twitter-link\" href=\"#\">Yanuar</a> /\n");
      out.write("        \n");
      out.write("    </aside>\n");
      out.write("</footer>\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("    var today = new Date();\n");
      out.write("    var dd = today.getDate();\n");
      out.write("    var mm = today.getMonth()+1; //January is 0!\n");
      out.write("    var yyyy = today.getFullYear();\n");
      out.write("    if(dd<10) {\n");
      out.write("        dd='0'+dd\n");
      out.write("    } \n");
      out.write("    if(mm<10) {\n");
      out.write("        mm='0'+mm\n");
      out.write("    } \n");
      out.write("    document.getElementById(\"Tanggal\").value= yyyy + \"-\" + mm + \"-\" + dd;\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
