package com.example.seniorproject;

import java.util.*;


class addElements {

    public static allFoods addToArray(ArrayList<foodType> allTypes, ArrayList<food> allFoods) {
        allFoods fooders = new allFoods();

        /*********************** CREATE THE GENRES *******************/
        foodGenre Asian = new foodGenre("Asian");
        foodGenre Mexican = new foodGenre("Mexican");
        foodGenre American = new foodGenre("American");
        foodGenre European = new foodGenre("European");

        /*********************** CREATE THE TYPES *********************/
        foodType MX = new foodType("Mexican", Mexican, allTypes);


        foodType Spanish = new foodType("Spanish", European);
        foodType French = new foodType("French", European);
        foodType Irish = new foodType("Irish", European);
        foodType Italian = new foodType("Italian", European);

        foodType Japanese = new foodType("Japanese", Asian);
        foodType Chinese = new foodType("Chinese", Asian);
        foodType Korean = new foodType("Korean", Asian);
        foodType Thai = new foodType("Thai", Asian);
        foodType Indian = new foodType("Indian", Asian);

        foodType AM = new foodType("American", American);

        /************* CREATE THE FOODS **************************/


        food Sushi = new food("Sushi", Japanese);
        food chickenTeriyaki = new food("Chicken Teriyaki", Japanese);
        food udon = new food("Udon", Japanese);

        food burger = new food("Burger", AM);
        food hamburger = new food("Hamburger", AM);
        food cheeseburger = new food("Cheeseburger", AM);
        food pizza = new food("Pizza", AM);

        /********************** ADD GENRES TO ALLFOODS *****************/
        fooders.add(Asian);
        fooders.add(Mexican);
        fooders.add(American);
        fooders.add(European);

        /********************** ADD THE TYPES TO THE GENRES *************/
        Mexican.add(MX);

        European.add(Spanish);
        European.add(French);
        European.add(Irish);
        European.add(Italian);

        Asian.add(Japanese);
        Asian.add(Chinese);
        Asian.add(Korean);
        Asian.add(Thai);
        Asian.add(Indian);

        American.add(AM);

        /******* ADD THE FOODS TO THE TYPE OF FOOD **************/
        Japanese.add(Sushi);
        Japanese.add(chickenTeriyaki);
        Japanese.add(udon);

        AM.add(burger);
        AM.add(hamburger);
        AM.add(cheeseburger);
        AM.add(pizza);

        allTypes.add(MX);
        allTypes.add(Japanese);
        allTypes.add(Chinese);
        allTypes.add(Korean);
        allTypes.add(Spanish);
        allTypes.add(AM);

        allFoods.add(Sushi);
        allFoods.add(chickenTeriyaki);
        allFoods.add(udon);
        allFoods.add(burger);
        allFoods.add(hamburger);
        allFoods.add(cheeseburger);
        allFoods.add(pizza);
        /*for (food food : allFoods) {
            System.out.println(food.type);
        }*/
        return fooders; 
    }
}
