package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import User.userPaket;
import Post.Post;
import User.User;
import Post.PostBean;

public final class show_005fpost_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Simple Blog of Bangsatya</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body class=\"default\">\n");
      out.write("<div class=\"wrapper\">\n");
      out.write("<nav class=\"nav\">\n");
      out.write("    <a style=\"border:none;\" id=\"logo\" href=\"index.php\"><h1>Simple<span>-</span>Blog</h1></a>\n");
      out.write("    <ul class=\"nav-primary\">\n");
      out.write("        <li><a href=\"new_post.html\">+ Tambah Post</a></li>\n");
      out.write("    </ul>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<div id=\"home\">\n");
      out.write("    <div class=\"posts\">\n");
      out.write("        <nav class=\"art-list\">\n");
      out.write("          <ul class=\"art-list-body\">\n");
      out.write("\t\t\t");

				PostBean pBean =  new PostBean();
				pBean.ViewPost(Integer.parseInt(request.getParameter("id")));
				
			
      out.write("\n");
      out.write("\t\t\t\t\t<center>\n");
      out.write("\t\t\t\t\t<table border=1 style=\"align:center\" >\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\" align=\"center\">\n");
      out.write("\t\t\t\t\t\t\t\t<h4>");
 out.println(pBean.getJudul()); 
      out.write("</h4>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t");
 out.println(pBean.getTanggal()); 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\n");
      out.write("\t\t\t\t\t\t\t\t<p>");
 out.println(pBean.getKonten()); 
      out.write("</p>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\n");
      out.write("\t\t\t\t\t\t\t  <a href=\"edit_post.jsp?id='");
 out.println(pBean.getId()); 
      out.write("'\">Edit</a> | <a href=\"delete.jsp?id='");
 out.println(pBean.getId()); 
      out.write("'\">Hapus</a>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\n");
      out.write("\t\t\t\t\t\t\t\t<hr/>\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"Komentar\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<hr/>\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"formKomentar\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<form method=\"post\" action=\"#\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\tNama <input type=\"text\" id=\"pNama\" name=\"nama\"><br/>\n");
      out.write("\t\t\t\t\t\t\t\t\t\tEmail <input type=\"text\" id=\"pEmail\" name=\"email\" ><br/>\n");
      out.write("\t\t\t\t\t\t\t\t\t\tPesan<br/>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<textarea id=\"pPesan\" name=\"pesan\" cols=\"84\" rows=\"5\"></textarea><br/>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"pTanggal\" name=\"tanggal\" value=\"'.date(\"Y-m-d\").'\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"pId\" name=\"id\" value=\"'");
 out.println(pBean.getId()); 
      out.write("'\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"button\" name=\"postKomentar\" value=\"Post Komentar\" onclick=\"return cekEmail();\">\n");
      out.write("\t\t\t\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t</table>\n");
      out.write("\n");
      out.write("\t\t\t\t</center>\n");
      out.write("\t\t\t\t<div id=\"terbaru\" align=\"center\">\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<hr/>\n");
      out.write("\t\t\t\t<!-- bagian komentar -->\n");
      out.write("\t\t\t\t\t<div id=\"unit-komentar\" align=\"center\">\n");
      out.write("\t\t\t\t\t\t<br/>\n");
      out.write("\t\t\t\t\t\t<br/>\n");
      out.write("\t\t\t\t\t\t</br>\n");
      out.write("\t\t\t\t\t\t<hr/>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("          </ul>\n");
      out.write("        </nav>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
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
      out.write("function cekEmail() {\n");
      out.write("\tvar x = document.getElementById(\"pEmail\").value;\n");
      out.write("    var atpos = x.indexOf(\"@\");\n");
      out.write("    var dotpos = x.lastIndexOf(\".\");\n");
      out.write("    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {\n");
      out.write("        alert(\"Email address salah\");\n");
      out.write("        return false;\n");
      out.write("    }else{\n");
      out.write("\t\tPostKomentar();\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write("function PostKomentar(){\n");
      out.write("\tvar isinama = document.getElementById(\"pNama\").value;\n");
      out.write("\tvar isiemail = document.getElementById(\"pEmail\").value;\n");
      out.write("\tvar isipesan = document.getElementById(\"pPesan\").value;\n");
      out.write("\tvar isiid = document.getElementById(\"pId\").value;\n");
      out.write("\tvar isitanggal\t=\tdocument.getElementById(\"pTanggal\").value;\n");
      out.write("\tvar xmlhttp=GetXmlHttpObject();\n");
      out.write("\tif(xmlhttp==null){\n");
      out.write("\t\talert(\"Silahkan gunakan browser yang mendukung AJAX\");\n");
      out.write("\t\treturn;\n");
      out.write("\t}\t\n");
      out.write("\tvar url\t=\t\"post_komentar.jsp\";\n");
      out.write("    var param=\"id=\"+isiid+\"&Name=\"+isinama+\"&Email=\"+isiemail+\"&Content=\"+isipesan;\n");
      out.write("\tdocument.getElementById(\"terbaru\").innerHTML = \"Sedang memproses komentar\";\n");
      out.write("\tvar message = \"<div id=unit-komentar align=center><br>\" + isinama + \"<br>\" + isitanggal + \"<br>\" + isipesan + \"<hr></div>\";\n");
      out.write("\tdocument.getElementById(\"terbaru\").innerHTML = message;\n");
      out.write("    xmlhttp.open(\"POST\",url,true);\n");
      out.write("    xmlhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n");
      out.write("    xmlhttp.setRequestHeader(\"Content-length\", param.length);\n");
      out.write("    xmlhttp.setRequestHeader(\"Connection\", \"close\");\n");
      out.write("    xmlhttp.send(param);\n");
      out.write("}\n");
      out.write("\n");
      out.write("function GetXmlHttpObject() {\n");
      out.write("    var xmlhttp=null;\n");
      out.write("    try {\n");
      out.write("        // Firefox, Opera 8.0+, Safari\n");
      out.write("        xmlhttp=new XMLHttpRequest();\n");
      out.write("    }\n");
      out.write("    catch (e) {\n");
      out.write("        // Internet Explorer\n");
      out.write("        try {\n");
      out.write("            xmlhttp=new ActiveXObject(\"Msxml2.XMLHTTP\");\n");
      out.write("        }\n");
      out.write("        catch (e) {\n");
      out.write("            xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("    return xmlhttp;\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("<script type=\"text/javascript\" src=\"assets/js/fittext.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"assets/js/app.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"assets/js/respond.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("  var ga_ua = '{{! TODO: ADD GOOGLE ANALYTICS UA HERE }}';\n");
      out.write("\n");
      out.write("  (function(g,h,o,s,t,z){g.GoogleAnalyticsObject=s;g[s]||(g[s]=\n");
      out.write("      function(){(g[s].q=g[s].q||[]).push(arguments)});g[s].s=+new Date;\n");
      out.write("      t=h.createElement(o);z=h.getElementsByTagName(o)[0];\n");
      out.write("      t.src='//www.google-analytics.com/analytics.js';\n");
      out.write("      z.parentNode.insertBefore(t,z)}(window,document,'script','ga'));\n");
      out.write("      ga('create',ga_ua);ga('send','pageview');\n");
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
