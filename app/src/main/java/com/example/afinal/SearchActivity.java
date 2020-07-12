package com.example.afinal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.afinal.MealActivity.TYPE;

public class SearchActivity extends AppCompatActivity {
    FloatingActionButton fab = findViewById(R.id.floatingActionButton);
    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();
    public static final String TITLE = "title";
    public static final String CALORIES = "calories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Intent intent = getIntent();
        String type = intent.getStringExtra(TYPE);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Food Items");

        title = new String[]{"Milk Tea","Black Tea","Milk Coffee","Black Coffee","Boiled eggs","Puri Tarkari","Jeri Swari","White Bread","Brown Bread","Omelette","White Rice","Brown Rice","Matar","Gundruk","Pumpkin","Chicken Biryani","Veg Biryani","Potato","Chicken Curry","Rajma","Chana Gravy","Buff Curry","Fish Curry","Moong Daal","Musuri Daal","Munta","Kalo Daal","Dhido","Barela","Brocolli","Cauli","Cabbage","Palungo","Karela","Brinjal","Bodi","Maida Roti","Atta Roti","Cucumber","Chukundar","Mango","Apple","Strawberry","Banana","Pineapple","Dosa","Veg Momo","Chicken Momo","Buff Momo","Chicken Chowmein","Buff Chowmein","Veg Chowmein","Samosa","Pani puri","Chatpate","Khuwa Barfi","Kaju Barfi","Icecream","Brownie","Motichurko laddo","juice","coke","sprite"};
        description = new String[]{"22 calories \n1cup", "7 calories \n1cup", "18 calories \n1cup", "5 calories \n1cup", "70 calories \n1 large","265 calories \n1 plate","328 calories \n1 plate","28 calories \n1 slice"," 34 calories \n1 slice","90 calories \n1 plate","130 calories \n100gm","123 calories \n100gm","319 calories \n100gm"," 19 Calories\n1bowl ","65 calories \n100gm","139 calories \n100gm","130 calories \n100gm","97 calories \n100gm ","590 calories \n100gm ","346 calories \n100gm"," 360 calories \n100gm","330 calories \n100gm","230 calories \n100gm","348 calories \n100gm","335 calories \n100gm","53 calories \n100gm","345 calories \n100gm","415 calories \n100gm","33 calories \n100gm","50 calories \n100gm","41 calories \n100gm","27 calories \n100gm","26 calories \n100gm","25 calories \n100gm","24 calories \n100gm","26 calories \n100gm","95 calories \n1pc","66 calories \n1pc","13 calories \n100gm","43 calories \n100gm","128 calories \n100gm","95 calories \n100gm","86 calories \n100gm","92 calories \n100gm","82 calories \n100gm","230 calories \n100gm","280 calories \n1plate","340 calories \n1plate","350 calories \n1 plate","325 calories \n1 plate","315 calories \n1plate","300 calories \n1 plate"," 275 calories \n1 piece","192 calories \n1 plate","180 calories \n100gm","140 calories \n1 pc","185 calories \n1 pc","137 calories \n2 scoop","227 calories \n1pc","150 calories \n1pc","35 calories \n1 glass","101 calories \n250ml","101 calories \n250ml"};
        icon = new int[]{R.drawable.milk_tea, R.drawable.black_tea, R.drawable.milk_coffee, R.drawable.black_coffee, R.drawable.boiledeggs,R.drawable.puritarkari,R.drawable.jeri_swari,R.drawable.white_bread,R.drawable.brownbread,R.drawable.omlette,R.drawable.white_rice,R.drawable.brownrice,R.drawable.matar,R.drawable.gundruk,R.drawable.pumpkin,R.drawable.biryani,R.drawable.biryani,R.drawable.aloo,R.drawable.chicken,R.drawable.rajma,R.drawable.chana,R.drawable.buff_curry,R.drawable.fishcurry,R.drawable.moog_dal,R.drawable.masoor_dal,R.drawable.muntz,R.drawable.kalodaal,R.drawable.dhido,R.drawable.barela,R.drawable.broccoli,R.drawable.ca,R.drawable.cabbage,R.drawable.palun,R.drawable.karela,R.drawable.brinjal,R.drawable.bodi,R.drawable.maida_roti,R.drawable.atta_roti,R.drawable.cucumber,R.drawable.chukandar,R.drawable.mango,R.drawable.apple,R.drawable.strawberry,R.drawable.banana,R.drawable.pineapple,R.drawable.dosa,R.drawable.veg_mom,R.drawable.chicken_momo,R.drawable.chicken_momo,R.drawable.vegchowmein,R.drawable.vegchowmein,R.drawable.vegchowmein,R.drawable.samosa,R.drawable.panipuri,R.drawable.chaatpate,R.drawable.khuwa_barfi,R.drawable.kaju_barfi,R.drawable.icecream,R.drawable.brownie,R.drawable.motichur_laddu,R.drawable.juice,R.drawable.coke,R.drawable.sprite};

        listView = findViewById(R.id.listView);

        for (int i =0; i<title.length; i++){
            Model model = new Model(title[i], description[i], icon[i], type);
            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("", "onItemClick: "+title[i]+"\n"+description[i]);
                Intent nextActivity = new Intent( SearchActivity.this, FoodCaloriesActivity.class);
                nextActivity.putExtra(TITLE, title[i]);
                nextActivity.putExtra(CALORIES,description[i]);
                startActivity(nextActivity);

            }
        });*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SearchActivity.this, FoodCaloriesActivity.class);
                startActivity(in);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

