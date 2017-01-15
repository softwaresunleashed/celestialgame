package com.unleashed.android.celestialgame.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.unleashed.android.celestialgame.R;
import com.unleashed.android.celestialgame.dbhelper.FirebaseDBHelper;
import com.unleashed.android.celestialgame.models.User;

public class MainActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private TextView tvLogin;
    private TextView tvSignUp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init UI Handles
        initUIHandles();

        // Init Firebase database.
        FirebaseDBHelper.init();

        //Add Firebase data change event handler -- onDataChange() & onCancelled()
        FirebaseDBHelper.addValueEventListener(this);

        writeNewUser("sudha", "hello");

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
        User userObj = new User(userId, password);

        FirebaseDBHelper.write("users", userId ,userObj);

    }


    //Firebase database data changed event callbacks
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        //String value = dataSnapshot.getValue(String.class);
        //Log.d(TAG, "Value is: " + value);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        // Failed to read value
        //Log.w(TAG, "Failed to read value.", databaseError.toException());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                break;

            case R.id.tv_signup:
                writeNewUser(etUsername.getText().toString(), etPassword.getText().toString());
                break;
        }
    }

}
