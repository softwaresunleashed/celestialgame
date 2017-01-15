package com.unleashed.android.celestialgame.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sudhanshu on 15/01/17.
 */

@IgnoreExtraProperties
public class UserProfile {

    private String address;
    private String contact;

    public UserProfile() {
    }

    public UserProfile(String address, String contact) {
        this.address = address;
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
