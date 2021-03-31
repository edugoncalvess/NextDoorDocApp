package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CashierHomePg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home_pg1);

        Button btnCheckPayment = findViewById(R.id.btnCheckPayment);
        Button btnHmListOfAllPayments = findViewById(R.id.btnHmListOfAllPayments);
        Button btnCashierLogoff = findViewById(R.id.btnCashierLogoff);
        //Button btnRegisterPayment = findViewById(R.id.btnRegisterPayment);
        FloatingActionButton logout = findViewById(R.id.logoutCashier);
        btnCheckPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,CashierPmtCheckPg1.class));
            }
        });

        btnHmListOfAllPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,CashierListOfPayments.class));
            }
        });

        btnCashierLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,LoginActivityPg1.class));
            }
        });



        /*
        *         btnRegisterPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,CashierRegisterPmtPg1.class));
            }
        });
        * */

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashierHomePg1.this,SplashActivity.class));
            }
        });


    }
}