/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Bagaskara Pramudita
 */
@ManagedBean(name="message", eager=true)
@RequestScoped
public class Message {
    private String message;
    
    /**
     * Creates a new instance of Message
     */
    public Message() {
        message="Hello World";
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String input){
        message = input;
    }
}
