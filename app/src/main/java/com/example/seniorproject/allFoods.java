package com.example.seniorproject;

import java.util.*;

class allFoods {
    ArrayList<foodGenre> genres = new ArrayList<>();

    public void add(foodGenre genre) {
        this.genres.add(genre);
    }

    public void printFoods() {
        for (foodGenre genre : this.genres) {
            System.out.println(genre.genre);
        }
    }

    public allFoods() {

    }

}
