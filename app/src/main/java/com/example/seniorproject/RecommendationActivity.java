package com.example.seniorproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;



import com.google.android.gms.common.api.GoogleApiClient;
import com.yelp.clientlib.entities.Business;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
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

        if(businesses.size() == 0 ) {
            TextView none = (TextView) findViewById(R.id.noResults);
            none.setText("No Results were found!");
        }

        final RestaurantAdapter adapter = new RestaurantAdapter(this, R.layout.row, businesses);
        ListView restListView = (ListView) findViewById(R.id.resList);

        restListView.setAdapter(adapter);
        restListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business item = (Business) adapter.getItem(position);
                Intent intent = new Intent(RecommendationActivity.this, RateActivity.class);
                intent.putExtra("name", item.name());
                intent.putExtra("id", item.id());
                startActivity(intent);
            }
        });


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
                convertView = inflater.inflate(R.layout.row, parent, false);
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

//            try {
                float average = 0;
                if (MainActivity.map.get(businesses.get(position).name()) != null) {
                    average = MainActivity.map.get(businesses.get(position).name());
                    ratingBar.setRating(average);
                } else {
                    ratingBar.setRating(average);
                }
//            } catch (Exception e) {
//
//            }

            if (restaurantIcon != null) {
                new ImageDownloaderTask(restaurantIcon).execute(businesses.get(position).imageUrl());
            }


            return convertView;
        }


    }


    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.foodbackground);
                        imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }
    }


    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

}
