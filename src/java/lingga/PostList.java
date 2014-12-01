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

@ManagedBean(name="postlist",eager=true)
@SessionScoped
public class PostList {
    private ArrayList <Post> listPos;
    private ArrayList <Post> unlistPos;
    private ArrayList <Post> trashPos;
    
    public void initPostList(DBConnector dbc){
        listPos = new ArrayList<Post>(dbc.listPost("true"));
	unlistPos = new ArrayList<Post>(dbc.listPost("false"));
	trashPos = new ArrayList<Post>(dbc.listPost("deleted"));
    }

    public Post getPost(int n){
        return listPos.get(n);
    }

    public ArrayList<Post> getListPos(User us) {
	return listPos;
    }
    
    public ArrayList<Post> getUnlistPos(User us) {
	if(us.getType()==2 || us.getType()==3){
	    return unlistPos;
	}
	else{
	    return null;
	}
    }
    
    public ArrayList<Post> getTrashPos(User us) {
	if(us.getType()==3){
	    return trashPos;
	}
	else{
	    return null;
	}
    }
}
