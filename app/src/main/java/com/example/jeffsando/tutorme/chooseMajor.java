package com.example.jeffsando.tutorme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class chooseMajor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_major);

        Button profilebutton =(Button) findViewById(R.id.profilebutton);


        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });


    }

    public void openProfile(){
        Intent intent = new Intent (chooseMajor.this, profile.class);
        Log.d("open profile function", "before startActivity");

        startActivity(intent);
        Log.d("open profile function", "middle");

        finish();
        Log.d("open profile function", "after finish");

    }
}
