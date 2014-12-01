/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Bagaskara Pramudita
 */
@ManagedBean(name="navigationController")
@RequestScoped
public class NavigationController implements Serializable{

    @ManagedProperty(value="#{param.pageId}")
    private String pageId;

    private List<String> posts;
    
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }
    
    /** 
     * Creates a new instance of NewJSFManagedBean
     */
    public NavigationController() {
        posts = new ArrayList<>();
        posts.add("lalala");
    }
    
    public String showPage(){
        if(pageId==null){
            return "home";
        }
        if(pageId.equals("1")){
            return "post";
        }
        else{
            return posts.get(0);
        }
    }    
}
