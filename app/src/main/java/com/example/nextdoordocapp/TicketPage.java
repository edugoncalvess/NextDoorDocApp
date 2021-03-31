package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicketPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_page);


        Button btnTestTicketsList = findViewById(R.id.btnOpenTickets);
        btnTestTicketsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketPage.this,AdmListOfTickets.class));
            }
        });

        Button btnTestTicketsDetails = findViewById(R.id.btnTicketDetails);

        btnTestTicketsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketPage.this,AdmTicketDetail.class));

            }
        });
    }
}