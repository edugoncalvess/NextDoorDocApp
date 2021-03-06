package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorHelloPg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_hello_pg1);

        Button btnDocShowMessages = findViewById(R.id.btnDocShowMessages);
        Button btnDocSHowSchedule = findViewById(R.id.btnDocShowSchedule);

        btnDocShowMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHelloPg1.this,DocMessagesPg1.class));
            }
        });

        btnDocSHowSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHelloPg1.this,DocSchedulePg1.class));
            }
        });
    }
}