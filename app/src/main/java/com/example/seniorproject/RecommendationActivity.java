package com.example.seniorproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



import java.util.ArrayList;


/**
 * Created by sramini on 3/8/17.
 */

public class RecommendationActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation);
        displayRecommendation();


    }

    public void displayRecommendation() {
        Bundle data = getIntent().getExtras();
        TextView recommend = (TextView) findViewById(R.id.recommendedText);
        ArrayList<String> businesses = data.getStringArrayList("businesses");
        String id = businesses.get(0);
        recommend.setText(id);

    }
}
