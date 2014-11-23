package wbd.tubesII;

/**
 *
 * @author Asep Saepudin
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {
    private static Connection currentCon = null;
    private static ResultSet rs = null;
    
    public static User login(User user) {
        Statement statement = null;
        
        String email = user.getEmail();
        String password = user.getPassword();
        
        String searchQuery = "SELECT * FROM `user` WHERE `email` = '" + email + "'"
                + " AND `password` = '" + password + "'";
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            
            rs = statement.executeQuery(searchQuery);
            if (!rs.next()) {
                System.err.println("Invalid email/password");
                user.setValid(false);
            } else {
                String name = rs.getString("name");	        
                String role = rs.getString("role");
                
                user.setName(name);
                user.setRole(role);
                user.setValid(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return user;
    }
    
    public static boolean register(User user) {
        Statement statement = null;
        
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String role = user.getRole();
        
        String searchQuery = "SELECT * FROM `user` WHERE `email` = '" + email + "'";
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            
            rs = statement.executeQuery(searchQuery);
            if (rs.next()) {
                System.err.println("Email has been used");
                return false;
            } else {
                searchQuery = "INSERT INTO `user` (`email`, `password`, `name`, `role`) VALUES ('" +
                        email + "', '" + password + "', '" + name + "', '" + role + "')";               
                statement.executeUpdate(searchQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return true;
    }
    
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        Statement statement = null;        
        String searchQuery = "SELECT * FROM `user`";
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            
            rs = statement.executeQuery(searchQuery);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setValid(true);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return users;
    }
    
    public static boolean delete(String email) {
        Statement statement = null;
                
        String searchQuery = "SELECT * FROM `user` WHERE `email` = '" + email + "'";
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            
            rs = statement.executeQuery(searchQuery);
            if (!rs.next()) {
                System.err.println("User doesn't exist");
                return false;
            } else {
                searchQuery = "DELETE FROM `user` where `email`='" +
                        email + "'";
                statement.executeUpdate(searchQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return true;
    }
    
    public static User getUser(String email) {
        Statement statement = null;        
        String searchQuery = "SELECT * FROM `user` WHERE `email`='" + email +"'";
        User user = new User();
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            
            rs = statement.executeQuery(searchQuery);
            if (rs.next()) {                
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setValid(true);                
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return user;
    }
    
    public static boolean update(User user) {
        Statement statement = null;
        
        int id = user.getId();
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String role = user.getRole();
        
        String searchQuery = "UPDATE `user` SET `email`='" + email + "', `password`='" + password +
                "', name='" + name + "', `role`='" + role + "' WHERE `id` = " + id;
        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
                        
            statement.executeUpdate(searchQuery);            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    rs = null;
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    statement = null;
                }
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }
                currentCon = null;
            }                        
        }
        return true;
    }    
}
