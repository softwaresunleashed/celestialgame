package com.unleashed.android.celestialgame.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sudhanshu on 15/01/17.
 */

@IgnoreExtraProperties
public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

