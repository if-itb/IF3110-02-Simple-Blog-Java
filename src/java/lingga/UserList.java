/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lingga;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jelink
 */

@ManagedBean(name="userlist",eager=true)
@SessionScoped
public class UserList {
    private ArrayList <User> listUser;
    
    public void initUserList(User pengguna, DBConnector dbc){
        listUser = new ArrayList<User>(dbc.listUser(pengguna));
    }

    public User getUser(int n){
        return listUser.get(n);
    }

    public ArrayList<User> getListUser(User us) {
	if(us.getType()==3){
	    return listUser;
	}
	else{
	    return null;
	}
    }
}
