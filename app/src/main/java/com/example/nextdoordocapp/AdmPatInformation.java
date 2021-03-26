package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdmPatInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_pat_information);

        Button btnAdmCreateProfile = findViewById(R.id.btnRgstPgCreateProfile);

        btnAdmCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdmPatInformation.this, "Profile Created", Toast.LENGTH_LONG).show();
            }
        });
    }
}