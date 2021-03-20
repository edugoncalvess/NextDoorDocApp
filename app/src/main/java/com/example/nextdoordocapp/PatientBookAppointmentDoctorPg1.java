package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class PatientBookAppointmentDoctorPg1 extends AppCompatActivity {

    Spinner spinnerPatAppointmentDocDays;
    Spinner spinnerPatAppointmentDocTime;
    Button btnPatAppointmentDocRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointment_doctor_pg1);

        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocDays = findViewById(R.id.spinnerPatAppointmentDocDays);

        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocTime = findViewById(R.id.spinnerPatAppointmentDocTime);

        // Button Register
        btnPatAppointmentDocRegister = findViewById(R.id.btnPatAppointmentDocRegister);

        btnPatAppointmentDocRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}