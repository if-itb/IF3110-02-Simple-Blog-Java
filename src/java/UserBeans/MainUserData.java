/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;


import java.util.ArrayList;

/**
 *
 * @author Yusuf
 */
public class MainUserData {
    public static void main(String[] args){
        /*
        UserData ud = new UserData();
        User user = ud.getUser("ucup");
        System.out.println("nama : "+user.getFullname());
        System.out.println("user : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        */
       
        /*
        userMgnt um = new userMgnt();
        BlogPost post = new BlogPost();
        post.setPostcontent("lalala2");
        post.setPosttitle("dadada2");
        post.setPostdate("12/01/2014");
        post.setUid(2);
        um.setPost(post);
        um.submitPost();
                */
        
        userMgnt um = new userMgnt();
        um.publishPost(9);
    }
}
