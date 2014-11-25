//UserDao.java(which interacts with the database to get the results)


package ExamplePackage;
import java.sql.*;

public class UserDAO    
{
   static Connection currentCon = null;
   static ResultSet rs = null;     
   public static UserBean login(UserBean bean) {
   
      //preparing some objects for connection 
      Statement stmt = null;    
   
      String username = bean.getUsername();    
      String password = bean.getPassword();   
       
      String searchQuery =
            "select * from users where username='"
                     + username
                     + "' AND password='"
                     + password
                     + "'";
       
   // "System.out.println" prints in the console; Normally used to trace the process
   System.out.println("Your username is " + username);          
   System.out.println("Your password is " + password);
   System.out.println("Query: "+searchQuery);
       
   try 
   {
      //connect to DB 
      currentCon = ConnectionManager.getConnection();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);
      boolean more = rs.next();
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Sorry, you are not registered");
         bean.setValid(false);
      } 
           
      //if user exists set the isValid variable to true
      else if (more) 
      {
         String username = rs.getString("Username");
           
         System.out.println("Welcome " + username);
         bean.setUsername(username);
         bean.setValid(true);
		//bean.setRole(role);
      }
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In failed" + ex);
   }
      
   //some exception handling
   finally 
   {
      if (rs != null)   {
         try {
            rs.close();
         } catch (Exception e) {}
            rs = null;
       }
      if (stmt != null) {
         try {
            stmt.close();
         } catch (Exception e) {}
            stmt = null;
      }
   
      if (currentCon != null) {
         try {
            currentCon.close();
         } catch (Exception e) {}
         currentCon = null;
      }
   }

return bean;
   
 }  

}