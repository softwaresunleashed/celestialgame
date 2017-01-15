package com.unleashed.android.celestialgame.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.unleashed.android.celestialgame.R;
import com.unleashed.android.celestialgame.helpers.Constants;

public class UserProfilePage extends AppCompatActivity {

    private String mUsername;


    private TextView tvUsername;


    public static void startActivity(Context context, String username) {
        Intent intent = new Intent(context, UserProfilePage.class);
        intent.putExtra(Constants.USERNAME, username);

        context.startActivity(intent);
    }

    private void initUIHandles() {
//        etUsername = (EditText)findViewById(R.id.et_username);
//        etUsername.setOnClickListener(this);
//
//        etPassword = (EditText)findViewById(R.id.et_password);
//        etPassword.setOnClickListener(this);

        tvUsername = (TextView)findViewById(R.id.tvUser);
//        tvLogin.setOnClickListener(this);
//
//        tvSignUp = (TextView)findViewById(R.id.tv_signup);
//        tvSignUp.setOnClickListener(this);
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

        tvUsername.setText(mUsername);

    }

}
