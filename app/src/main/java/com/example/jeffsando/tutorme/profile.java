package com.example.jeffsando.tutorme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


public class profile extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    String currUsername = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        //TODO: UPDATE THE USERNAME
//        TextView usernameView = (TextView) findViewById(R.id.username);
//
//        String newName = updateUsername();
//        usernameView.setText(newName);



        Button schedulebutton = (Button) findViewById(R.id.button2);
        schedulebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetSchedule();
            }
        });

        Button sessionsbutton = (Button) findViewById(R.id.button3);
        sessionsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJobBoard();
            }
        });
    }

    public void openSetSchedule() {
        Intent intent = new Intent(this, setSchedule.class);
        startActivity(intent);
    }

    public void openJobBoard() {
        //TODO: Enable button to search job board based on
        //Intent intent = new Intent(this,job_board.class);
        //startActivity(intent);
    }


    public String updateUsername() {

        final String userId = auth.getCurrentUser().getUid();
        Log.d("before ", "the listener");
        Log.d("also using", userId);


        mDatabase.child("users").child(userId).child("username").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        String user = (String) dataSnapshot.getValue();


                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.d("User ", " is unexpectedly null");
                            currUsername = "noName";
                        } else {
                            // Write new post
                            currUsername = user;
                            Log.d("we got here", "to update the currUsername thing");
                            Log.d("the username is ", currUsername);

                        }

                        // Finish this Activity, back to the stream

                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                        Log.d("shit failed", "db error");

                    }
                }
                );
        // [END single_value_read]

        Log.d("end updateusername", "function");

        return currUsername;



    }

}