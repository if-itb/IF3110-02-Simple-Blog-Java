/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author user
 */
public class User {
    private int user_id;
    private String username;
    private String password;
    private int user_type;
    User(){}
    public void setUserID(int value){
        user_id = value;
    }
    public void setUserName(String value){
        username = value;
    }
    public void setPassword(String value){
        password = value;
    }
    public void setUserType(int value){
        user_type = value;
    }
    public int setUserID(){
        return user_id;
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getUserType(){
        return user_type;
    }
}
