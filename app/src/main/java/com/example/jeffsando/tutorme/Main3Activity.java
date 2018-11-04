package com.example.jeffsando.tutorme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import de.codecrafters.tableview.TableView;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;
public class Main3Activity extends AppCompatActivity {
    TableView t1;
    int counter = 0;
    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.n1, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ArrayList<String> Arr = new ArrayList<String>();
        for (int i = 0; i < Arr.size(); i++) {
            rowView = inflater.inflate(R.layout.n1, null);
            ((TextView)rowView.findViewById(R.id.textView2)).setText(Integer.toString(counter));

            t1.addView(rowView);
            counter++;

        }

    }
}
