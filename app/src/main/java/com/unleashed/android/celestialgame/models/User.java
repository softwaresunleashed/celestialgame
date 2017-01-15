package com.unleashed.android.celestialgame.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sudhanshu on 15/01/17.
 */

@IgnoreExtraProperties
public class User {
    public String username;
    public String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

