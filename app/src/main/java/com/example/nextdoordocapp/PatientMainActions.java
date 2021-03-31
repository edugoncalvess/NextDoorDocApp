package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PatientMainActions extends AppCompatActivity {

    Button foodTrackBtn;
    Button findDoctorBtn;
    Button changePassword;
    DatabaseHelper db;
    int patientId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main_actions);
        Intent foodTrackIntent = getIntent();
        patientId = foodTrackIntent.getIntExtra("patientId", 0);
        Log.d("patientId: ", String.valueOf(patientId));

        db = new DatabaseHelper(this);
        foodTrackBtn = findViewById(R.id.btnPatFoodTrack);
        findDoctorBtn = findViewById(R.id.btnPatFindDoctor);
        changePassword = findViewById(R.id.btnPatChangePassword);
        Button checkMessage = findViewById(R.id.btnPatientCheckMessage);
        FloatingActionButton logout = findViewById(R.id.logOutFAB);


        foodTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent foodTrackIntent = new Intent(PatientMainActions.this, PatientFoodTrackPg1.class);
                foodTrackIntent.putExtra("patientId", patientId);
                startActivity(foodTrackIntent);


                //startActivity(new Intent(PatientMainActions.this,PatientFoodTrackPg1.class));
            }
        });

        findDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PatientMainActions.this,PatientFindDoctorPg1.class));
                Intent findDoctorPg1Intent = new Intent(PatientMainActions.this, PatientFindDoctorPg1.class);
                findDoctorPg1Intent.putExtra("patientId", patientId);
                startActivity(findDoctorPg1Intent);
            }
        });

        checkMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodTrackIntent = new Intent(PatientMainActions.this, PatientCheckNewMessage.class);
                foodTrackIntent.putExtra("patientId", patientId);
                startActivity(foodTrackIntent);

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActions.this,patientChangesPassword.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActions.this,SplashActivity.class));
            }
        });
    }
}