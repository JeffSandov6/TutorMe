package com.example.jeffsando.tutorme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addSession extends AppCompatActivity {

    // public final static String CLASSNUMBER = "com.example.jeffsando.tutorme.CLASSNUM";
    // public final static String POSTINFO = "com.example.jeffsando.tutorme.POSTINFO";
    // public final static String HOURLYRATE = "com.example.jeffsando.tutorme.HOURLYRATE";

    private EditText classNumet, postInfoet, rateet;
    private Button btn;
    private String classNumst, postInfost, ratest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        //listen button
        btn = findViewById(R.id.sessionBtn);
        Log.d("addSession","onCreate");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("addSession","onClick");


                // extras.putString(POSTINFO,postInfost);
                // extras.putString(HOURLYRATE,ratest);

                //addSession.this, tableActivity.class
                Intent i = new Intent();
                classNumet = findViewById(R.id.classNumber_textInput);
                classNumst = classNumet.getText().toString();
                i.putExtra("classNum",classNumst);

                postInfoet = findViewById(R.id.session_postInfo);
                postInfost = postInfoet.getText().toString();
                i.putExtra("postInfo",postInfost);

                rateet = findViewById(R.id.session_hourlyrate);
                ratest = rateet.getText().toString();
                i.putExtra("rate",ratest);

                setResult(RESULT_OK, i);
                Log.d("addSession","setResult");

                //startActivity(i);
                finish();
            }
        });


    }







}