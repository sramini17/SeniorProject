package com.example.seniorproject;

import java.util.*;


class addElements {

    public static allFoods addToArray(ArrayList<foodType> allTypes, ArrayList<food> allFoods) {
        allFoods fooders = new allFoods();

        /*********************** CREATE THE GENRES *******************/
        foodGenre Asian = new foodGenre("asian");
        foodGenre Mexican = new foodGenre("mexican");
        foodGenre American = new foodGenre("american");
        foodGenre European = new foodGenre("european");

        /*********************** CREATE THE TYPES *********************/
        foodType MX = new foodType("mexican", Mexican, allTypes);


        foodType Spanish = new foodType("spanish", European);
        foodType French = new foodType("french", European);
        foodType Irish = new foodType("irish", European);
        foodType Italian = new foodType("italian", European);

        foodType Japanese = new foodType("japanese", Asian);
        foodType Chinese = new foodType("chinese", Asian);
        foodType Korean = new foodType("korean", Asian);
        foodType Thai = new foodType("thai", Asian);
        foodType Indian = new foodType("indian", Asian);

        foodType AM = new foodType("american", American);

        /************* CREATE THE FOODS **************************/


        food Sushi = new food("sushi", Japanese);
        food chickenTeriyaki = new food("chicken teriyaki", Japanese);
        food udon = new food("udon", Japanese);

        food burger = new food("burger", AM);
        food hamburger = new food("hamburger", AM);
        food cheeseburger = new food("cheeseburger", AM);
        food pizza = new food("pizza", AM);

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
        allTypes.add(Indian);
        allTypes.add(Thai);
        allTypes.add(Spanish);
        allTypes.add(AM);
        allTypes.add(French);
        allTypes.add(Irish);
        allTypes.add(Italian);

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
