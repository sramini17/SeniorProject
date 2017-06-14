package com.example.seniorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sramini on 5/28/17.
 */

public class RateActivity extends Activity {
    Button cancel;
    Button submit;
    String restName;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRatingRef = mRootRef.child("restaurants");

    public float actualAverage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        setFields();
        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submitRating);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });



        //DATABASE STUFF
        mRatingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String text =
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //mRatingRef.addValueEventListener(listener);
    }

    //@Override
    protected void updateFun() {
//        super.onStart();

        if (restName != null) {
            DatabaseReference curRestRef = mRootRef.child("restaurants").child(restName);

            curRestRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (snapshot.getValue(Restaurant.class).name.equals(restName)) {

                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);

                    if (restaurant != null) {

                        if (restaurant.num <= 0) {
                            DatabaseReference num = mRootRef.child("restaurants").child(restName).child("num");
                            num.setValue(1);
                        } else {
                            DatabaseReference num = mRootRef.child("restaurants").child(restName).child("num");
                            int ratingsNum = dataSnapshot.getValue(Restaurant.class).num;
                            num.setValue(ratingsNum + 1);
                        }
//
                        float total = restaurant.total;
                        float curAdd = restaurant.curVal;
                        DatabaseReference restRatingRef = mRootRef.child("restaurants").child(restName).child("total");
                        //RatingBar ratingBar = (RatingBar)findViewById(R.id.userRate);
                        //float rating = ratingBar.getRating();
                        restRatingRef.setValue(total + curAdd);

                        actualAverage = total/restaurant.num;

                        MainActivity.map.put(restName, actualAverage);
                    }
//

//                    DatabaseReference temp = mRootRef.child("restaurants").child("temp").child("average");
//
//                    if (new Float(restaurant.average) != null) {
//                        temp.setValue(restaurant.average);
//                    }
//
//                    if (restaurant.name.equals("POTATO")) {
//                        float avg = restaurant.average;
//
//                        //System.out.print("THE TEMPNAME IS:" + tempName);
//                        //email fetched from database
//
//                        DatabaseReference temp = mRootRef.child("restaurants").child("temp").child("average");
//                        temp.setValue(avg);
//                    } else {
//                        DatabaseReference temp = mRootRef.child("restaurants").child("temp").child("average");
//                        temp.setValue(-2);
//                    }
//                    }
//                }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    public void setFields() {
        TextView name = (TextView) findViewById(R.id.rateRestName);
        restName = getIntent().getStringExtra("name");
        name.setText(restName);
    }

    //DATABASE STUFF
    public void submit() {
        //have the number of stars
        RatingBar ratingBar = (RatingBar)findViewById(R.id.userRate);
        float rating = ratingBar.getRating();

        //restName is restaurant name
        DatabaseReference restRatingRef = mRootRef.child("restaurants").child(restName).child("curVal");
        restRatingRef.setValue(rating);

        DatabaseReference restNameRef = mRootRef.child("restaurants").child(restName).child("name");
        restNameRef.setValue(restName);

        updateFun();

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //ref

        //int size = dataSnapshot.getValue(Integer.class);
                //Dinosaur dinosaur = dataSnapshot.getValue(Dinosaur.class);

        //if (restRatingRef.get)

//        DatabaseReference ratingStuff = FirebaseDatabase.getInstance().getReference("/asdfadsf`");
//        if (ratingStuff == null) {
//            DatabaseReference temp = mRootRef.child("restaurants").child("temp");
//            temp.setValue("DSLKF");
//        } else {
//            DatabaseReference temp = mRootRef.child("restaurants").child("temp");
//            temp.setValue("there");
//        }

        finish();
    }

    public void cancel() {
        finish();
    }
}
