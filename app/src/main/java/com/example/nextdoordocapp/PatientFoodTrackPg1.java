package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PatientFoodTrackPg1 extends AppCompatActivity {
    Button addButton;
    Button viewBreakFastList;
    Button viewLunchList;
    Button viewDinnerList;
    TextView patientGoalCalorie;
    TextView patientFoodConsumedCalories;
    TextView patientRemainingCalorie;
    TextView PatientCalorieAlarm;
    TextView PatientBreakfastTotalCalorie;
    TextView PatientLunchTotalCalorie;
    TextView PatientDinnerTotalCalorie;
    TextView breakFastList;
    TextView lunchList;
    TextView dinnerList;
    EditText patientBreakfastFoodName;
    EditText patientBreakfastFoodCalorie;
    EditText patientLunchFoodName;
    EditText patientLunchFoodCalorie;
    EditText patientDinnerFoodName;
    EditText patientDinnerFoodCalorie;
    int breakfastTotalCalories = 0;
    int LunchTotalCalories = 0;
    int DinnerTotalCalories = 0;
    String breakfastFoodName;
    String lunchFoodName;
    String DinnerFoodName;
    int breakfastFoodCalorie;
    int lunchFoodCalorie;
    int dinnerFoodCalorie;
    Boolean breakfastViewStatus = false;
    Boolean lunchViewStatus = false;
    Boolean dinnerViewStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int goalCalorie = 2000;
        int foodConsumedCalories =135;
        int remainedCalorie;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_food_track_pg1);

        //select breakfast cardView
        CardView crdViewPatFoodTrackBreakfast = findViewById(R.id.crdViewPatFoodTrackBreakfast);

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
        PatientBreakfastTotalCalorie.setText("0");

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
                    //fout.write(breakfastFoodCalorie);
                    fout.write("\n".getBytes());
                    fout.close();
                    Toast.makeText(PatientFoodTrackPg1.this,"Data Saved",Toast.LENGTH_LONG).show();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }

                patientBreakfastFoodName.setText("");
                patientBreakfastFoodCalorie.setText("");

            }
        });

        viewBreakFastList = findViewById(R.id.btnPatViewBreakfast);
        breakFastList = findViewById(R.id.txtPatBreakfastList);
        viewBreakFastList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(breakfastViewStatus==false){

                    try {
                        FileInputStream fin = openFileInput("breakfastFile.txt");
                        String temp="";
                        int c;
                        char ch;
                        while ((c=fin.read()) != -1){
                            ch = (char) c;
                            temp += Character.toString(ch);
                        }
                        breakFastList.setVisibility(View.VISIBLE);
                        breakFastList.setText(temp);
                        fin.close();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                    breakfastViewStatus =true;
                    viewBreakFastList.setText(R.string.txtPatCloseView);


                }
                else if(breakfastViewStatus==true){
                    breakfastViewStatus =false;
                    viewBreakFastList.setText(R.string.view);
                    breakFastList.setVisibility(View.INVISIBLE);


                }

            }

        });


         //Lunch settings
        //Lunch foodName
        patientLunchFoodName = findViewById(R.id.inptPatLunchFoodName);

        //Lunch foodCalorie
        patientLunchFoodCalorie = findViewById(R.id.inptPatLunchFoodCalorie);


        //set value for total breakfast calories
        PatientLunchTotalCalorie = findViewById(R.id.txtPatLunchCaloryCalculated);
        PatientLunchTotalCalorie.setText("0");


        Button addLunchButton=findViewById(R.id.btnPatAddFoodToLunch);
        addLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LunchTotalCalories = LunchTotalCalories + Integer.parseInt(String.valueOf(patientLunchFoodCalorie.getText()));
                PatientLunchTotalCalorie.setText(String.valueOf(LunchTotalCalories));

                lunchFoodName = patientLunchFoodName.getText().toString();
                lunchFoodCalorie = Integer.parseInt(patientLunchFoodCalorie.getText().toString());
                try {
                    FileOutputStream foutLunch= openFileOutput("LunchFile.txt",MODE_APPEND);
                    foutLunch.write(lunchFoodName.getBytes());
                    //fout.write(breakfastFoodCalorie);
                    foutLunch.write("\n".getBytes());
                    foutLunch.close();
                    Toast.makeText(PatientFoodTrackPg1.this,"Data Saved",Toast.LENGTH_LONG).show();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }

                patientLunchFoodName.setText("");
                patientLunchFoodCalorie.setText("");

            }
        });

        viewLunchList = findViewById(R.id.btnPatViewLunch);
        lunchList = findViewById(R.id.txtPatLunchList);
        viewLunchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lunchViewStatus==false){

                    try {
                        FileInputStream finLunch = openFileInput("lunchFile.txt");
                        String tempLunch="";
                        int cLunch;
                        char chLunch;
                        while ((cLunch=finLunch.read()) != -1){
                            chLunch = (char) cLunch;
                            tempLunch += Character.toString(chLunch);
                        }
                        lunchList.setVisibility(View.VISIBLE);
                        lunchList.setText(tempLunch);
                        finLunch.close();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                    lunchViewStatus =true;
                    viewLunchList.setText(R.string.txtPatCloseView);
                }
                else if(lunchViewStatus==true){
                    lunchViewStatus =false;
                    viewLunchList.setText(R.string.view);
                    lunchList.setVisibility(View.INVISIBLE);
                }

            }

        });


        //Dinner settings
        //Dinner foodName
        patientDinnerFoodName = findViewById(R.id.inptPatDinnerFoodName);

        //Lunch foodCalorie
        patientDinnerFoodCalorie = findViewById(R.id.inptPatDinnerFoodCalorie);


        //set value for total breakfast calories
        PatientDinnerTotalCalorie = findViewById(R.id.txtPatDinnerCaloryCalculated);
        PatientDinnerTotalCalorie.setText("0");

        Button addDinnerButton=findViewById(R.id.btnPatAddFoodToDinner);
        addDinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DinnerTotalCalories = DinnerTotalCalories + Integer.parseInt(String.valueOf(patientDinnerFoodCalorie.getText()));
                PatientDinnerTotalCalorie.setText(String.valueOf(DinnerTotalCalories));

                DinnerFoodName = patientDinnerFoodName.getText().toString();
                dinnerFoodCalorie = Integer.parseInt(patientDinnerFoodCalorie.getText().toString());
                try {
                    FileOutputStream foutDinner= openFileOutput("DinnerFile.txt",MODE_APPEND);
                    foutDinner.write(DinnerFoodName.getBytes());
                    //fout.write(breakfastFoodCalorie);
                    foutDinner.write("\n".getBytes());
                    foutDinner.close();
                    Toast.makeText(PatientFoodTrackPg1.this,"Data Saved",Toast.LENGTH_LONG).show();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }

                patientDinnerFoodName.setText("");
                patientDinnerFoodCalorie.setText("");

            }
        });

        viewDinnerList = findViewById(R.id.btnPatViewDinner);
        dinnerList = findViewById(R.id.txtPatDinnerList);
        viewDinnerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dinnerViewStatus==false){

                    try {
                        FileInputStream finDinner = openFileInput("dinnerFile.txt");
                        String tempDinner="";
                        int cDinner;
                        char chDinner;
                        while ((cDinner=finDinner.read()) != -1){
                            chDinner = (char) cDinner;
                            tempDinner += Character.toString(chDinner);
                        }
                        dinnerList.setVisibility(View.VISIBLE);
                        dinnerList.setText(tempDinner);
                        finDinner.close();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                    dinnerViewStatus =true;
                    viewDinnerList.setText(R.string.txtPatCloseView);
                }
                else if(dinnerViewStatus==true){
                    dinnerViewStatus =false;
                    viewDinnerList.setText(R.string.view);
                    dinnerList.setVisibility(View.INVISIBLE);
                }

            }

        });
    }
}