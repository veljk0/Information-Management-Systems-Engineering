package com.airline.backend.migration.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class MongoAdmin {

    @Id
    private String id;
    private String username;
    private String password;

    public MongoAdmin(){}

    public MongoAdmin(String adminID, String username, String password) {
        this.id = adminID;
        this.username = username;
        this.password = password;
    }

    public MongoAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getAdminID() {
        return id;
    }

    public void setAdminID(String adminID) {
        this.id = adminID;
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
        return "Admin [adminID=" + id + ", password=" + password + ", username=" + username + "]";
    }

}
