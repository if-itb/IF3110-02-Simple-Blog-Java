/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Luthfi Hamid Masykuri
 */
public class Comment {
    private String name;
    private String email;
    private String content;
    private Timestamp time;
    
    public Comment(String name,String email,String content,Timestamp time)
    {
        this.name = name;
        this.email = email;
        this.content = content;
        this.time = time;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public Timestamp getTime()
    {
        return time;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public void setTime(Timestamp time)
    {
        this.time = time;
    }
}
