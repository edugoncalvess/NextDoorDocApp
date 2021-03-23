package com.example.nextdoordocapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class DocSchedulePg1 extends AppCompatActivity {

    Integer t1;
    Integer t2;
    CalendarView calender;
    TextView dateView;
    String Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_schedule_pg1);

        Button btnSet = findViewById(R.id.btnDocSetDT);
        EditText timeAvailability = findViewById(R.id.txtDocTimeAvRange);
        EditText timeAvailability2 = findViewById(R.id.txtDocTimeAvRange2);
        calender = findViewById(R.id.calender);
        dateView = findViewById(R.id.dateView);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "-" + (month +1) + "-" + year;

            }

        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1 =Integer.parseInt(timeAvailability.getText().toString());
                t2 =Integer.parseInt(timeAvailability2.getText().toString());
                dateView.setText(Date + " " + "From " + t1 + " to " + t2 );
            }
        });

    }
}