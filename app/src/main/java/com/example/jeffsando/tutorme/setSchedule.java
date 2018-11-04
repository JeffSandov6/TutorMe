package com.example.jeffsando.tutorme;

import java.util.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

//TODO: setSchedule should read current schedule from the database


public class setSchedule extends AppCompatActivity {
    ArrayList daysAvailable= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        Button updatebutton =(Button) findViewById(R.id.update);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSchedule();
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
     }

}


