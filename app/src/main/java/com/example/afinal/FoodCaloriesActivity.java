package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;

import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.Food;
import NonActivityClasses.FoodList;

import static com.example.afinal.MealActivity.TYPE;
import static com.example.afinal.SearchActivity.CALORIES;
import static com.example.afinal.SearchActivity.TITLE;


public class FoodCaloriesActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    private Spinner spin;
    int calorie;
    private String nutriUnit = "mg";
    private Food selectedFood;
    private int numOfServ = 1;
    String title,calories;
    String totalCalorie;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_calories);
        this.setTitle("Food Info");

        Intent intent = getIntent();
        title = intent.getStringExtra(TITLE);
        String d = intent.getStringExtra(CALORIES);
        type = intent.getStringExtra(TYPE);

        String[] cut = d.split("\n");
        String c = cut[0];
        String[] cal = c.split(" ");
        calorie = Integer.parseInt(cal[0]);
        calories = cut[1];
        Log.d("", "updateFoodAdded: "+title+"\n"+calories+c+d+"\n"+calorie);


        pref = getSharedPreferences(AppControl.FOOD_PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();


         // FoodList.getInstance().getFood(index);

        // Take object spinner
        spin = (Spinner)findViewById(R.id.NumOfServ);

        // Create a data source for spinner
        List<String> list = new ArrayList<>();
        for (int x = 1; x <= 100; x++) {
            list.add(String.valueOf(x));
        }

        // Assign data source to Adapter
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);

        //Call to display list for spinner
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numOfServ = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                //selectedFood.setNumOfServ(numOfServ);
                updateFoodAdded();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    public void addFood(View v) {
        /*Calories.getInstance().calcServingCalo(selectedFood);
        prefEditor.putInt("foodAdded", Calories.getInstance().getAddedCalo());
        prefEditor.putInt("foodRemain", Calories.getInstance().calcRemain());
        prefEditor.commit();*/

        Intent nextActivity = new Intent(this, MealActivity.class);
        nextActivity.putExtra(TYPE, type);
        nextActivity.putExtra(CALORIES,totalCalorie);
        startActivity(nextActivity);
    }

    //Multiplied depending on number of servings

    private void updateFoodAdded() {


        ((TextView)findViewById(R.id.tv_FoodName)).setText(title);
        ((TextView)findViewById(R.id.tv_servSize)).setText(calories);
        totalCalorie = String.valueOf(calorie * numOfServ);
        ((TextView)findViewById(R.id.tv_calories)).setText(totalCalorie+ " kcal");

    }
}
