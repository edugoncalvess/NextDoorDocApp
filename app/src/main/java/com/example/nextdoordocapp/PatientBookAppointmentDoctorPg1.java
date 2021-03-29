package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PatientBookAppointmentDoctorPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Spinner spinnerPatAppointmentDocDays;
    Spinner spinnerPatAppointmentDocTime;
    Button btnPatAppointmentDocRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointment_doctor_pg1);

        databaseHelper = new DatabaseHelper(this);

        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocDays = findViewById(R.id.spinnerPatAppointmentDocDays);

        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocTime = findViewById(R.id.spinnerPatAppointmentDocTime);

        // Button Register
        btnPatAppointmentDocRegister = findViewById(R.id.btnPatAppointmentDocRegister);




/* Cursor c = databaseHelper.viewNewMessageDoc();
        if(c.getCount()>0){
            while (c.moveToNext()){
                String sender = c.getString(0);
                String title = c.getString(1);
                String Details = c.getString(2);
                String time = c.getString(3);

                mEmail = new EmailData("Patient: " + sender, "Message/Question",
                        Details,
                        time);
            }
            mEmailData.add(mEmail);
        }*/



        btnPatAppointmentDocRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}