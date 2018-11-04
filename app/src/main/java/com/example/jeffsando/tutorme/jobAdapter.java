package com.example.jeffsando.tutorme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class jobAdapter extends RecyclerView.Adapter<jobAdapter.JobViewHolder> {


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


    private List<jobInfo> jobList;

    public jobAdapter(List<jobInfo> jobList) {
        this.jobList = jobList;
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    @Override
    public void onBindViewHolder(JobViewHolder jobViewHolder, int i) {
        jobInfo ci = jobList.get(i);
        jobViewHolder.vTutorName.setText(ci.TutorName);
        jobViewHolder.vClassNumber.setText(ci.ClassNum);
        jobViewHolder.vTutorInfo.setText(ci.TutorInfo);
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new JobViewHolder(itemView);
    }

}
