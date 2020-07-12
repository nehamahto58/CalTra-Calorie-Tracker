package com.example.afinal;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    DatePicker simpleDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleDatePicker=(DatePicker)findViewById(R.id.simpleDatePicker);
        Calendar cal=Calendar.getInstance();
        simpleDatePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(CalendarActivity.this,"Date:-"+simpleDatePicker.getDayOfMonth()+"\n"+
                        "Month:-"+(simpleDatePicker.getMonth()+1)+"\n"+"Year:-"+simpleDatePicker.getYear(),Toast.LENGTH_SHORT).show();

            }
        });


    }

}