package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientFindDoctorPg2 extends AppCompatActivity {
    TextView txtPatDoctorNameResult;
    TextView txtPatDoctorAddressResult;
    TextView txtPatDoctorPhoneResult;
    Button btnPatDoctorBookAppointment;
    Button btnPatDoctorCheckAvailability;
    Button btnPatDoctorGetOnlineHelp;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_find_doctor_pg2);

        Intent findDoctorPg1Intent = getIntent();
        int patientIdPG2 = findDoctorPg1Intent.getIntExtra("patientId",0);
        patientId = patientIdPG2;
        Log.d("PatientIdDoc2" , String.valueOf(patientId));

        // DoctorNameResult
        txtPatDoctorNameResult = findViewById(R.id.txtPatDoctorNameResult);

        // DoctorAddressResult
        txtPatDoctorAddressResult = findViewById(R.id.txtPatDoctorAddressResult);

        // DoctorPhoneResult
        txtPatDoctorPhoneResult = findViewById(R.id.txtPatDoctorPhoneResult);

        //BookAppointment Button
        btnPatDoctorBookAppointment = findViewById(R.id.btnPatDoctorBookAppointment);

        //CheckAvailability Button
        btnPatDoctorCheckAvailability = findViewById(R.id.btnPatDoctorCheckAvailability);

        //btnPatDoctorGetOnlineHelp Button
        btnPatDoctorGetOnlineHelp = findViewById(R.id.btnPatDoctorGetOnlineHelp);

        //Action For Book Appointment
        btnPatDoctorBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientFindDoctorPg2.this,PatientBookAppointmentDoctorPg1.class));
            }
        });

        //btnPatDoctorCheckAvailability
        btnPatDoctorCheckAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientFindDoctorPg2.this,PatientCheckDocsAvailability.class));
            }
        });

        //btnPatDoctorOnlineHelp
        btnPatDoctorGetOnlineHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PatientFindDoctorPg2.this,PatientSendMessageToDoctorPg1.class));
                Intent findDoctorPg1Intent = new Intent(PatientFindDoctorPg2.this, PatientSendMessageToDoctorPg1.class);
                findDoctorPg1Intent.putExtra("patientId", patientId);
                startActivity(findDoctorPg1Intent);
            }
        });
    }
}