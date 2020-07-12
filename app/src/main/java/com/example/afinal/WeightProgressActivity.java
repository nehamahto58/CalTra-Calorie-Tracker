package com.example.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class WeightProgressActivity extends AppCompatActivity {
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_progress);
        barChart =(BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> AmtOfCal = new ArrayList<>();

        AmtOfCal.add(new BarEntry(945f, 0));
        AmtOfCal.add(new BarEntry(1040f, 1));
        AmtOfCal.add(new BarEntry(1133f, 2));
        AmtOfCal.add(new BarEntry(1240f, 3));
        AmtOfCal.add(new BarEntry(1369f, 4));
        AmtOfCal.add(new BarEntry(1487f, 5));
        AmtOfCal.add(new BarEntry(1501f, 6));


        BarDataSet bardataSet=new BarDataSet(AmtOfCal,"Calories");

        ArrayList<String> week = new ArrayList<String>();

        week.add("Sunday");
        week.add("Monday");
        week.add("Tuesday");
        week.add("Wednesday");
        week.add("Thursday");
        week.add("Friday");
        week.add("Saturday");
        BarData data = new BarData(week, bardataSet);
        barChart.setData(data);
        barChart.setDescription("Your weekly calorie");
        BarDataSet barDataSet = new BarDataSet(AmtOfCal, "Your weekly calorie");
        bardataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);

    }
}

