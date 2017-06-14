package com.example.seniorproject;

import java.util.*;

class food {
    foodType type;
    String food;

    public food(String food, foodType type) {
        this.food = food;
        this.type = type;
        
    }

    public food(String food, foodType type, ArrayList<food> allFoods) {
        this.food = food;
        this.type = type;
        allFoods.add(new food(food, type));
    }
}
