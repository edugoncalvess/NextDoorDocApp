package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdmTicketDetail extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    int ticketID = 0;
    String tktMsgEmail;
    String tktMsgPhone;
    String tktMsgMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_ticket_detail);

        databaseHelper = new DatabaseHelper(this);

        EditText txtTicketID = findViewById(R.id.txtTicketID);
        Button btnCheckTicketStatus = findViewById(R.id.btnCheckTicketStatus);

        TextView txttktContactEmail = findViewById(R.id.txttktContactEmail);
        TextView txttktContactPhone = findViewById(R.id.txttktContactPhone);
        TextView txttktMessage2 = findViewById(R.id.txttktMessage2);

        Button btnCloseTicket = findViewById(R.id.btnCloseTicket);
        Button btnBackHomeFromTktDetails = findViewById(R.id.btnBackHomeFromTktDetails);



        btnCheckTicketStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtTicketID.getText().toString().trim().length() > 0){
                    ticketID = Integer.parseInt(txtTicketID.getText().toString());
                }
                else {
                    ticketID = 0;
                }

                Cursor c = databaseHelper.checkTicketByPatientId(ticketID);

                if(c.getCount() > 0) {
                    while (c.moveToNext()) {
                        tktMsgEmail = c.getString(1); // email
                        tktMsgPhone = c.getString(2); // Phone
                        tktMsgMessage = c.getString(3); // Message
                    }
                    //Log.d("DBGETPAYMT", paymentId.toString());
                }
                else if(c.getCount() == 0) {
                    Toast.makeText(AdmTicketDetail.this, "There is no open ticket with this id", Toast.LENGTH_SHORT).show();
                    tktMsgEmail = "";
                    tktMsgPhone = "";
                    tktMsgMessage = "";
                }
                else {
                    Toast.makeText(AdmTicketDetail.this, "There is no open ticket with this id", Toast.LENGTH_SHORT).show();
                    tktMsgEmail = "";
                    tktMsgPhone = "";
                    tktMsgMessage = "";
                }
                c.close();;

                txttktContactEmail.setText(tktMsgEmail);
                txttktContactPhone.setText(tktMsgPhone);
                txttktMessage2.setText(tktMsgMessage);
            }
        });


        btnCloseTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated;
                isUpdated = databaseHelper.updateCloseTicket(ticketID);

                if(isUpdated) {
                    Toast.makeText(AdmTicketDetail.this,"Ticket closed successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AdmTicketDetail.this,"Could not close the ticket payment=",Toast.LENGTH_LONG).show();
                }



            }
        });

        btnBackHomeFromTktDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmTicketDetail.this,AdmWelcomePage.class));

            }
        });


    }
}