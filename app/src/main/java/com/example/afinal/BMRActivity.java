package com.example.afinal;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import NonActivityClasses.AppControl;
import NonActivityClasses.InputFilterMinMax;

public class BMRActivity extends AppCompatActivity {
    private EditText heightTxt;
    private EditText weightTxt;
    private Button nextBtn,Calculate;
    TextView result;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        pref = getSharedPreferences(AppControl.USER_PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();

        heightTxt = findViewById(R.id.height);
        weightTxt = findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        Calculate = (Button) findViewById(R.id.calculate);
        nextBtn = findViewById(R.id.btnN);
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float heightValue,weightValue,output;
                heightValue = Float.parseFloat(heightTxt.getText().toString());
                weightValue = Float.parseFloat(weightTxt.getText().toString());
                output=weightValue*10000/(heightValue*heightValue);
                result.setText("" + output);

            }
        });


        heightTxt.addTextChangedListener(watcher);
        weightTxt.addTextChangedListener(watcher);

        // Set min, max input value for weight and height
        heightTxt.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 300, this) });
        weightTxt.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 250, this) });

        heightTxt.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        weightTxt.setTransformationMethod(new NumericKeyBoardTransformationMethod());
    }

    @Override
    protected void onResume() {
        super.onResume();

        float height = pref.getInt("userHeight", 0);
        float weight = pref.getInt("userWeight", 0);

        //If no saved key, instead of showing 0, show empty
        if (height <= 0) {
            heightTxt.setText("");
        } else {
            heightTxt.setText("" + height);
        }

        if (weight <= 0) {
            weightTxt.setText("");
        } else {
            weightTxt.setText("" + weight);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        int defaultH = 0, defaultW = 0;

        if (!TextUtils.isEmpty(AppControl.getText(heightTxt))) {
            defaultH = Integer.parseInt(AppControl.getText(heightTxt));
        }
        if (!TextUtils.isEmpty(AppControl.getText(weightTxt))) {
            defaultW = Integer.parseInt(AppControl.getText(weightTxt));
        }
        prefEditor.putInt("userHeight", defaultH);
        prefEditor.putInt("userWeight", defaultW);
        prefEditor.commit();
    }

    //In order to only allow the user to use numbers only, input type number password is needed
    //So we need to convert the * back to what the user types.
    //https://stackoverflow.com/questions/13817521/edittext-view-with-keyboard-number-only
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    //https://stackoverflow.com/questions/26196770/enable-button-once-all-edittext-fields-are-not-empty
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        //Whenever text is changed, check if all is filled
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(heightTxt.getText().toString().trim())
                    || TextUtils.isEmpty(weightTxt.getText().toString().trim())) {
                nextBtn.setEnabled(false);
            } else {
                nextBtn.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    public void nextActivity(View v) {
        Intent nextActivity = new Intent(this, ActiveLevel.class);
        startActivity(nextActivity);
    }
}
