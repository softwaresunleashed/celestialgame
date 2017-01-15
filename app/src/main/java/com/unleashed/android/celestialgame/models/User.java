package com.unleashed.android.celestialgame.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sudhanshu on 15/01/17.
 */

@IgnoreExtraProperties
public class User {

    private String username;
    private String password;
    private UserProfile userProfile;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, UserProfile userProfile) {
        this.username = username;
        this.password = password;

        this.userProfile = userProfile;
    }

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}

