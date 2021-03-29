package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientFindDoctorPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText inptPatAddress;
    EditText inptPatPostalCode;
    TextView message;
    Button btnPatDoctorfind;

    String docAddress, docPostalCode;
    String doctorFName;
    String doctorLName;
    String doctorAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_find_doctor_pg1);

        databaseHelper = new DatabaseHelper(this);

        Intent findDoctorPg1Intent = getIntent();
        int patientId = getIntent().getIntExtra("patientId",0);
        Log.d("This is User id" , String.valueOf(patientId));

        /*databaseHelper.addRecordDocTest();
        databaseHelper.addRecordPatientTest();*/

        // DoctorName
        inptPatAddress = findViewById(R.id.inptPatAddress);

        // DoctorPostalCode
        inptPatPostalCode = findViewById(R.id.inptPatPostalCode);

        // find Doctor
        btnPatDoctorfind = findViewById(R.id.btnPatDoctorFind);

        //message label
        message = findViewById(R.id.txtPatFindDoctorMessageLbl);

        btnPatDoctorfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                docAddress = inptPatAddress.getText().toString();
                docPostalCode = inptPatPostalCode.getText().toString();

                if(!docAddress.isEmpty() || !docPostalCode.isEmpty()){
                    if(!docAddress.isEmpty()){
                        Cursor DoctorByAddCursor = databaseHelper.getDoctorByAddress(docAddress);
                        if(DoctorByAddCursor.getCount()>0){
                            while (DoctorByAddCursor.moveToNext()){
                                doctorFName = DoctorByAddCursor.getString(0);
                                doctorLName = DoctorByAddCursor.getString(1);
                                doctorAdd = DoctorByAddCursor.getString(2);
                            }
                            Log.d("Name",doctorFName);
                            Log.d("Last",doctorLName);
                            Log.d("Add",doctorAdd);
                        }
                    }
                    else if(!docPostalCode.isEmpty()){

                    }
                }
                else {
                    message.setText("Please Enter Address or Postal code");
                }

                //startActivity(new Intent(PatientFindDoctorPg1.this,PatientFindDoctorPg2.class));
            }
        });


    }
}