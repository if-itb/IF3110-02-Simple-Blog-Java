/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Luthfi Hamid Masykuri
 */
public class Post {
    private int id;
    private String title;
    private Date date;
    private String content;
    private User author;
    
    public Post(int id,String title,Date date,String content,User author)
    {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.author = author;
    }
    
    public int getID()
    {
        return id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public User getAuthor()
    {
        return author;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public void setAuthor(User author)
    {
        this.author = author;
    }
    
    public static void main(String args[]) {
       // Instantiate a Date object
       int year = 2012;
       int month = 12;
       int day = 21;
       Date date = new Date(year-1900,month-1,day);
        
       // display time and date using toString()
       System.out.println(date.toString());
   }
}
