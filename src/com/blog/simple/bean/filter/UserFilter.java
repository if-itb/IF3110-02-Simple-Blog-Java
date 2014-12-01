package com.blog.simple.bean.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.simple.model.User.Role;

/**
 * Filter for limiting user access on the website
 * @author Alvin Natawiguna
 *
 */
@WebFilter(filterName = "UserFilter", urlPatterns = {"*.xhtml"})
public class UserFilter implements Filter {
	//TODO: need to fix these URLs, match with the others
	private static final String adminURL[] = {
		"userList.xhtml"
	};
	
	private static final String ownerURL[] = {
		"newPost.xhtml"
	};
	
	private static final String editorURL[] = {
		"post.xhtml?action=edit",
		"post.xhtml?action=publish"
	};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String requestUri = req.getRequestURI();
		boolean ok;
		if(session != null) {
			// user is logged in; do filtering based on Role
			if(session.getAttribute("userRole") != null) {
				Role role = Role.getRole((int) session.getAttribute("userRole"));
				
				switch(role) {
				case ADMIN:
				case OWNER:
				case EDITOR:
					ok = checkURI(role, requestUri);
					break;
				default:
					throw new ServletException("Unknown user role in session");
				}
			} else {
				ok = checkURI(null, requestUri);
			}
		} else {
			// user is not logged in; assume that the user is a guest
			ok = checkURI(null, requestUri);
		}
		
		if(ok) {
			chain.doFilter(request, response);
		} else {
			// redirect to error page
			// TODO: define the error page
			resp.sendRedirect("unauthorized.xhtml");
		}
	}
	
	private boolean checkURI(Role role, String requestURI) {
		boolean ret = true;
		
		if(role != null) {
			switch(role) {
			case ADMIN:
				// no filtering required
				break;
			case OWNER:
				ret = filterAdmin(requestURI);
				break;
			case EDITOR:
				ret = filterAdmin(requestURI);
				if(ret) {
					ret = filterOwner(requestURI);
				}
				break;
			}
		} else { // guest!
			ret = filterAdmin(requestURI);
			if(ret) {
				ret = filterOwner(requestURI);
				if(ret) {
					ret = filterEditor(requestURI);
				}
			}
		}
		
		return ret;
	}

	private boolean filterAdmin(String requestURI) {
		boolean ret = true;
		for(int i = 0; i < adminURL.length && ret; i++) {
			ret = !requestURI.contains(adminURL[i]);
		}
		
		return ret;
	}
	
	private boolean filterOwner(String requestURI) {
		boolean ret = true;
		for(int i = 0; i < ownerURL.length && ret; i++) {
			ret = !requestURI.contains(ownerURL[i]);
		}
		
		return ret;
	}
	
	private boolean filterEditor(String requestURI) {
		boolean ret = true;
		for(int i = 0; i < editorURL.length && ret; i++) {
			ret = !requestURI.contains(editorURL[i]);
		}
		
		return ret;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
