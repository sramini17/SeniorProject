package com.example.seniorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signIn;
    private Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = (Button)findViewById(R.id.signInButton);
        createAccount = (Button)findViewById(R.id.createAccountButton);
        signIn.setOnClickListener(this);
        createAccount.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButton:
                Intent i = new Intent(this, Display.class);
                startActivity(i);
                break;
            case R.id.createAccountButton:
                Intent k = new Intent(this, CreateAccount.class);
                startActivity(k);
                break;
            default:
                break;
        }
    }

    /*public void createAccount(View v) {
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

    public void signIn(View v) {
        Intent i = new Intent(this, Display.class);
        startActivity(i);
    }*/


}
