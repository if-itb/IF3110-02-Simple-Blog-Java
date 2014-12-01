/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author A46CB
 */
public class edit_post extends HttpServlet {

  private String databaseURL, username, password;
private static final String DATA_DIRECTORY = "assets/image";
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Retrieve the database-URL, username, password from webapp init parameters
        super.init(config);
        ServletContext context = config.getServletContext();
        databaseURL = "jdbc:mysql://localhost:3306/simpleblog";
        username = "root";
        password = "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String judul = null;
        String tanggal = null;
        String konten = null;
        String imagefilename = "-";
        String id = null;
         PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        String comment = "";
        try {
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e) {
                    out.println("Unable to load Driver");
                }
             

            conn = DriverManager.getConnection(databaseURL, username, password);
            stmt = conn.createStatement();
             
            String output = "";
            String UPLOAD_DIRECTORY = "/uploads";
             String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> multiparts = new ServletFileUpload(
                                             new DiskFileItemFactory()).parseRequest(request);
                    for(FileItem item : multiparts){
                        if(!item.isFormField()){
                            if (!item.getName().equals("")) {
                                imagefilename = new File(item.getName()).getName();
                                item.write( new File(uploadFolder + File.separator + imagefilename));
                                output = UPLOAD_DIRECTORY + "/" + imagefilename;
                            }
                        } else {
                            if (item.getFieldName().equals("id")) {
                                id = item.getString();
                            }
                            if (item.getFieldName().equals("Judul")) {
                                judul = item.getString();
                            }
                            if (item.getFieldName().equals("Tanggal")) {
                                tanggal = item.getString();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tanggal);
                                tanggal = dateFormat.format(date);
                            }
                            if (item.getFieldName().equals("Konten")) {
                                konten = item.getString();
                            }
                        }
                    } 
                    
                    String sqlStr = "UPDATE post SET judul='"+judul+"', tanggal='"+tanggal+"', konten='"+konten+"', image='"+imagefilename+"' WHERE id='"+id+"'";
                    if (imagefilename.equals("-")) {
                        sqlStr = "UPDATE post SET judul='"+judul+"', tanggal='"+tanggal+"', konten='"+konten+"' WHERE id='"+id+"'";
                    }
                    stmt.executeUpdate(sqlStr); // Send the query to the server
                    response.sendRedirect("post.jsp?id="+id);
                    
                } catch (Exception ex) {
                   ex.printStackTrace();
                }         
                
            } else {
                response.sendRedirect("new_post_image.jsp");
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Unable to connect to database");
        } finally {
            out.close();
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
