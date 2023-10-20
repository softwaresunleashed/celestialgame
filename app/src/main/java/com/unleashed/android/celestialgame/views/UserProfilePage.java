package com.unleashed.android.celestialgame.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unleashed.android.celestialgame.R;
import com.unleashed.android.celestialgame.controllers.FirebaseDBHelper;
import com.unleashed.android.celestialgame.helpers.Constants;
import com.unleashed.android.celestialgame.models.User;
import com.unleashed.android.celestialgame.models.UserProfile;

public class UserProfilePage extends AppCompatActivity implements View.OnClickListener {

    private String mUsername;
    private String mPassword;
    private String mAddress;
    private String mContact;


    private TextView tvUsername;
    private TextView tvUpdateProfile;
    private EditText etAddress;
    private EditText etContact;
    private EditText etPassword;


    public static void startActivity(Context context, String username, String password, String address, String contact) {
        Intent intent = new Intent(context, UserProfilePage.class);
        intent.putExtra(Constants.USERNAME, username);
        intent.putExtra(Constants.PASSWORD, password);
        intent.putExtra(Constants.ADDRESS, address);
        intent.putExtra(Constants.CONTACT, contact);

        context.startActivity(intent);
    }

    private void initUIHandles() {
        tvUsername = (TextView) findViewById(R.id.tv_UserName);
        etAddress = (EditText) findViewById(R.id.et_Address);
        etContact = (EditText) findViewById(R.id.et_ContactNumber);
        etPassword = (EditText) findViewById(R.id.et_password);

        tvUpdateProfile = (TextView) findViewById(R.id.tv_updateprofile);
        tvUpdateProfile.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        // Init UI Handles
        initUIHandles();

        // Get the Name of the User ID the user is logged in with.
        // This is passed from Login Screen.
        mUsername = getIntent().getExtras().getString(Constants.USERNAME);
        mPassword = getIntent().getExtras().getString(Constants.PASSWORD);
        mAddress = getIntent().getExtras().getString(Constants.ADDRESS);
        mContact = getIntent().getExtras().getString(Constants.CONTACT);

        // Set the username (uneditable)
        tvUsername.setText(mUsername);

        // Set the original password
        etPassword.setText(mPassword);

        etAddress.setText(mAddress);
        etContact.setText(mContact);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_updateprofile:
                updateProfile();
                break;
        }
    }

    private void updateProfile() {
        User userObj = new User(mUsername, etPassword.getText().toString(), new UserProfile(etAddress.getText().toString(), etContact.getText().toString()));
        FirebaseDBHelper.write("users", mUsername ,userObj);
    }
}
