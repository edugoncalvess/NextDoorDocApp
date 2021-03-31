package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientCheckDocsAvailability extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    TextView showAllSchedule;
    Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_docs_availability);


        databaseHelper = new DatabaseHelper(this);

        okBtn = findViewById(R.id.btnPatOkCheckedSchedule);
        showAllSchedule = findViewById(R.id.txtDocShowAllAvailibility);


        Cursor c = databaseHelper.viewDoctorAvailability();
        StringBuilder str = new StringBuilder();
        if(c.getCount()>0){
            while (c.moveToNext()){

                str.append(("Date: " + c.getString(1)));
                str.append((" From " + c.getString(2)));
                str.append(("To " + c.getString(3)));
                str.append("\n");

            }
            showAllSchedule.setText(str);
        }
        if(c.getCount()==0)
            showAllSchedule.setText("Please wait for the doctor to set their availability. Thank you!");

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientCheckDocsAvailability.this,PatientFindDoctorPg2.class));
            }
        });

    }
}