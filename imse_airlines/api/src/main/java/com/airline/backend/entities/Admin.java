package com.airline.backend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Admin {

    @Id
    private int adminID;
    private String username;
    private String password;
    
    @ManyToMany(mappedBy = "admins")
    List<Company> companies;

    public Admin(){}

    public Admin(int adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [adminID=" + adminID + ", password=" + password + ", username=" + username + "]";
    }

    

    
}
