/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

/**
 *
 * @author Kevin Huang
 */
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
/**
 *
 * @author Kevin Huang
 */
@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean{

    /**
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
        public PostingDatabase a;
        private final List<User> ListUser;
        
        public UserBean() throws ClassNotFoundException, SQLException{
            this.ListUser = a.getUsers();
        }
        
        public List<User> getOrderList() {
		return ListUser;
	}
        
	public String saveAction() {
 
		//get all existing value but set "editable" to false 
		for (User user : ListUser){
			user.setEditable(false);
		}
		//return to current page
		return null;
 
	}
 
	public String editAction(User user) {
 
		user.setEditable(true);
		return null;
	}
}