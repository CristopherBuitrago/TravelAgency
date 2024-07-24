package com.chulos.travelagency.auth.domain.entity;

public class Auth {
    // attributes
    private String email;
    private String password;
    
    // empty constructor
    public Auth() {
    }

    // constructor
    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}