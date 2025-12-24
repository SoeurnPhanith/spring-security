package com.example.spring_security_inRAM_part2.model;

public class Users {

    private String username;
    private String password;
    private String role;

    public Users() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;   // ❗ MUST return password
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
