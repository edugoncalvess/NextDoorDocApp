package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientFindDoctorPg1 extends AppCompatActivity {

    EditText inptPatAddress;
    EditText inptPatPostalCode;
    Button btnPatDoctorfind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_find_doctor_pg1);

        // DoctorName
        inptPatAddress = findViewById(R.id.inptPatAddress);

        // DoctorPostalCode
        inptPatPostalCode = findViewById(R.id.inptPatPostalCode);

        // find Doctor
        btnPatDoctorfind = findViewById(R.id.btnPatDoctorFind);

        btnPatDoctorfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientFindDoctorPg1.this,PatientFindDoctorPg2.class));
            }
        });


    }
}