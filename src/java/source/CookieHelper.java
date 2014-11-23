/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author akhfa
 */
public class CookieHelper extends HttpServlet{
    private Cookie[] cookies;
    private Cookie foundCookie;
    private String username;
    
    public CookieHelper(Cookie [] _cookies)
    {
        cookies = _cookies;
    }
    
    /**
     * Untuk mengecek apakah sudah ada cookie yang disimpan.
     * Jika ada, variable username akan terisi sesuai value dari cookie yang disimpan
     * @return 
     */
    public boolean thereIsCookie()
    {
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username"))
                {
                    foundCookie = cookie;
                    username = cookie.getValue();
                    return true;
                }
            }
        }
        return false;        
    }
    
    public void destroyCookie()
    {
        foundCookie.setMaxAge(0);
    }
    
    public Cookie getCookie()
    {
        return foundCookie;
    }
    /**
     * Fungsi untuk mengambil nilai dari cookie jika cookie memang ada
     * @return Value dari cookie
     */
    public String getUsername()
    {
        return username;
    }
}
