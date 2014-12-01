/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import java.util.Date;


/**
 *
 * @author Yusuf
 */
public class BlogPost {

    private int pid;
    private int uid;
    private String username;
    private String posttitle;
    private String postcontent;
    private String postdate;
    private boolean ispublished;
    private boolean isdeleted;

    public BlogPost() {
        pid = 0;
        uid = 0;
        posttitle = "";
        postcontent = "";
        username = "";
        ispublished = false;
        isdeleted = false;
        Date date = new Date();
        postdate = String.valueOf(date.getMonth()+1)+"/"+String.valueOf(date.getDate())+"/"+String.valueOf(date.getYear()+1900);
    }
    
    public void reset(){
        pid = 0;
        uid = 0;
        posttitle = "";
        postcontent = "";
        username = "";
        ispublished = false;
        isdeleted = false;
        Date date = new Date();
        postdate = String.valueOf(date.getMonth()+1)+"/"+String.valueOf(date.getDate())+"/"+String.valueOf(date.getYear()+1900);
    }

    public int getPid() {
        return pid;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public String getPostcontent() {
        return postcontent;
    }

    public String getPostContentWithLimit(int limit) {
        String content = postcontent;
        if (content.length() > limit) {
            content = postcontent.substring(0, limit);
        }
        return content;
    }

    public String getPostdate() {
        return postdate;
    }

    public boolean getPublished() {
        return ispublished;
    }

    public boolean getDeleted() {
        return isdeleted;
    }

    public int publishedToInt(){
        int ret = 0;
        if(ispublished)
            ret = 1;
        return ret;
    }
    
    public int deletedToInt(){
        int ret = 0;
        if(isdeleted)
            ret = 1;
        return ret;
    }

    public void setPid(int Pid) {
        pid = Pid;
    }

    public void setUid(int userID) {
        uid = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPosttitle(String PostTitle) {
        posttitle = PostTitle;
    }

    public void setPostcontent(String PostContent) {
        postcontent = PostContent;
    }

    /**
     * 
     * @param PostDate date format : mm/dd/yyyy
     */
    public void setPostdate(String PostDate) {
        postdate = PostDate;
    }


    public void setPublished(boolean Published) {
        ispublished = Published;
    }

  

    public void setDeleted(boolean Deleted) {
        isdeleted = Deleted;
    }

    
}
