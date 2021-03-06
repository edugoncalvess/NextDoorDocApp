package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientMainActions extends AppCompatActivity {
    Button foodTrackBtn;
    Button findDoctorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main_actions);

        foodTrackBtn = findViewById(R.id.btnPatFoodTrack);
        findDoctorBtn = findViewById(R.id.btnPatFindDoctor);


        foodTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActions.this,PatientFoodTrackPg1.class));
            }
        });

        findDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientMainActions.this,PatientFindDoctorPg1.class));
            }
        });
    }
}