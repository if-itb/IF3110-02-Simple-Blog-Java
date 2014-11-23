package wbd.tubesII;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Asep Saepudin
 */
public class CustomHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) context.getAttribute("activeUsers");
        
        activeUsers.put(session.getId(), session);
        context.setAttribute("activeUsers", activeUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) context.getAttribute("activeUsers");
        
        activeUsers.remove(session.getId());
    }
    
}
