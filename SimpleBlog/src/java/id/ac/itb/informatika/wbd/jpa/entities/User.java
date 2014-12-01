package id.ac.itb.informatika.wbd.jpa.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT c FROM User c"),
                @NamedQuery(name = "User.findById", query = "SELECT c FROM User c WHERE c.id = :id"),
                @NamedQuery(name = "User.findByRole", query = "SELECT c FROM User c WHERE c.role = :role")})

public class User {
    
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "user_email")
    private String email;
    
    @Column(name = "user_name")
    private String name;
    
    @Column(name = "user_username")
    private String username;
    
    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_role")
    private String role;
    
    public Long getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRole(String role){
        this.role = role;
    }
}
