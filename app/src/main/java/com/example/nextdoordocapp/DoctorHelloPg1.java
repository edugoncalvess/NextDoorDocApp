package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DoctorHelloPg1 extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_hello_pg1);

        Intent docMessagesIntent = getIntent();
        int docID = getIntent().getIntExtra("docID",0);

        databaseHelper = new DatabaseHelper(this);


        Button btnDocShowMessages = findViewById(R.id.btnDocShowMessages);
        Button btnDocSHowSchedule = findViewById(R.id.btnDocShowSchedule);

        btnDocShowMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(DoctorHelloPg1.this,ShowDocsList.class));
                Intent docMessagesIntent = new Intent(DoctorHelloPg1.this, DocMessagesPg1.class);
                docMessagesIntent.putExtra("docId", docID);
                startActivity(docMessagesIntent);

              //  startActivity(new Intent(DoctorHelloPg1.this,DocMessagesPg1.class));
            }
        });

        btnDocSHowSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(DoctorHelloPg1.this,DocSchedulePg1.class));
                Intent docScheduleIntent = new Intent(DoctorHelloPg1.this, DocSchedulePg1.class);
                docScheduleIntent.putExtra("docId", docID);
                startActivity(docScheduleIntent);

            }
        });
    }
}