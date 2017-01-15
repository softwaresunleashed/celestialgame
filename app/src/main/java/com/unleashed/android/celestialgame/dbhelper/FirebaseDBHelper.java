package com.unleashed.android.celestialgame.dbhelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sudhanshu on 15/01/17.
 */

public class FirebaseDBHelper {

    private static FirebaseDatabase database;
    private static DatabaseReference dbRef;




    public static void init(){
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

    }

    public static void addValueEventListener(ValueEventListener vel){
        dbRef.addValueEventListener(vel);
    }

    public static void write(String tablename, String userId, Object obj_to_write){
        dbRef.child(tablename).child(userId).setValue(obj_to_write);
    }




}
