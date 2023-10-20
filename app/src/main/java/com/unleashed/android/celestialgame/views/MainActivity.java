package com.unleashed.android.celestialgame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unleashed.android.celestialgame.R;
import com.unleashed.android.celestialgame.controllers.FirebaseDBHelper;
import com.unleashed.android.celestialgame.helpers.Constants;
import com.unleashed.android.celestialgame.helpers.Utils;
import com.unleashed.android.celestialgame.models.User;
import com.unleashed.android.celestialgame.models.UserProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    private static final String TAG = "CelestialGameTag";
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvLogin;
    private TextView tvSignUp;

    private List<User> mListUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init UI Handles
        initUIHandles();

        // Init Datastructures
        initDataStructures();

        // Init Firebase database.
        FirebaseDBHelper.init();

        //Add Firebase data change event handler -- onDataChange() & onCancelled()
        FirebaseDBHelper.addValueEventListener(this);

    }

    private void initDataStructures() {
        if(mListUser == null){
            mListUser = new ArrayList<User>();
        }
    }


    private void initUIHandles() {
        etUsername = (EditText)findViewById(R.id.et_username);
        etUsername.setOnClickListener(this);

        etPassword = (EditText)findViewById(R.id.et_password);
        etPassword.setOnClickListener(this);

        tvLogin = (TextView)findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);

        tvSignUp = (TextView)findViewById(R.id.tv_signup);
        tvSignUp.setOnClickListener(this);
    }

    private void writeNewUser(String userId, String password) {
        User userObj = new User(userId, password, new UserProfile("",""));

        // Check if user exists already, if yes display a error toast
        if(isUserAlreadyExist(userId)){
            Utils.displayPopup("User Exists...Try With Different UserName");
            return;
        } else {
            FirebaseDBHelper.write("users", userId ,userObj);
            clearInputFields();
            Utils.displayPopup("New User Created...");
        }
    }

    private void clearInputFields() {
        etUsername.setText("");
        etPassword.setText("");
    }

    private void prepareLocalUserList(DataSnapshot dataSnapshot){
        Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.getValue();
        if(objectMap == null){
            // No Users yet
            return;
        }

        HashMap<String, Object> jsonPayload = (HashMap<String, Object>)objectMap.get("users");
        if(jsonPayload == null){
            // No Users yet
            return;
        }

        Collection<HashMap<String, String>> list = new ArrayList(jsonPayload.values());

        int sizeoflist = list.size();
        int i = 0;
        mListUser.clear();
        while(i < sizeoflist){
            String username = ((HashMap<String, String>)list.toArray()[i]).get(Constants.USERNAME).toString();
            String password = ((HashMap<String, String>)list.toArray()[i]).get(Constants.PASSWORD).toString();
            String address = ((HashMap<String, String>)list.toArray()[i]).get(Constants.ADDRESS).toString();
            String contact = ((HashMap<String, String>)list.toArray()[i]).get(Constants.CONTACT).toString();

            User user = new User(username, password, new UserProfile(address, contact));
            mListUser.add(user);
            i++;
        }
    }

    //Firebase database data changed event callbacks
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        prepareLocalUserList(dataSnapshot);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", databaseError.toException());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                authenticateUser(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                break;

            case R.id.tv_signup:
                writeNewUser(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
        }
    }

    private void authenticateUser(String username, String password) {
        boolean validuser = false;

        int sizeoflist = mListUser.size();
        int i = 0;
        while(i < sizeoflist){
            String usernameInDB = mListUser.get(i).getUsername();
            String passwordInDB = mListUser.get(i).getPassword();
            if(username.equals(usernameInDB) && password.equals(passwordInDB)){
                validuser = true;
                break;
            }
            i++;
        }

        // Check if valid user was found
        if(validuser){
            Utils.displayPopup("Login Success...");
            clearInputFields();
            // Take me to user profile page
            UserProfilePage.startActivity(this, username,
                                                password,
                                                mListUser.get(i).getUserProfile().getAddress(),
                                                mListUser.get(i).getUserProfile().getContact() );

        } else {
            Utils.displayPopup("Try again with Correct Login Credentials.");
        }


    }

    private boolean isUserAlreadyExist(String username){
        boolean result = false;

        int sizeoflist = mListUser.size();
        int i = 0;
        while(i < sizeoflist){
            String usernameInList = mListUser.get(i).getUsername();

            if(username.equals(usernameInList)){
                result = true;
                break;
            }

            i++;
        }
        return result;
    }



}
