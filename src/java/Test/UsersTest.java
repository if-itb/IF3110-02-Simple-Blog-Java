/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import Database.DatabaseAccess;
import Model.User;
import Model.Users;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinyu
 */
public class UsersTest {
    
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("KevinYudi");
        user.setPassword("Password");
        user.setRole("owner");
        user.setEmail("kevin.kayu@gmail.com");
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Users.getInstance().addUser(user);
        
        User user1 = new User();
        user1.setUsername("winson");
        //Users.getInstance().deleteUserByUsername(user1);

        //User user2 = Users.getInstance().findUser(5);
        //System.out.println("Nama : "+user2.getUsername());
        
        /*User user3 = new User();
        user3.setId(5);
        user3.setUsername("Eric");
        user3.setPassword("teric");
        Users.getInstance().updateUser(user3);*/
        
        User user4 = new User();
        user4.setId(3);
        Users.getInstance().deleteUserByUserId(user4);
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
