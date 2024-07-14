package com.chulos.travelagency.user.domain.entity;

public class User {
    // attributes
    private int id;
    private String username;
    private String email;
    private String password;
    private String roleCode;
    private String roleName;

    // empty constructor
    public User() {
    }

    // full constructor
    public User(int id, String username, String email, String password, String roleCode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleCode = roleCode;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName () {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
