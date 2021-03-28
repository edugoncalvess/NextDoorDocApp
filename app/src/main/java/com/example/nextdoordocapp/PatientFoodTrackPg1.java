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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_food_track_pg1);

        Intent foodTrackIntent = getIntent();
        int patientId = getIntent().getIntExtra("patientId",0);
        Log.d("This is User id" , String.valueOf(patientId));



        //add database
        databaseHelper = new DatabaseHelper(this);
        //databaseHelper. addRecordPatientTest();

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

        //select food items cardView
        CardView crdViewPatFoodTrackItems = findViewById(R.id.crdViewPatFoodTrack);

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


        // foodName
        patientFoodName = findViewById(R.id.inptPatFoodName);

        // foodCalorie
        patientFoodCalorie = findViewById(R.id.inptPatFoodCalorie);


        //set value for total food calories
        PatientFoodTotalCalorie = findViewById(R.id.txtPatFoodCalculated);
        PatientFoodTotalCalorie.setText("0");

        //set calculated food calorie -total
        PatientFoodTotal2Calorie = findViewById(R.id.txtPatFoodCaloryCalculated);
        PatientFoodTotal2Calorie.setText("0");

        addButton = findViewById(R.id.btnPatAddFoodToList);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodsTotalCalories = foodsTotalCalories + Integer.parseInt(String.valueOf(patientFoodCalorie.getText()));
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

                //Refresh Activity
                /*Intent i = new Intent(PatientFoodTrackPg1.this, PatientFoodTrackPg1.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);*//*

                //patientFoodConsumedCalories.setText(String.valueOf(breakfastTotalCalories));

                *//*SharedPreferences totalConsumedCaloriesPreferences = getSharedPreferences("totalConsumedCalories", MODE_PRIVATE);
                SharedPreferences.Editor edit = totalConsumedCaloriesPreferences.edit();
                edit.putInt("key1",currentFoodConsumedCalories);
                edit.commit();
                int tBreakcalorie = totalConsumedCaloriesPreferences.getInt("key1",0);
                Log.d("tBreakcalorie", String.valueOf(tBreakcalorie));
                //patientFoodConsumedCalories.setText(tBreakcalorie);
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



/*
                try {
                    FileOutputStream fout = openFileOutput("foodItemFile.txt", MODE_APPEND);

                    //add today Date
                    *//*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                    String currentDateandTime = sdf.format(new Date());
                    fout.write(currentDateandTime.getBytes());
                    fout.write("\n".getBytes());*//*

                    fout.write(FoodName.getBytes());
                    fout.write(": ".getBytes());
                    fout.write(String.valueOf(FoodCalorie).getBytes());
                    fout.write("\n".getBytes());
                    fout.close();
                   *//* Toast.makeText(PatientFoodTrackPg1.this, "Saving...", Toast.LENGTH_LONG).show();*//*

                    //delete file
                    File dir = getFilesDir();
                    File file = new File(dir, "breakfastFile.txt");
                    boolean deleted = file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
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

                    /*FileInputStream fin = openFileInput("foodItemFile.txt");
                    int read = -1;
                    StringBuffer buffer = new StringBuffer();
                    while((read =fin.read())!= -1){
                        buffer.append((char)read);
                    }*/
                    //Log.d("Code", buffer.toString());
                    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                    //String currentDateandTime = sdf.format(new Date());

                       /* String bname = buffer.substring(0,buffer.indexOf(" "));
                        String bcal = buffer.substring(buffer.indexOf(" ")+1);
                        foodList.setText(bname + bcal);*/

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

            }
        });

    }
}