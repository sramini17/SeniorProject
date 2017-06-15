package com.example.seniorproject;

import java.util.*;
//import java.util.stream.Collectors;

class test {
    public static ArrayList<foodType> allTypes = new ArrayList<>();
    public static ArrayList<food> allFoods = new ArrayList<>();

    public test() {

    }

    public static String getGenre(ArrayList<foodGenre> genres, String input) {
        Random rand = new Random();
        int index = rand.nextInt(genres.size());

        while (genres.get(index).genre.equals(input)) {
            index = rand.nextInt(genres.size());
        }
        //System.out.println(genres.get(index).genre);
        return genres.get(index).genre;
    }


    public static String getType(ArrayList<foodType> types, String input) {
        Random rand = new Random();
        int index = rand.nextInt(types.size());

        while (types.get(index).type.equals(input) && types.size() > 1) {
            index = rand.nextInt(types.size());
            //System.out.println("IN WHILE");
        }
        //System.out.println(types.get(index).type);
        return types.get(index).type;
    }


    public static String getFood(ArrayList<food> foods, String input) {
        Random rand = new Random();
        int index = rand.nextInt(foods.size());

        while (foods.get(index).food.equals(input) && foods.size() > 1) {
            index = rand.nextInt(foods.size());
        }
        //System.out.println(foods.get(index).food);
        return foods.get(index).food;
    }



    public static String createRec(allFoods foods, String input) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Category: ");
//        String input = sc.nextLine();

        /*********** START AT TOP MOST LEVEL --> CHECKING IF ASIAN/MEXICAN ****************/
        for (foodGenre genre : foods.genres) {
            if (genre.genre.equals(input)) {
                return getGenre(foods.genres, input);
                //return;
            }

        }

        /********** IF IT IS NOT A GENRE... CHECK TO SEE IF IT IS A TYPE **************/
        for (foodType type : allTypes) {
            if (input.equals(type.type)) {

                //return getType(allTypes, input);
//                List<foodGenre> t =
//                        foods.genres
//                                .stream()
//                                .filter(p -> p.genre.equals((type.genre.genre)))
//                                .collect(Collectors.toList());

                //CHANGE FOODS TO FOODTYPES
//                ArrayList<foodType> typoos = t.get(0).foods;
                ArrayList<foodType> arr = new ArrayList<>();
                for (foodType ft : allTypes) {
                    if (type.genre.genre.equals(ft.genre.genre)) {
                        foodType temp = new foodType(ft.type, new foodGenre(type.genre.genre));
                        arr.add(temp);
                    }
                }
                return getType(arr, input);
                /*for (foodType ft : typoos) {
                    System.out.println("FOODTYPE: " + ft.type);
                }*/
                //return getType(typoos, input);
                //return;
            }
        }

        for (food food : allFoods) {
            if (input.equals(food.food)) {
//                List<foodType> t =
//                        allTypes
//                                .stream()
//                                .filter(p -> p.type.equals(food.type.type))
//                                .collect(Collectors.toList());


                /*for (foodType type1 : t) {
                    System.out.println(type1.type);
                }*/
                ArrayList<food> arr = new ArrayList<>();
                for (food tempFood : allFoods) {
                    //System.out.println("IN THE FOR LOOP");
                    if (food.type.type.equals(tempFood.type.type)) {
                        //food temp = new food(input, food.type.type);
                        //System.out.println(tempFood.food);
                        arr.add(tempFood);
                    }
                }
                return getFood(arr, input);

                //ArrayList<food> foodies = t.get(0).foods;

                /*for (food food : foodies) {
                    System.out.println("Food "+ food.type);
                }*/
                //return getFood(foodies, input);

                //return;
            }
        }

        return input;
    }

    public static void main(String[] args) {
//        allFoods food = addElements.addToArray(allTypes, allFoods);


//        while (true)
//            System.out.println(createRec(food, input));
    }

}

