package com.unleashed.android.celestialgame.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.unleashed.android.celestialgame.R;
import com.unleashed.android.celestialgame.helpers.Constants;

public class UserProfilePage extends AppCompatActivity {

    private String mUsername;


    private EditText etUsername;


    public static void startActivity(Context context, String username) {
        Intent intent = new Intent(context, UserProfilePage.class);
        intent.putExtra(Constants.USERNAME, username);

        context.startActivity(intent);
    }

    private void initUIHandles() {

        etUsername = (EditText) findViewById(R.id.et_UserName);

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

        etUsername.setText(mUsername);

    }

}
