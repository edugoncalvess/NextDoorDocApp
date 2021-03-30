package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
    TextView txtAppointment;
    boolean isUpdate;
    int check = 1;

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
        txtAppointment = findViewById(R.id.txtBookedApptConfirm);


        btnPatAppointmentDocRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerChoiceDate = spinnerPatAppointmentDocDays.getSelectedItem().toString();
                spinnerChoiceTime =spinnerPatAppointmentDocTime.getSelectedItem().toString();
                timeRange = spinnerPatAppointmentDocTime.getSelectedItemPosition() + 8;

                Log.d("bookeddate",spinnerChoiceDate);
                Log.d("bookedTime", String.valueOf(timeRange));
                Cursor c = databaseHelper.viewDoctorAvailability();
                if(c.getCount()>0) {
                    while (c.moveToNext()) {
                        date = c.getString(1);
                        startTime = c.getString(2);
                        endTime = c.getString(3);
                        Log.d("dateDoc", date);
                        Log.d("startTimeDoc",startTime);
                        Log.d("endTimeDoc",endTime);

                        if (spinnerChoiceDate.equals(date) && timeRange >= Integer.parseInt(startTime) && timeRange <= Integer.parseInt(endTime))
                            {
                                isUpdate = databaseHelper.upDateApptWithAvb(1, spinnerChoiceDate, String.valueOf(timeRange));
                                if (isUpdate) {
                                    check ++;
                                    Toast.makeText(PatientBookAppointmentDoctorPg1.this, "Appointment Saved", Toast.LENGTH_LONG).show();
                                    txtAppointment.setText("You booked for " + spinnerChoiceDate + " For " + timeRange + " to " + (timeRange + 1));
                                    break;
                            }
                         }
                    }
                    if (!isUpdate && check == 1)
                    {
                        Toast.makeText(PatientBookAppointmentDoctorPg1.this,
                                "Please chose according to availability", Toast.LENGTH_LONG).show();
                        check =1;
                    }
                }
                else
                    Toast.makeText(PatientBookAppointmentDoctorPg1.this,
                            "Please chose according to availability", Toast.LENGTH_LONG).show();

                }



        });


    }


}