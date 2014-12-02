//ConnectionManager.java(to establish a connection to database)


package ExamplePackage;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionManager {

   static Connection con;
   static String url;
         
   public static Connection getConnection()
   {
     
      try
      {
         String url ="jdbc:oracle:thin:@localhost:1521:XE"; 

         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         try
         {               
            con = DriverManager.getConnection(url,"*******","*******"); 
                                     
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}
