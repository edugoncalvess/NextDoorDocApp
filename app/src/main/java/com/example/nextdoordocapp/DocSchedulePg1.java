package com.example.nextdoordocapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class DocSchedulePg1 extends AppCompatActivity {

    CalendarView calender;
    TextView dateView;
    String Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_schedule_pg1);

        calender = findViewById(R.id.calender);
        //dateView = findViewById(R.id.dateView1);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = dayOfMonth + "-" + (month +1) + "-" + year;
              dateView.setText(Date);
            }
        });


    }
}