package com.example.seniorproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yelp.clientlib.entities.Business;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sramini on 6/13/17.
 */

public class FeelingLuckyActivity extends Activity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRatingRef = mRootRef.child("restaurants");
    float average = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lucky);
        setFields();

    }

    @Override
    public void onRestart() {
        super.onRestart();
        setFields();
    }

    public void setFields() {
        int index =0;
        Bundle data = getIntent().getExtras();
        ArrayList<Business> businesses = (ArrayList<Business>) data.getSerializable("businesses");
        Random random = new Random();
        index = random.nextInt(businesses.size());
        TextView name = (TextView) findViewById(R.id.nameRestLucky);
        name.setText(businesses.get(index).name());

        ImageView pic = (ImageView) findViewById(R.id.restLucky);
        if (pic != null) {
            new FeelingLuckyActivity.ImageDownloaderTask(pic).execute(businesses.get(index).imageUrl());
        }
//
        RatingBar ratingBar = (RatingBar) findViewById(R.id.restLuckyRating);

        try {
            average = MainActivity.map.get(businesses.get(index).name());
            ratingBar.setRating(average);
        } catch (Exception e) {

        }

        ratingBar.setRating(businesses.get(index).rating().floatValue());



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
