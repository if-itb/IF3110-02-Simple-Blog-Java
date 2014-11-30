package entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constrain.Constant;
import controller.DatabaseUtility;

@ManagedBean
@SessionScoped
public class UserData implements Serializable {

	private static final long serialVersionUID = -8430435915513518517L;
	private String username, password;
	private boolean loggedIn = false;
	private UserDetails details;

	public void check(int p, String page) {
		int now = 1;
		if (details != null) {
			if (!loggedIn)
				System.err.println("when details != null, loggedIn should be = true");
			now <<= (details.getRole() / 10);
		}

		if ((now & p) == 0) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(page);
			} catch (IOException e) {
				System.err.println("OMG THIS IS THE MOST FATAL ERROR EVER");
				e.printStackTrace();
			}
		}
	}

	public void checkCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		HttpServletRequest servletRequest = (HttpServletRequest) (fc
				.getExternalContext().getRequest());
		Cookie cookies[] = servletRequest.getCookies();

		if (cookies != null && !isLoggedIn()) {
			for (int i = 0; i < cookies.length; i++) {
				String cookieName = cookies[i].getName();
				String cookieValue = cookies[i].getValue();

				if (cookieName.equals("username"))
					setUsername(cookieValue);

				if (cookieName.equals("password"))
					setPassword(cookieValue);
			}

			login();
		}
	}

	public UserDetails getDetails() {
		return details;
	}

	public String getEmail() {
		if (isLoggedIn())
			return details.getEmail();
		else
			return "";
	}

	public String getName() {
		if (isLoggedIn())
			return details.getName();
		else
			return "";
	}

	public String getLoginLink() {
		if (!isLoggedIn()) {
			return "<a href=\"login.jsf\"><button type=\"button\" class=\"btn btn-warning\">Login</button> </a>";
		} else {
			return "<a href=\"logout.jsf\"><button type=\"button\" class=\"btn btn-warning\">Logout</button> </a>";
		}
	}

	public String getPassword() {
		return password;
	}

	public String getUserHeader() {
		return ("header.xhtml");
	}
	
	public int getUserID() {
		if (isLoggedIn())
			return details.getUserId();
		else
			return -1;
	}

	public List<NavigationMenu> getUserMenu() {
		List<NavigationMenu> result = new ArrayList<NavigationMenu>();
		if (loggedIn == false) {
			result.add(new NavigationMenu(Constant.MENU_HOME_NAME,
					Constant.MENU_HOME_URL));
			return result;
		} else if (details.getRole() == 10) {
			result.add(new NavigationMenu(Constant.MENU_HOME_NAME,
					Constant.MENU_HOME_URL));
			result.add(new NavigationMenu(Constant.MENU_ADDPOST_NAME,
					Constant.MENU_ADDPOST_URL));
			result.add(new NavigationMenu(Constant.MENU_MYPOST_NAME,
					Constant.MENU_MYPOST_URL));
			return result;
		} else if (details.getRole() == 20) {
			result.add(new NavigationMenu(Constant.MENU_HOME_NAME,
					Constant.MENU_HOME_URL));
			result.add(new NavigationMenu(Constant.MENU_EDITOR_NAME,
					Constant.MENU_EDITOR_URL));
			return result;
		} else if (details.getRole() == 30) {
			result.add(new NavigationMenu(Constant.MENU_HOME_NAME,
					Constant.MENU_HOME_URL));
			result.add(new NavigationMenu(Constant.MENU_ADDPOST_NAME,
					Constant.MENU_ADDPOST_URL));
			result.add(new NavigationMenu(Constant.MENU_POSTMANAGER_NAME,
					Constant.MENU_POSTMANAGER_URL));
			result.add(new NavigationMenu(Constant.MENU_USERMANAGER_NAME,
					Constant.MENU_USERMANAGER_URL));
			return result;
		} else {
			return result;
		}
	}

	public String getUsername() {
		return username;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String login() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		details = dbUtil.findUser(username, password);
		if (details != null) {
			System.out.println("Login, Username and Password Found");
			loggedIn = true;
			loginCookie();
			return ("index?faces-redirect=true");
		}

		return null;
	}

	private void loginCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		Cookie cookieUser = new Cookie("username", username);
		Cookie cookiePassword = new Cookie("password", password);

		cookieUser.setMaxAge(Constant.COOKIE_MAX_AGE);
		cookiePassword.setMaxAge(Constant.COOKIE_MAX_AGE);

		HttpServletResponse servletResponse = (HttpServletResponse) (fc
				.getExternalContext().getResponse());
		servletResponse.addCookie(cookieUser);
		servletResponse.addCookie(cookiePassword);
	}

	public String logout() {
		loggedIn = false;
		setUsername(null);
		setPassword(null);
		logoutCookie();
		System.out.println("Logout Done");
		return "index?faces-redirect=true";
	}

	private void logoutCookie() {
		FacesContext fc = FacesContext.getCurrentInstance();

		Cookie cookieUser = new Cookie("username", null);
		Cookie cookiePassword = new Cookie("password", null);

		cookieUser.setMaxAge(0);
		cookiePassword.setMaxAge(0);

		HttpServletResponse servletResponse = (HttpServletResponse) (fc
				.getExternalContext().getResponse());
		servletResponse.addCookie(cookieUser);
		servletResponse.addCookie(cookiePassword);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
