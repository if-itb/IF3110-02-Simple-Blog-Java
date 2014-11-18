/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Rikysamuel
 */

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class navigationController {
    
   @ManagedProperty(value="#{param.pageId}")
   private String pageId;

    public String moveToAddPost(){
       return "AddPost";
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
   
}
