package entities;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import controller.DatabaseUtility;

@ManagedBean(name = "userData", eager = true)
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

	public String login() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		details = dbUtil.findUser(username, password);
		if (details != null) {
			loggedIn = true;
			HttpSession session = Util.getSession();
			session.setAttribute("username", username);
			session.setAttribute("userid", details.getUserId());
			return ("index?faces-redirect=true");
		}

		return null;
	}
	
	public String logout(){
		HttpSession session = Util.getSession();
		session.invalidate();
		return ("index?faces-redirect=true");
	}

	public void check() {
		if (isLoggedIn()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("index.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
