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
@ManagedBean(name="inputHandler")
@RequestScoped
public class InputHandler {
    @ManagedProperty(value="#{param.inputText}")
    private String inputText;

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }
    /**
     * Creates a new instance of InputHandler
     */
    public InputHandler() {
        inputText="default"; 
    }    
}
