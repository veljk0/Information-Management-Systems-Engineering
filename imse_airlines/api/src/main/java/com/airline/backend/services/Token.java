package com.airline.backend.services;

public class Token {
    private String username;
    private Boolean flag;

    public Token(String username, Boolean flag) {
        this.username = username;
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Token [flag=" + flag + ", username=" + username + "]";
    }

    

    
    
}
