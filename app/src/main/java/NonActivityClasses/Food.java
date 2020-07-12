package NonActivityClasses;

public class Food {
    private String name;
    private int calories, carbs, protein, fat, fiber, cholesterol, calcium, numOfServ;
    private String servingSize;

    public Food(String name, int calories,  String servingSize) {
        this.name = name;
        this.calories = calories;
        this.servingSize= servingSize;
    }

    public int getCalories() { return calories; }

    public int getNumOfServ(){
        return numOfServ;
    }

    public void setNumOfServ(int amount) { numOfServ = amount; }

    public String getServingSize() {
        return this.servingSize;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
