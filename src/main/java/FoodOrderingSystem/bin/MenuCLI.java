package FoodOrderingSystem.bin;

import FoodOrderingSystem.Enums.Cuisine;
import FoodOrderingSystem.Enums.Type;

import java.util.*;
import java.util.stream.Collectors;



public class MenuCLI {

    private static Scanner scanner= new Scanner(System.in);

    //Hardcoded menu: add as much dishes as you would like
    private static List<Food> offer = new ArrayList<Food>(List.of(
            new Food(Cuisine.POLISH, Type.MAIN, "Pierogi", 10),
            new Food(Cuisine.POLISH, Type.MAIN, "Zupa", 7),
            new Food(Cuisine.ITALIAN, Type.MAIN, "Pasta Carbonara", 11),
            new Food(Cuisine.ITALIAN, Type.MAIN, "Ginestrata", 10),
            new Food(Cuisine.MEXICAN, Type.MAIN, "Salsa", 6),
            new Food(Cuisine.MEXICAN, Type.MAIN, "Menudo", 7),

            new Food(Cuisine.POLISH, Type.DESSERT, "Nalesniki", 8),
            new Food(Cuisine.POLISH, Type.DESSERT, "Szarlotka", 11),
            new Food(Cuisine.ITALIAN, Type.DESSERT, "Biscotti", 6),
            new Food(Cuisine.ITALIAN, Type.DESSERT, "Budino", 7),
            new Food(Cuisine.MEXICAN, Type.DESSERT, "Churros", 6),
            new Food(Cuisine.MEXICAN, Type.DESSERT, "Sopapilla", 13),

            new Food(Cuisine.OTHER, Type.DRINK, "Coffee", 3),
            new Food(Cuisine.OTHER, Type.DRINK, "Tea", 2),
            new Food(Cuisine.OTHER, Type.DRINK, "Water", 1),
            new Food(Cuisine.OTHER, Type.DRINK, "Lemonade", 3)
    ));

    public static void runMenu(){
        int answer;
        answer = menuPhase("Welcome. Would you like a lunch or a drink?", List.of("Lunch", "Drink"));

        if (answer == 1) {

            // Pick a cuisine, then make a lunch. Adding new cuisines and dishes wouldn`t require any algorithm changes.
            List<String> cuisines = Arrays.stream(Cuisine.values()).map(Cuisine::name).
                    filter(s -> !"OTHER".equals(s)).collect(Collectors.toList());

            answer = menuPhase("Which cuisine would you like?", cuisines);


            final int csn = answer;
            List<String> mainDishes = offer.stream().
                    filter(food -> food.getCuisine().equals(Cuisine.valueOf(cuisines.get(csn - 1)))).
                    filter(food -> food.getType().equals(Type.MAIN)).
                    map(Food::toString).collect(Collectors.toList());

            List<String> desserts = offer.stream().
                    filter(food -> food.getCuisine().equals(Cuisine.valueOf(cuisines.get(csn - 1)))).
                    filter(food -> food.getType().equals(Type.DESSERT)).
                    map(Food::toString).collect(Collectors.toList());


            // Variable 'answer', here and further, could be used to calculate total price and print a 'receipt'.
            // To add that I would think of a way to split this if/else in different methods to make code more readable
            answer = menuPhase("Please, pick your main course:", mainDishes);
            answer = menuPhase("Please, pick your dessert:", desserts);

            System.out.println("Thank you for your order!");


        }
        else{
            List<String> drinks = offer.stream().filter(food -> food.getType() == Type.DRINK).
                    map(Food::toString).
                    collect(Collectors.toCollection(ArrayList::new));

            answer = menuPhase("Pick your drink please:", drinks);

            answer = menuPhase("Would you like a lemon or ice in your dink?", List.of("Lemon", "Ice", "Both", "Nothing"));

            System.out.println("Thank you for your order!");

        }



    }

    //Unified Restaurant CLI: makes it possible to harmlessly add cuisines and dishes
    private static int menuPhase(String question, List<String> options){
        int answer = 0;
        boolean firstTime = true;

        while (answer < 1 || answer > options.size()) {

            clearScreen();
            if (!firstTime) System.out.println("Please pick by typing a corresponding number");
            System.out.println(question);


            for (int i = 0; i < options.size(); i++) {
                System.out.println(i + 1 + "  -  " + options.get(i) + ";");
            }

            if (scanner.hasNextInt()){
                answer = scanner.nextInt();
            } else {
                scanner.next();
                answer = 0;
            }

            firstTime = false;
        }

        return answer;
    }

    //Doesnt work in IDE. Maybe in console?
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
