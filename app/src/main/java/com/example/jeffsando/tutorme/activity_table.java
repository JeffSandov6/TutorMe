package com.example.jeffsando.tutorme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class activity_table extends AppCompatActivity {

    private LinearLayout parentLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.card_layout, null);
        // Add the new row before the add field button.

        ((TextView)rowView.findViewById(R.id.tutorClass_textView)).setText(data.getStringExtra("classNum"));
        ((TextView)rowView.findViewById(R.id.postInfo_textView)).setText(data.getStringExtra("postInfo"));
        ((TextView)rowView.findViewById(R.id.ratehourly_TextView)).setText(data.getStringExtra("rate"));

        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }


    public void goToSessionActivity (View view){
        Intent intent = new Intent (this, addSession.class);
        startActivityForResult(intent,1);
    }
}
