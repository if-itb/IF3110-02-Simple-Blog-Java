/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Afik
 */
public class AllPostBean {
    private ArrayList <Post> publishedPost; 
    private ArrayList<Post> unPublishedPost;
    /**
     * Creates a new instance of AllPostBean
     */
    public AllPostBean() {
    }

    /**
     * @return the allPost
     */
    public ArrayList <Post> getPublishedPost() {
        publishedPost = new ArrayList<Post> (DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO().listPublish());
        return publishedPost;
    }

    /**
     * @param publishedPost the publishedPost to set
     */
    public void setPublishedPost(ArrayList <Post> publishedPost) {
        this.publishedPost = publishedPost;
    }

    /**
     * @return the unPublishedPost
     */
    public ArrayList<Post> getUnPublishedPost() {
        unPublishedPost = new ArrayList<Post> (DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO().listUnPublish());
        return unPublishedPost;
    }

    /**
     * @param unPublishedPost the unPublishedPost to set
     */
    public void setUnPublishedPost(ArrayList<Post> unPublishedPost) {
        this.unPublishedPost = unPublishedPost;
    }
}
