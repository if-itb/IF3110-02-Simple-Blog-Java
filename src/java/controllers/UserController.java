package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.Post;
import models.User;
import services.DBConnector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@ManagedBean(name="userCtrl")
@SessionScoped
public class UserController {
    @ManagedProperty(value="#{user}")
    private User user;
    private ArrayList<User> users;

    public String showCreate() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.clearAttributes();
        user.setIsNewRecord(true);
        session.setAttribute("user",user);
        return "create";
    }

    public String showUpdate() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();

        if (requestParam.containsKey("id")) {
            User user = (User) session.getAttribute("user");
            user.load(Integer.parseInt(requestParam.get("id")));
            user.setIsNewRecord(false);
            session.setAttribute("user",user);
            return "update";
        } else {
            return "fail";
        }
    }
    
    public String showDelete() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();

        if (requestParam.containsKey("id")) {
            User user = new User();
            user.load(Integer.parseInt(requestParam.get("id")));
            user.delete();
            return "delete";
        } else {
            return "fail";
        }
    }
    
    public String doSubmit() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();
        
        user = (User) session.getAttribute("user");
        if (user != null && user.save()){
            return "success";
        } else {
            return "fail";
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT `id` FROM `user` ORDER BY `id` DESC";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);
            users = new ArrayList<>();

            while (result.next()) {
                User user = new User();
                user.load(result.getInt("id"));
                users.add(user);
            }
            return this.users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    

}
