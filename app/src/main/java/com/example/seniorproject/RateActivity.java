package com.example.seniorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sramini on 5/28/17.
 */

public class RateActivity extends Activity {
    Button cancel;
    Button submit;

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



    }

    public void setFields() {
        TextView name = (TextView) findViewById(R.id.rateRestName);
        String restName = getIntent().getStringExtra("name");
        name.setText(restName);
    }

    public void submit() {
        finish();
    }

    public void cancel() {
        finish();
    }
}
