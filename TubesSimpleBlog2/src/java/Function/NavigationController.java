/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
/**
 *
 * @author Anggi
 */
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable{
	//this managed property will read value from request parameter pageId
   @ManagedProperty(value="#{param.idpost}")
   private String postId;

   //condional navigation based on pageId
   //if pageId is 1 show page1.xhtml,
   //if pageId is 2 show page2.xhtml
   //else show home.xhtml
   public String showPage(){
		//ViewPost view = new ViewPost();
		//view.connectDb(postId);
		return "usermanagement";
   }
   public void setPostId(String postId){
	   this.postId = postId;
   }
   public String getPostId(){
	   return postId;
   }
}
