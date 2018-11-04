package com.example.jeffsando.tutorme;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class JobViewHolder extends RecyclerView.ViewHolder{
    protected TextView vTutorName;
    protected TextView vClassNumber;
    protected TextView vTutorInfo;

    public JobViewHolder(View v){
        super(v);
        vTutorName = (TextView) v.findViewById(R.id.tutorName_textView);
        vClassNumber = (TextView) v.findViewById(R.id.tutorClass_textView);
        vTutorInfo = (TextView) v.findViewById(R.id.postInfo_textView);
    }
}
