package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PatientSendMessageToDoctorPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_send_message_to_doctor_pg1);

        //---------------------Checked to see if data is adding to database------------------
        databaseHelper = new DatabaseHelper(this);

        databaseHelper.addRecordPatient_leaveMessage_DoctorTest();
        boolean isInserted = databaseHelper.addRecordPatient_leaveMessage_DoctorTest();
        if (isInserted){
            Toast.makeText(PatientSendMessageToDoctorPg1.this,"added",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(PatientSendMessageToDoctorPg1.this,"not added",Toast.LENGTH_LONG).show();

        }
        //------------------------------------------------------------------
    }
}