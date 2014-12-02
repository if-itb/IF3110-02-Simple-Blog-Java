/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simpleblogjava;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mysql.jdbc.*;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KBK
 */
public class DbConnection extends HttpServlet{
    public void init(){
        try {
            Driver d = new Driver();
            Properties p = new Properties();
            
            p.put("user", "root");
            p.put("password", "");
            
            d.connect("localhost:3306", p);
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
