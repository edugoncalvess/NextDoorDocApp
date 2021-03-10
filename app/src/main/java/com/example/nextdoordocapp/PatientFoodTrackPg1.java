package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PatientFoodTrackPg1 extends AppCompatActivity {
    Button addButton;
    Button viewBreakFastList;
    TextView patientGoalCalorie;
    TextView patientFoodConsumedCalories;
    TextView patientRemainingCalorie;
    TextView PatientCalorieAlarm;
    TextView PatientBreakfastTotalCalorie;
    TextView breakFastList;
    EditText patientBreakfastFoodName;
    EditText patientBreakfastFoodCalorie;
    int breakfastTotalCalories = 0;
    String breakfastFoodName;
    int breakfastFoodCalorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int goalCalorie = 2000;
        int foodConsumedCalories =135;
        int remainedCalorie;



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_food_track_pg1);

        //Find alarm label
        PatientCalorieAlarm = findViewById(R.id.txtPatAlarmMessage);

        /*set value to Goal Calorie label*/
        patientGoalCalorie = findViewById(R.id.txtPatGoalCaloryCalculated);
        patientGoalCalorie.setText(String.valueOf(goalCalorie));

        /*set value to Food Consumed Calorie label*/
        patientFoodConsumedCalories = findViewById(R.id.txtPatFoodCalculated);
        patientFoodConsumedCalories.setText(String.valueOf(foodConsumedCalories));

        /*set value to Remaining Calorie label and check the consumed balance*/
        patientRemainingCalorie = findViewById(R.id.txtPatRemainingCalculated);
        remainedCalorie = goalCalorie - foodConsumedCalories;
        patientRemainingCalorie.setText(String.valueOf(remainedCalorie));
        if(remainedCalorie > 0){
            patientRemainingCalorie.setTextColor(Color.parseColor("#009F21"));
        }
        else {
            patientRemainingCalorie.setTextColor(Color.parseColor("#DC0000"));
            PatientCalorieAlarm.setText("Be Careful you passed your Goal!!");
        }

        //Breakfast foodName
        patientBreakfastFoodName = findViewById(R.id.inptPatFoodName);

        //Breakfast foodCalorie
        patientBreakfastFoodCalorie = findViewById(R.id.inptPatFoodCalorie);


        //set value for total breakfast calories
        PatientBreakfastTotalCalorie = findViewById(R.id.txtPatBreakfastCaloryCalculated);

       // SharedPreferences preferences = getSharedPreferences("myPref",MODE_PRIVATE);

        addButton=findViewById(R.id.btnPatAddFoodToBreakfast);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PatientFoodTrackPg1.this,PatientFoodSearch.class));
                breakfastTotalCalories = breakfastTotalCalories + Integer.parseInt(String.valueOf(patientBreakfastFoodCalorie.getText()));
                PatientBreakfastTotalCalorie.setText(String.valueOf(breakfastTotalCalories));

                //Using Preference which is not useful here
               /* breakfastFoodName = patientBreakfastFoodName.getText().toString();
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("key1",breakfastFoodName);
                editor.putInt("key2",breakfastFoodCalorie);
                editor.commit();


                String name = preferences.getString("key1","");
                int calorie = preferences.getInt("key2",0);

                TextView txtTest = findViewById(R.id.textView5);
                txtTest.setText(name + calorie);*/

                breakfastFoodName = patientBreakfastFoodName.getText().toString();
                breakfastFoodCalorie = Integer.parseInt(patientBreakfastFoodCalorie.getText().toString());
                try {
                    FileOutputStream fout= openFileOutput("breakfastFile.txt",MODE_APPEND);
                    fout.write(breakfastFoodName.getBytes());
                    fout.write(breakfastFoodCalorie);
                    fout.write("\n".getBytes());
                    fout.close();
                    Toast.makeText(PatientFoodTrackPg1.this,"Data Saved",Toast.LENGTH_LONG).show();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }


            }
        });

        viewBreakFastList = findViewById(R.id.btnPatViewBreakfast);
        breakFastList = findViewById(R.id.txtPatBreakfastList);
        viewBreakFastList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput("breakfastFile.txt");
                    String temp="";
                    int c;
                    char ch;
                    while ((c=fin.read()) != -1){
                        ch = (char) c;
                        temp += Character.toString(ch);
                    }
                    breakFastList.setText(temp);
                    fin.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}