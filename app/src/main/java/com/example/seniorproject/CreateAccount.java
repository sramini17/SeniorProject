package com.example.seniorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sramini on 3/8/17.
 */

public class CreateAccount extends Activity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

    }

    public void submit(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
