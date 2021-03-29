package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PatientBookAppointmentDoctorPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Spinner spinnerPatAppointmentDocDays;
    Spinner spinnerPatAppointmentDocTime;
    Button btnPatAppointmentDocRegister;
    String spinnerChoiceDate;
    String spinnerChoiceTime;
    int timeRange;
    String date;
    String startTime;
    String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointment_doctor_pg1);

        databaseHelper = new DatabaseHelper(this);

        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocDays = findViewById(R.id.spinnerPatAppointmentDocDays);
        // Spinner spinnerPatAppointmentDocDays
        spinnerPatAppointmentDocTime = findViewById(R.id.spinnerPatAppointmentDocTime2);
        // Button Register
        btnPatAppointmentDocRegister = findViewById(R.id.btnPatAppointmentDocRegister);

        spinnerChoiceDate = spinnerPatAppointmentDocDays.getSelectedItem().toString();
        spinnerChoiceTime =spinnerPatAppointmentDocTime.getSelectedItem().toString();
        timeRange = spinnerPatAppointmentDocTime.getSelectedItemPosition() + 9;

        btnPatAppointmentDocRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = databaseHelper.viewDoctorAvailability();
                if(c.getCount()>0){
                    while (c.moveToNext()){
                        date = c.getString(1);
                        startTime = c.getString(2);
                        endTime = c.getString(3);
                        Log.d("date",date);
                        if(spinnerChoiceDate.equals(date) && timeRange >= Integer.parseInt(startTime) && timeRange <= Integer.parseInt(endTime)) {
                            Log.d("ifStatement", "Ok!");
                            break;
                        }
                        else
                            Toast.makeText(PatientBookAppointmentDoctorPg1.this, "Please chose in the range", Toast.LENGTH_LONG).show();
                    }
                        boolean isUpdate = databaseHelper.upDateApptWithAvb(1,date,startTime);
                        if(isUpdate) {
                        Toast.makeText(PatientBookAppointmentDoctorPg1.this, "Appointment Saved", Toast.LENGTH_LONG).show();
                         }
                        else
                        Toast.makeText(PatientBookAppointmentDoctorPg1.this, "Appointment not Saved", Toast.LENGTH_LONG).show();
                     }
                }




        });

    }


}