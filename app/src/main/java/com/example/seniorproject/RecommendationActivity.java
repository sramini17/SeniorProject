package com.example.seniorproject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;



import com.google.android.gms.common.api.GoogleApiClient;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;


/**
 * Created by sramini on 3/8/17.
 */

public class RecommendationActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation);
        displayRecommendation();

    }

    public void displayRecommendation() {
        Bundle data = getIntent().getExtras();
        ArrayList<Business> businesses = (ArrayList<Business>) data.getSerializable("businesses");

        RestaurantAdapter adapter = new RestaurantAdapter(this, R.layout.row, businesses);
        ListView restListView = (ListView) findViewById(R.id.resList);
        restListView.setAdapter(adapter);

    }




    public class RestaurantAdapter extends ArrayAdapter {
        private ArrayList<Business> businesses;
        private int resource;
        private LayoutInflater inflater;


        public RestaurantAdapter(Context context, int resource, ArrayList<Business> businesses) {
            super(context, resource, businesses);
            this.businesses = businesses;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.row, null);
            }

            ImageView restaurantIcon;
            TextView name;
            TextView distance;
            RatingBar ratingBar;

            restaurantIcon = (ImageView)convertView.findViewById(R.id.restaurantPic);
            name = (TextView)convertView.findViewById(R.id.restName);
            distance =(TextView)convertView.findViewById(R.id.restDist);
            ratingBar = (RatingBar)convertView.findViewById(R.id.rating);




            name.setText(businesses.get(position).name());
            distance.setText(businesses.get(position).location().city());

            ratingBar.setRating(businesses.get(position).rating().floatValue());


            return convertView;
        }


    }
}
