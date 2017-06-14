package com.example.seniorproject;

import java.util.*;

class foodGenre {
    String genre;
    ArrayList<foodType> foods = new ArrayList<>();

    public void add(foodType type) {
        this.foods.add(type);
    }

    public void printFoods() {
        for (foodType food : this.foods) {
            System.out.println(food.type);
        }
    }

    public foodGenre(String genre) {
        this.genre = genre;
    }
}
