package com.example.S10174164_Week11Practical;

public class Account {
    private String username;
    private String password;

    public Account(String usr, String pwd) {
        username = usr;
        password = pwd;
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
}
