package NonActivityClasses;


import java.util.ArrayList;
import java.util.List;

import NonActivityClasses.Food;

public class FoodList {
    private List<Food> foodList;
    private static final FoodList listInstance = new FoodList();

    public static FoodList getInstance() {
        return listInstance;
    }

    public void add() {
        foodList = new ArrayList<Food>();
        foodList.add(new Food("Beef",259,"4oz"));
        foodList.add(new Food("Pork",130,"4oz"));
        foodList.add(new Food("Chicken Breast",94,"3oz"));
        foodList.add(new Food("Brocolli",31,"100g"));
        foodList.add(new Food("Salmon",100,"4oz"));
        foodList.add(new Food("Banana",105,"1 banana"));
        foodList.add(new Food("Rice",150,"0.8 cup cooked"));
        foodList.add(new Food("Bread",200,"1 slice"));
        foodList.add(new Food("Eggs",72,"1 large"));
        foodList.add(new Food("Pasta",200,"2 ounces"));
    }

    public List<Food> getFoodList() {
        return this.foodList;
    }

    public Food getFood(int index) {
        return this.foodList.get(index);
    }
}