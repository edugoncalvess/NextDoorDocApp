package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdmListOfTickets extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_list_of_tickets);

        databaseHelper = new DatabaseHelper(this);

        Button btnCheckPendingTickets = findViewById(R.id.btnCheckPendingTickets);
        TextView txtListOfPendingTickets = findViewById(R.id.txtListOfPendingTickets);
        Button btnBackHomeFromTktList = findViewById(R.id.btnBackHomeFromTktList);


        btnCheckPendingTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor c = databaseHelper.listAllOpenTickets();
                StringBuilder strMsg = new StringBuilder();
                if(c.getCount() > 0) {
                    strMsg.append("TicketID; "); strMsg.append("\t");
                    strMsg.append("Email; "); strMsg.append("\t");
                    strMsg.append("Phone");
                    strMsg.append("\n");
                    while (c.moveToNext()) {
                        strMsg.append(c.getString(0) + ";  "); strMsg.append("\t"); // ticketId
                        strMsg.append(c.getString(1) + ";  "); strMsg.append("\t"); // ticketEmail
                        strMsg.append(c.getString(2) + " "); // ticketPhone
                        strMsg.append("\n");

                        //     final static String T13COL_1 = "ticketId";
                        //    final static String T13COL_2 = "ticketEmail";
                        //    final static String T13COL_3 = "ticketPhone";
                        //    final static String T13COL_4 = "ticketMessage";
                        //    final static String T13COL_5 = "ticketStatus";



                    }
                    txtListOfPendingTickets.setText(strMsg);
                }
                else if(c.getCount() == 0)  {
                    strMsg.append("There are no open tickets"); strMsg.append("\t");
                    Log.d("TicketCursorCount", "Query retrieving open tickets returned with 0 entries " + c.getCount());
                    txtListOfPendingTickets.setText(strMsg);
                }

                c.close();;

            }
        });







        btnBackHomeFromTktList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdmListOfTickets.this,AdmWelcomePage.class));
            }
        });

    }
}