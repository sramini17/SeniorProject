package com.example.seniorproject;

import java.util.*;

class foodType {
    foodGenre genre;
    String type;
    ArrayList<food> foods = new ArrayList<>();

    public void add(food food) {
        this.foods.add(food);
    }

    public foodType(String type, foodGenre genre) {
        this.type = type;
        this.genre = genre;
    }

    public foodType(String type, foodGenre genre, ArrayList<foodType> allTypes) {
        this.type = type;
        this.genre = genre;
        allTypes.add(new foodType(type, genre));
    }
}
