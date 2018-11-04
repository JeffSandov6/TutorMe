package com.example.jeffsando.tutorme;

import java.util.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;


//TODO: setSchedule should read current schedule from the database


public class setSchedule extends AppCompatActivity {
    ArrayList<String> daysAvailable= new ArrayList<String>();

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button updatebutton =(Button) findViewById(R.id.update);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSchedule();
                Log.d("did we add our stuff?", "Yes");
                //TODO: Add a message to confirm that schedule is saved
            }
        });
    }

    //saves valus
     public void updateSchedule(){
         EditText Course1 =(EditText)findViewById(R.id.course1);
         EditText Course2 =(EditText)findViewById(R.id.course2);
         EditText Course3 =(EditText)findViewById(R.id.course3);
         EditText Course4 =(EditText)findViewById(R.id.course4);

         if(((CheckBox) findViewById(R.id.checkBox)).isChecked())
             daysAvailable.add("Monday");
         if(((CheckBox) findViewById(R.id.checkBox2)).isChecked())
             daysAvailable.add("Tuesday");
         if(((CheckBox) findViewById(R.id.checkBox3)).isChecked())
             daysAvailable.add("Wednesday");
         if(((CheckBox) findViewById(R.id.checkBox4)).isChecked())
             daysAvailable.add("Thursday");
         if(((CheckBox) findViewById(R.id.checkBox5)).isChecked())
             daysAvailable.add("Friday");
         if(((CheckBox) findViewById(R.id.checkBox6)).isChecked())
             daysAvailable.add("Saturday");
         if(((CheckBox) findViewById(R.id.checkBox7)).isChecked())
             daysAvailable.add("Sunday");

         if(daysAvailable.size() > 0) {
             setDaysAvailable();
         }

     }

     public void setDaysAvailable() {

         final String userId = auth.getCurrentUser().getUid();
         HashMap<String, Object> daysAvailableHash = new HashMap<>();
         StringBuilder daysOpen = new StringBuilder();

         for(int i = 0; i < daysAvailable.size(); i++) {

             String day = daysAvailable.get(i);
             daysOpen.append(day);

             if(i != daysAvailable.size() - 1) {
                 daysOpen.append(", ");
             }

         }

         daysAvailableHash.put("daysAvailable", daysOpen.toString());

         mDatabase.child("users").child(userId).child("openForTutoring").setValue(daysAvailableHash);

     }

}


