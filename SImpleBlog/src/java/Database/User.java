/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

/**
 *
 * @author Rikysamuel
 */
public class User {
    String Username;
    String Password;
    String Name;
    String Email;
    String Role;
    boolean editable;
    public User(){
    }
    
    public boolean geteditable(){
        return this.editable;
    }
    
    public void setEditable(boolean edit){
        editable = edit;
    }
    public void SaveAction(){
        this.editable = false;
    }
    
    public void WantToEdit(){
        this.editable = true;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
}
