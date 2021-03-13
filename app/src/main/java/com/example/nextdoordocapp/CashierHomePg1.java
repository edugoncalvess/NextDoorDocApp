package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CashierHomePg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home_pg1);

        Button btnCheckPayment = findViewById(R.id.btnCheckPayment);
        Button btnRegisterPayment = findViewById(R.id.btnRegisterPayment);

        btnCheckPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,CashierPmtCheckPg1.class));
            }
        });


        btnRegisterPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,CashierRegisterPmtPg1.class));
            }
        });

    }
}