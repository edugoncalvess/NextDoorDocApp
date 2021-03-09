package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientFoodTrackPg1 extends AppCompatActivity {
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_food_track_pg1);

        addButton=findViewById(R.id.btnPatAddFoodToBreakfast);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientFoodTrackPg1.this,PatientFoodSearch.class));
            }
        });
    }
}