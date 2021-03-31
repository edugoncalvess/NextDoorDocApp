package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DocShowAvailability extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView showAllSchedule;
    Button homeButton;
    Button addMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_show_availability);

        databaseHelper = new DatabaseHelper(this);

        homeButton = findViewById(R.id.btnDocGoHome);
        addMoreButton = findViewById(R.id.btnPatOkCheckedSchedule);
        showAllSchedule = findViewById(R.id.txtDocShowAllAvailibility);

        Intent docMessagesIntent = getIntent();
        int docID = getIntent().getIntExtra("docID",0);

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

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(DocShowAvailability.this,DoctorHelloPg1.class));
            }
        });

        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(DocShowAvailability.this,DocSchedulePg1.class));
                Intent doctorShowSchedule = new Intent(DocShowAvailability.this, DocSchedulePg1.class);
                doctorShowSchedule.putExtra("docID", Integer.parseInt(String.valueOf(docID)));
                startActivity(doctorShowSchedule);
            }
        });


    }
}