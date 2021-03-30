package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PatientFoodTrackPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Button addButton;
    Button viewFoodList;
    Button saveFinalInfo;
    TextView patientGoalCalorie;
    TextView patientFoodConsumedCalories;
    TextView patientRemainingCalorie;
    TextView PatientCalorieAlarm;
    TextView PatientFoodTotalCalorie;
    TextView PatientFoodTotal2Calorie;
    TextView foodList;
    EditText patientFoodName;
    EditText patientFoodCalorie;
    int foodsTotalCalories = 0;
    String FoodName;
    int FoodCalorie;

    Boolean foodViewStatus = false;

    int goalCalorie = 0;
    int foodConsumedCalories = 0;
    int currentFoodConsumedCalories = 0;
    double remainedCalorie;

    String gender;
    int height;
    int weight;
    String birthDate;
    Date ConvertedBirthDate;
    int age;
    int checkedPatientId;
    String checkedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_food_track_pg1);

        Intent foodTrackIntent = getIntent();
        int patientId = getIntent().getIntExtra("patientId",0);
        Log.d("This is User id" , String.valueOf(patientId));


        //add today Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTime = sdf.format(new Date());

        //add database
        databaseHelper = new DatabaseHelper(this);
        //databaseHelper. addRecordPatientTest();

        //set value for total food calories
        PatientFoodTotalCalorie = findViewById(R.id.txtPatFoodCalculated);
        PatientFoodTotalCalorie.setText("0");


        /*set value to Food Consumed Calorie label*/
       // patientFoodConsumedCalories = findViewById(R.id.txtPatFoodCalculated);
        //patientFoodConsumedCalories.setText(String.valueOf(foodConsumedCalories));


        // get Patient information
        Cursor patientDetailCursor = databaseHelper.getPatientWeightHeightGender(patientId);
        if(patientDetailCursor.getCount()>0){
            while (patientDetailCursor.moveToNext()){
                gender = patientDetailCursor.getString(0);
                weight = Integer.parseInt(patientDetailCursor.getString(1));
                height = Integer.parseInt(patientDetailCursor.getString(2));
                birthDate = patientDetailCursor.getString(3);
                try {
                    //chnage Birthdate format
                    ConvertedBirthDate =new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);

                    //read Year from String
                    String firstFourChars = "";
                    firstFourChars = birthDate.substring(0, 4);

                    //Find this year
                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    //change Birthdaye to age
                     age = year - Integer.parseInt(firstFourChars);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //Calculate Goal Calorie for Patient
           if(gender.equals("Female")){
               double FBMR= 655 + (4.3 * (weight * 2.205)) + (4.7 * (height / 2.54)) - (4.7 * age);
               goalCalorie = (int)(FBMR * 1.55);
           }
           else{
               double MBMR =  66 + (6.3 * (weight * 2.205)) + (12.9 * (height / 2.54)) - (6.8 * age);
               goalCalorie = (int)(MBMR * 1.55);
           }

        }

        Cursor patientDailyCalorieAmount = databaseHelper.getPatientDailyCalorieAmount(patientId,currentDateTime);
        if(patientDailyCalorieAmount.getCount() > 0){
            while (patientDailyCalorieAmount.moveToNext()){
                PatientFoodTotalCalorie.setText(patientDailyCalorieAmount.getString(0));
            }
        }


        //select food items cardView
        CardView crdViewPatFoodTrackItems = findViewById(R.id.crdViewPatFoodTrack);

        //Find alarm label
        PatientCalorieAlarm = findViewById(R.id.txtPatAlarmMessage);

        /*set value to Goal Calorie label*/
        patientGoalCalorie = findViewById(R.id.txtPatGoalCaloryCalculated);
        patientGoalCalorie.setText(String.valueOf(goalCalorie));



        /*set value to Remaining Calorie label and check the consumed balance*/
        patientRemainingCalorie = findViewById(R.id.txtPatRemainingCalculated);


        // foodName
        patientFoodName = findViewById(R.id.inptPatFoodName);

        // foodCalorie
        patientFoodCalorie = findViewById(R.id.inptPatFoodCalorie);


        //set calculated food calorie -total
        PatientFoodTotal2Calorie = findViewById(R.id.txtPatFoodCaloryCalculated);
        PatientFoodTotal2Calorie.setText("0");

        remainedCalorie = goalCalorie - Integer.parseInt(PatientFoodTotalCalorie.getText().toString());
        patientRemainingCalorie.setText(String.valueOf(remainedCalorie));
        if (remainedCalorie > 0) {
            patientRemainingCalorie.setTextColor(Color.parseColor("#009F21"));
        } else {
            patientRemainingCalorie.setTextColor(Color.parseColor("#DC0000"));
            PatientCalorieAlarm.setText("Be Careful you passed your Goal!!");
        }

        addButton = findViewById(R.id.btnPatAddFoodToList);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodsTotalCalories = Integer.parseInt(PatientFoodTotalCalorie.getText().toString()) + Integer.parseInt(patientFoodCalorie.getText().toString());
                //foodsTotalCalories =0;
                PatientFoodTotalCalorie.setText(String.valueOf(foodsTotalCalories));
                PatientFoodTotal2Calorie.setText(String.valueOf(foodsTotalCalories));
                currentFoodConsumedCalories = foodsTotalCalories;
                foodConsumedCalories = foodsTotalCalories;

                //Calculate Remained Calorie
                remainedCalorie = goalCalorie - foodConsumedCalories;
                patientRemainingCalorie.setText(String.valueOf(remainedCalorie));
                if (remainedCalorie > 0) {
                    patientRemainingCalorie.setTextColor(Color.parseColor("#009F21"));
                } else {
                    patientRemainingCalorie.setTextColor(Color.parseColor("#DC0000"));
                    PatientCalorieAlarm.setText("Be Careful you passed your Goal!!");
                }

                FoodName = patientFoodName.getText().toString();
                FoodCalorie = Integer.parseInt(patientFoodCalorie.getText().toString());
               boolean isInserted = databaseHelper.addRecordFoodItem(patientFoodName.getText().toString(),patientFoodCalorie.getText().toString());

                 if (isInserted){
                    Toast.makeText(PatientFoodTrackPg1.this,"added to database",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PatientFoodTrackPg1.this,"not added to database",Toast.LENGTH_LONG).show();

                }
            }
        });

        viewFoodList = findViewById(R.id.btnPatViewFood);
        foodList = findViewById(R.id.txtPatFoodList);
        viewFoodList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Cursor c = databaseHelper.viewPatientAddedFoodItems();
                StringBuilder str = new StringBuilder();
                if(c.getCount()>0){
                    while (c.moveToNext()){
                        str.append(("ID : ") + c.getString(0));
                        str.append("\n");
                        str.append(("Food Name : " + c.getString(1)));
                        str.append("\n");
                        str.append(("Food Calorie :" + c.getString(2)));
                        str.append("\n");
                        str.append("------------------");
                        str.append("\n");

                    }

                }
                if(foodViewStatus==false){

                    foodList.setVisibility(View.VISIBLE);
                    foodList.setText(str);
                    //fin.close();
                    foodViewStatus =true;
                    viewFoodList.setText(R.string.txtPatCloseView);


                }
                else if(foodViewStatus){
                    foodViewStatus =false;
                    viewFoodList.setText(R.string.view);
                    foodList.setVisibility(View.INVISIBLE);


                }
            }

        });

        //define  save final daily  calorie button
        saveFinalInfo = findViewById(R.id.btnPatFinalSaveInfo);
        saveFinalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //check patient Id and date in daily Calorie table
                Boolean patientAvailabilityCursor = databaseHelper.checkPatientHasRecord(patientId, currentDateTime);

                //if patient already exist
                if(patientAvailabilityCursor){
                     databaseHelper.updateRecDailyCalorie(patientId,Integer.parseInt(PatientFoodTotalCalorie.getText().toString()));
                }
                else {
                    boolean isInserted = databaseHelper.addRecordDailyCalorie(patientId, Integer.parseInt(PatientFoodTotalCalorie.getText().toString()),currentDateTime);
                    if (isInserted){
                        Toast.makeText(PatientFoodTrackPg1.this,"Consumed daily Calorie added to database",Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(PatientFoodTrackPg1.this,"Consumed daily Calorie added to database not added to database",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }
}