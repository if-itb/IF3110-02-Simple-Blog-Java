package somepackage;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UserData implements Serializable {
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
		DatabaseUtility DBConn = DatabaseUtility.getInstance();

		details = DBConn.findUser(username, password);
		if (details != null) {
			loggedIn = true;
			return ("index?faces-redirect=true");
		}

		return null;
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
