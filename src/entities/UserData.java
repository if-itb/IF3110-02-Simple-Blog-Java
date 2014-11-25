package entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import controller.DatabaseUtility;

@ManagedBean
@SessionScoped
public class UserData implements Serializable {

	private static final long serialVersionUID = -8430435915513518517L;
	private String username, password;
	private boolean loggedIn = false;
	private UserDetails details;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getDetails() {
		return details;
	}

	public String getUserHeader() {
		return ("header.xhtml");
	}

	public String login() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		details = dbUtil.findUser(username, password);
		if (details != null) {
			loggedIn = true;
			return ("index?faces-redirect=true");
		}

		return null;
	}

	public void check(int p, String page) {
		int now = 1;
		if (details != null)
			now <<= (details.getRole() / 10);

		if ((now & p) == 0) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(page);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getLoginLink() {
		if (!loggedIn) {
			return "<a href=\"login.jsf\"><button type=\"button\" class=\"btn btn-warning\">Login</button> </a>";
		} else {
			return "<a href=\"logout.jsf\"><button type=\"button\" class=\"btn btn-warning\">Logout</button> </a>";
		}
	}

	public String logout() {

		loggedIn = false;
		return "index?faces-redirect=true";
	}

	public List<NavigationMenu> getUserMenu() {
		List<NavigationMenu> result = new ArrayList<NavigationMenu>();
		if (loggedIn == false) {
			return result;
		} else if (details.getRole() == 10) {
			result.add(new NavigationMenu("Home", "index.jsf"));
			result.add(new NavigationMenu("Add Post", "add_post.jsf"));
			result.add(new NavigationMenu("My Post", ""));
			return result;
		} else if (details.getRole() == 20) {
			result.add(new NavigationMenu("Home", "index.jsf"));
			result.add(new NavigationMenu("Editor Menu", ""));
			return result;
		} else if (details.getRole() == 30) {
			result.add(new NavigationMenu("Home", "index.jsf"));
			result.add(new NavigationMenu("Add Post", "add_post.jsf"));
			result.add(new NavigationMenu("Post Manager", ""));
			result.add(new NavigationMenu("User Manager", "crud.jsf"));
			return result;
		} else {
			return result;
		}
	}
}
