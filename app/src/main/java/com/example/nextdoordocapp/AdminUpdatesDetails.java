package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminUpdatesDetails extends AppCompatActivity {


    public String emailUpdateSearch;
    Button btnSearchPatient;
    Button btnSearchDoc;
    EditText txtEmailUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_updates_details);

        btnSearchPatient = findViewById(R.id.btnSearchPatient);
        btnSearchDoc = findViewById(R.id.btnUpdateDoc);
        txtEmailUpdate = findViewById(R.id.txtEmailUpdate);
        emailUpdateSearch =txtEmailUpdate.getText().toString();

        btnSearchPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminUpdatesDetails.this,AdminUpdatingPatient.class));




            }


        });

        btnSearchDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }




}