package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.SharedPreferences;
import java.util.Calendar;

import NonActivityClasses.AlarmReceiver;
import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.UserInfo;

import java.text.DateFormat;

import static com.example.afinal.SearchActivity.CALORIES;


public class MealActivity extends AppCompatActivity {
    TextView textView;
    Button button,button1;
    ImageButton img1,img2,img3,img4;
    private SharedPreferences userPref, foodPref;
    private SharedPreferences.Editor userPrefEditor, foodPrefEditor;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private TextView caloGoal;
    private TextView caloAdded;
    private TextView caloRemain;

    private TextView bcal,lcal,scal,dcal;

    int bc=0,lc=0,sc=0,dc=0;

    String foodAdd;


    private UserInfo user = UserInfo.getInstance();

    private static final int code1 = 1;
    private static final int code2 = 2;

    public static final String TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        Intent intent = getIntent();
        String calories = intent.getStringExtra(CALORIES);
        String type = intent.getStringExtra(TYPE);


        textView = (TextView) findViewById(R.id.simpleCalendarView);
        button = (Button) findViewById(R.id.update);
        button1=(Button) findViewById(R.id.viewprogress);
        img1 = (ImageButton)findViewById(R.id.image1);
        img2 = (ImageButton)findViewById(R.id.image2);
        img3 = (ImageButton)findViewById(R.id.image3);
        img4 = (ImageButton)findViewById(R.id.image4);


        img1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MealActivity.this, SearchActivity.class);
                intent.putExtra(TYPE,"breakfast");
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( MealActivity.this, SearchActivity.class);
                intent1.putExtra(TYPE,"lunch");
                startActivity(intent1);
            }
        });
        img3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( MealActivity.this, SearchActivity.class);
                intent2.putExtra(TYPE,"snacks");
                startActivity(intent2);
            }
        });
        img4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent( MealActivity.this, SearchActivity.class);
                intent3.putExtra(TYPE,"dinner");
                startActivity(intent3);
            }
        });

        userPref = getSharedPreferences(AppControl.USER_PREF, Activity.MODE_PRIVATE);
        foodPref = getSharedPreferences(AppControl.FOOD_PREF, Activity.MODE_PRIVATE);
        userPrefEditor = userPref.edit();
        foodPrefEditor = foodPref.edit();

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        bcal = findViewById(R.id.bcal);
        lcal = findViewById(R.id.lcal);
        scal = findViewById(R.id.scal);
        dcal = findViewById(R.id.dcal);

        SharedPreferences shared = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

        if(type!=null) {
            switch (type) {

                case "breakfast":
                    editor.putInt("bcal", Integer.parseInt(calories));
                    editor.apply();

                    break;
                case "lunch":
                    editor.putInt("lcal", Integer.parseInt(calories));
                    editor.apply();
                    break;
                case "snacks":
                    editor.putInt("scal", Integer.parseInt(calories));
                    editor.apply();
                    break;
                case "dinner":
                    editor.putInt("dcal", Integer.parseInt(calories));
                    editor.apply();
                    break;
            }
        }

        SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        bc = preferences.getInt("bcal",0);
        bcal.setText(String.valueOf(bc));
        lc = preferences.getInt("lcal",0);
        lcal.setText(String.valueOf(lc));
        sc = preferences.getInt("scal",0);
        scal.setText(String.valueOf(sc));
        dc = preferences.getInt("dcal",0);
        dcal.setText(String.valueOf(dc));

        foodAdd = String.valueOf(bc+lc+sc+dc);


        Calendar c = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textView.setText(currentDate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(MealActivity.this,CalendarActivity.class);
                startActivity(j);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent im = new Intent(MealActivity.this, WeightProgressActivity.class);
                startActivity(im);
            }
        });

            if(userPref.getInt("userGoalVal", 0) == 0) {
            toBasicInfo();
        }
        else {
            //If there are PendingIntents that match code, context, and intent, return null, otherwise return the pending intent
            //then check if the return is not equal to null
            //https://stackoverflow.com/questions/4556670/how-to-check-if-alarmmanager-already-has-an-alarm-set/9575569#9575569
            boolean alarmUp1 = (PendingIntent.getBroadcast(this, code1,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            boolean alarmUp2 = (PendingIntent.getBroadcast(this, code2,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            //Check whether alarm is existed or not
            if (!(alarmUp1 || alarmUp2)) {
                setAlarm(true);     //Set the alarm for midnight reset
                setAlarm(false);    //Set the alarm for 10p.m. notification
            }
            setUserValue();
            print();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Integer.parseInt(caloGoal.getText().toString()) != 0) {
            foodPrefEditor.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
            foodPrefEditor.putInt("foodAdded", Integer.parseInt(caloAdded.getText().toString()));
            foodPrefEditor.putInt("foodRemain", Integer.parseInt(caloRemain.getText().toString()));
            foodPrefEditor.commit();
        }
    }


  /* public void addFood(View v) {
        Intent nextActivity = new Intent(this, SearchActivity.class);
        startActivity(nextActivity);
    }*/

    public void editInfo(View v) {
        toBasicInfo();
    }

    public void print(){
        int foodAdded = foodPref.getInt("foodAdded", 0);
        Calories.getInstance().setAddedCalo(foodAdded);

        caloGoal.setText(String.valueOf(Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(foodAdd));
        int remain = Calories.getInstance().maxCalo()-Integer.parseInt(foodAdd);
        //caloRemain.setText(String.valueOf(Calories.getInstance().calcRemain()));
        caloRemain.setText(String.valueOf(remain));


    }

    private void setUserValue() {
        //Everytime opeing the app, set all the userinfo to run functions.
        user.setAge(userPref.getInt("userAge", 0));
        user.setGender(userPref.getString("userGenderText", ""));
        user.setHeight(userPref.getInt("userHeight", 0));
        user.setWeight(userPref.getInt("userWeight", 0));
        user.setActiveStatus(userPref.getFloat("userActiveVal", 0));
        user.setGoalStatus(userPref.getInt("userGoalVal", 0));
    }

    private void toBasicInfo() {
        Intent nextActivity = new Intent(this, BasicInfo.class);
        startActivity(nextActivity);
    }

    //https://developer.android.com/training/scheduling/alarms.html
    public void setAlarm( boolean isMidnight ) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("isMidnight", isMidnight);
        alarmIntent = PendingIntent.getBroadcast(this, isMidnight ? code1 : code2, intent, PendingIntent.FLAG_UPDATE_CURRENT);  //Need to use separate code because if the same code, it replaces the old PendingIntent

        Calendar calendar = Calendar.getInstance();
        //Set specific time for alarm
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, isMidnight ? 1 : 0);    //since 12a.m. is for the next day, we need to add 1 to the date, but none for 10p.m.
        calendar.set(Calendar.HOUR_OF_DAY, isMidnight ? 0 : 22);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        alarmMgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);


    }
}

