package com.example.jeffsando.tutorme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import de.codecrafters.tableview.TableView;

import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;
public class Main3Activity extends AppCompatActivity implements Serializable {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ArrayList<String> listOfText = new ArrayList<String>();
        Intent i = getIntent();
        listOfText = i.getStringArrayListExtra("listOfText");

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linLayout);


        Log.d("main3","before For");
       /* for (int j = 0; j < listOfText.size(); j++) {
            Log.d("main3","in For");

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.n1, mainLayout, false);
            // Add the new row before the add field button.
            Log.d("main3","in For,listoftext.get(j) = " + listOfText.get(j) );

            ((TextView)rowView.findViewById(R.id.textView2)).setText(listOfText.get(j));

            mainLayout.addView(rowView,mainLayout.getChildCount() - 1);
        }*/


    }
    @Override
    protected void onStart(){
        super.onStart();

        ArrayList<String> Arr = new ArrayList<String>();

        Arr.add("hi");
        Arr.add("hi");
        Arr.add("hi");
        Arr.add("hi");
        Arr.add("hi");
        Arr.add("hi");

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linLayout);

        for (int j = 0; j < Arr.size(); j++) {
            Log.d("main3","in For");

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.n1, mainLayout,false);
            // Add the new row before the add field button.

            System.out.println("setting the text here at");
            ((TextView)rowView.findViewById(R.id.textView2)).setText(Arr.get(j));

            mainLayout.addView(rowView,mainLayout.getChildCount() - 1);
        }


    }
}
