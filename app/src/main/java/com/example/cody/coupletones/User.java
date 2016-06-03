package com.example.cody.coupletones;

/**
 * Created by Utkrisht on 6/3/2016.
 */
public class User {
    private String email;
    private String fullName;
    private String password;

    public User() {}

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword(){
        return password;
    }
}

