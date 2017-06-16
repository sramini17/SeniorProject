package com.example.seniorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sramini on 2/12/17.
 */

public class SearchActivity extends Activity {

    YelpAPIFactory apiFactory;
    YelpAPI yelpAPI;
    ArrayList<Business> businesses;
    Intent startNewActivity;

    private static final String CONSUMER_KEY = "pnQ28-b0JNAxqbVXUsvyEg";
    private static final String CONSUMER_SECRET = "qOdYc-QqlaanEyRaaFonSd0BDTc";
    private static final String TOKEN = "wkv66pbdrRpMJd4cfwiGEcyZ6aRldbQm";
    private static final String TOKEN_SECRET = "tMpbiJCXd_l24R5B4ArupntW1T4";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        apiFactory = new YelpAPIFactory(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        yelpAPI = apiFactory.createAPI();

        Button btn = (Button) findViewById(R.id.submitButton);
        Button lucky = (Button) findViewById(R.id.feelingLucky);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submit(v);
                }
                catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        });

        lucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    feelingLucky(v);
                }
                catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        });

    }

    public void submit(View view) throws IOException{

        startNewActivity = new Intent(this, RecommendationActivity.class);


        /*Get location for query*/
        EditText loc = (EditText) findViewById(R.id.location);
        String location = loc.getText().toString();

        if(location.equals("")) {
            location = "San Luis Obispo, CA";
        }

        /*Get Meal Type for query*/
        EditText type = (EditText) findViewById(R.id.mealType);
        String typeMeal = type.getText().toString();

        if(typeMeal.equals("")) {
            typeMeal = "lunch";
        }

        /*Get distance filter*/
        EditText dist = (EditText) findViewById(R.id.distance);
        double distMeters = Integer.parseInt(dist.getText().toString()) * 1609.34 ;


        if(dist.getText().toString().equals("")) {
            distMeters = 25 * 1609.34;
        }

        String distance = Double.toString(distMeters);

        new BusinessOperation().execute(location, typeMeal, distance);


     /*   params.put("term", typeMeal);

        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                businesses = searchResponse.businesses();
                // Update UI text with the Business object.

                ArrayList<String> businessIDs = new ArrayList<>();

                for(Business b: businesses) {
                    businessIDs.add(b.id());
                }

                startNewActivity.putExtra("businesses", businessIDs);
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };

        Call<SearchResponse> call = yelpAPI.search(location, params);
        call.enqueue(callback);
        call.cancel();*/



    }


    public void feelingLucky(View view) {
        startNewActivity = new Intent(this, FeelingLuckyActivity.class);
         /*Get location for query*/
        EditText loc = (EditText) findViewById(R.id.location);
        String location = loc.getText().toString();
        Random rand = new Random();

        int temp = rand.nextInt(RecCreator.allTypes.size());
        String reco = RecCreator.allTypes.get(temp).type;

        new BusinessOperation().execute(location,reco, "25");


    }

    private class BusinessOperation extends AsyncTask<String, Void, ArrayList<Business>> {

        @Override
        protected ArrayList<Business> doInBackground(String... params) {
            try {
                allFoods food = addElements.addToArray(RecCreator.allTypes, RecCreator.allFoods);

                Map<String, String> p = new HashMap<>();
                String[] terms = params[1].split(",");
                String termFilter = "";
                for(int i = 0; i < terms.length; i++) {
                    termFilter += "+" + terms[i];
                }

                p.put("term", termFilter);
                p.put("radius_filter", params[2]);
                p.put("sort", "1");
                p.put("limit", "7");
                Call<SearchResponse> call = yelpAPI.search(params[0], p);


                Response<SearchResponse> response = call.execute();
                call.cancel();
                SearchResponse searchResponse = response.body();

                Map<String, String> p2 = new HashMap<>();
                termFilter = "";
                for(int i = 0; i < terms.length; i++) {
                    String appendThing = RecCreator.createRec(food, terms[i]);
                    termFilter += "+" + appendThing;
                }

                p2.put("term", termFilter);
                p2.put("radius_filter", params[2]);
                p2.put("sort", "1");
                p2.put("limit", "3");
                Call<SearchResponse> call2 = yelpAPI.search(params[0], p2);


                Response<SearchResponse> response2 = call2.execute();
                call2.cancel();
                SearchResponse searchResponse2 = response2.body();

                ArrayList<Business> first = searchResponse.businesses();
                ArrayList<Business> second = searchResponse2.businesses();

                for (Business b : second) {
                    if (!first.contains(b)) {
                        first.add(b);
                    }
                }
//                searchResponse.businesses().addAll(searchResponse2.businesses());
                return first;
            } catch (Exception e ){
                Log.e("ERROR", e.toString());
            }
            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<Business> businesses) {
            onSuccess(businesses);

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void onSuccess(ArrayList<Business> businesses) {
       /* ArrayList<String> businessIDs = new ArrayList<>();

        for(Business b: businesses) {
            businessIDs.add(b.id());
        }*/

        startNewActivity.putExtra("businesses", businesses);
        startActivity(startNewActivity);

    }

}
