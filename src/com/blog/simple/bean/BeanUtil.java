package com.blog.simple.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BeanUtil {
	public static HttpSession getSession() {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
}
