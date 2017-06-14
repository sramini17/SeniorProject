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
        ratingBar.getNumStars();

        //restName is restaurant name
        DatabaseReference restRatingRef = mRootRef.child("restaurants").child(restName);
        restRatingRef.setValue("good");


        finish();
    }

    public void cancel() {
        finish();
    }
}
