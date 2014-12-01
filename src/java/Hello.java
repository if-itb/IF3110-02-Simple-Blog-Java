/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Bagaskara Pramudita
 */
@ManagedBean(name="hello", eager=true)
@RequestScoped
public class Hello {
    /**
     * Creates a new instance of Hello
     */
    
    @ManagedProperty(value="#{message}")
    private Message messageBean;
    
    private String message;
    
    public Hello() {
        System.out.println("hello started");
    }
    public String getMessage(){
        if (messageBean!=null){
            return messageBean.getMessage();
        }
        return message;
    }
    
    public void setMessageBean(Message input){
        messageBean = input;
    }
}
