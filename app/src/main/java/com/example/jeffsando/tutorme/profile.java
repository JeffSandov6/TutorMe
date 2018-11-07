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

import java.io.Serializable;
import java.util.*;



public class profile extends AppCompatActivity implements Serializable {
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    String currUsername = "";
    ArrayList<String> usersCoursesList = new ArrayList<String>();
    ArrayList<String> usersDaysAvailability = new ArrayList<String>();


    ArrayList<HashMap<String, String>> allCoursesDataList = new ArrayList<HashMap<String, String>>();

    ArrayList<HashMap<String, String>> matchingCourses = new ArrayList<HashMap<String, String>>();  //do matching times list next

    ArrayList<HashMap<String, String>> matchingCoursesAndTime = new ArrayList<HashMap<String, String>>();  //do matching times list next

    ArrayList<String> allCoursesAsText = new ArrayList<String>();


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
        Intent intent = new Intent(this,Main3Activity.class);
       // startActivity(intent);


        //Get the data from your courses, grab all the courses or whatever


        getUserCourses();
        getUserAvailability();

        getAllSessions(); //don't have time to do exact queries right away



        //////TODO: when we present the pages we can just do a string builder.


        getMatchingSessions();

        getMatchingDays();


        //Intent intent = new Intent(profile.this, Main3Activity.class);
        intent.putStringArrayListExtra("listOfText", allCoursesAsText);


        startActivity(intent);

    }



    public void getAllSessions() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tutoring sessions");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        getHashedData((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });




    }


    public void getHashedData(Map<String, Object> sessionData)
    {
        //allCourseData //= new ArrayList<HashMap<String, String>>();

        for (Map.Entry<String, Object> entry : sessionData.entrySet()) {

            HashMap<String, String> currSession = (HashMap<String, String>) entry.getValue();


            allCoursesDataList.add(currSession);

        }

    }





    public void getMatchingSessions() {

        for(String course : usersCoursesList) {


            for(int i = 0; i < allCoursesDataList.size(); i++) {

                HashMap<String, String> currentCourseData = allCoursesDataList.get(i);

                String courseInData = currentCourseData.get("Course");

                if(course.equals(courseInData)) {  //if they match

                    matchingCourses.add(currentCourseData);

                }



            }
        }



    }







    public void getMatchingDays() {

        for(String day : usersDaysAvailability) {

            for(int i = 0; i < matchingCourses.size(); i++) {

                HashMap<String, String> currMatchCourseData = matchingCourses.get(i);

                String sessionDay = currMatchCourseData.get("Date");

                if(day.equals(sessionDay)) {

                    matchingCoursesAndTime.add(currMatchCourseData);
                }


            }


        }



        for(int i = 0; i < matchingCoursesAndTime.size(); i++)
        {
            HashMap<String, String> currMatchCourseData = matchingCoursesAndTime.get(i);
            StringBuilder matchingSessionsAsTest = new StringBuilder();

            matchingSessionsAsTest.append("Tutoring is available for you this week for the course ");
            matchingSessionsAsTest.append(currMatchCourseData.get("Course"));

            matchingSessionsAsTest.append(". The session's description is: \n ");
            matchingSessionsAsTest.append(currMatchCourseData.get("Description"));

            matchingSessionsAsTest.append(".\n We are letting you know because it is on one of your free days, ");
            matchingSessionsAsTest.append(currMatchCourseData.get("Date"));

            matchingSessionsAsTest.append(".\n The rate for this session will be ");
            matchingSessionsAsTest.append(currMatchCourseData.get("Rate"));



            matchingSessionsAsTest.append(" dollars, and the time it'll be at is ");
            matchingSessionsAsTest.append(currMatchCourseData.get("Time"));


            allCoursesAsText.add(matchingSessionsAsTest.toString());
            System.out.println(matchingSessionsAsTest.toString());




        }


    }








    public void getUserCourses() {
        final String userId = auth.getCurrentUser().getUid();

        mDatabase.child("users").child(userId).child("Courses").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        HashMap<String, String> userCourses = new HashMap<String, String>();

                        userCourses = (HashMap<String, String>) dataSnapshot.getValue();



                        for (Map.Entry<String, String> item : userCourses.entrySet()) {
                            String key = item.getKey();
                            String value = item.getValue();

                            usersCoursesList.add(value);
                            Log.d("values dont count ", value);

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                        Log.d("shit failed", "db error");

                    }
                }
        );


    }





    public void getUserAvailability() {
        final String userId = auth.getCurrentUser().getUid();

        mDatabase.child("users").child(userId).child("DaysOpenForTutoring").child("daysAvailable").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        String userAvailability;

                        userAvailability = (String) dataSnapshot.getValue();

                        List<String> finalString= Arrays.asList(userAvailability.split("\\s*,\\s*"));

                        usersDaysAvailability.addAll(finalString);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                        Log.d("shit failed", "db error");

                    }
                }
        );



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