package FoodOrderingSystem.bin;

import FoodOrderingSystem.Enums.Cuisine;
import FoodOrderingSystem.Enums.Type;

public class Food {

    private Cuisine cuisine;
    private Type type;
    private String name;
    private int price;


    @Override
    public String toString() {
        return "'" + name + '\'' +
                " - " + price;
    }

    public Food() {

        this.cuisine = Cuisine.OTHER;
        this.type = Type.Undefined;
        this.name = "Unknown";

    }

    public Food(Cuisine cuisine, Type type, String name, int price) {
        this.cuisine = cuisine;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
