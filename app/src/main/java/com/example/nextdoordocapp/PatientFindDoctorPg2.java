package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientFindDoctorPg2 extends AppCompatActivity {

    Button btnPatDoctorBookAppointment;
    Button btnPatDoctorCheckAvailability;
    Button btnPatDoctorGetOnlineHelp;

    TextView mFname;
    TextView mLname;
    TextView mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_find_doctor_pg2);

        // DoctorNameResult
        mFname = findViewById(R.id.txtPatDoctorNameResult);

        // DoctorAddressResult
        mLname = findViewById(R.id.txtPatDoctorAddressResult);

        // DoctorPhoneResult
        mAddress = findViewById(R.id.txtPatDoctorLastNameResult);

        //BookAppointment Button
        btnPatDoctorBookAppointment = findViewById(R.id.btnPatDoctorBookAppointment);

        //CheckAvailability Button
        btnPatDoctorCheckAvailability = findViewById(R.id.btnPatDoctorCheckAvailability);

        //btnPatDoctorGetOnlineHelp Button
        btnPatDoctorGetOnlineHelp = findViewById(R.id.btnPatDoctorGetOnlineHelp);



        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mFname.setText(mBundle.getString("firstName"));
            mLname.setText(mBundle.getString("LastName"));
            mAddress.setText(mBundle.getString("Address"));
        }

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
                startActivity(new Intent(PatientFindDoctorPg2.this,PatientSendMessageToDoctorPg1.class));
            }
        });
    }
}