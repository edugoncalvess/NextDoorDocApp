package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactSupportPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support_pg1);

        databaseHelper = new DatabaseHelper(this);

        Button btnContactSupportSend = findViewById(R.id.btnContactSupportSend);
        EditText txtContactSupportEmail = findViewById(R.id.txtContactSupportEmail);
        EditText txtContactSupportPhone = findViewById(R.id.txtContactSupportPhone);
        EditText txtContactSuppMultiLine2 = findViewById(R.id.txtContactSuppMultiLine2);


        btnContactSupportSend.setOnClickListener(new View.OnClickListener() {

            boolean isInserted;

            @Override
            public void onClick(View v) {





                // For future Implementations:
                String needLoginHelpEmail = txtContactSupportEmail.getText().toString();
                String needLoginHelpPhone = txtContactSupportPhone.getText().toString();
                String needLoginHelpMessage = txtContactSuppMultiLine2.getText().toString();




                        isInserted = databaseHelper.addTicketRecords(txtContactSupportEmail.getText().toString(),
                                txtContactSupportPhone.getText().toString(),
                                txtContactSuppMultiLine2.getText().toString());


                        if (isInserted){
                            // Sending message on screen
                            Toast.makeText(ContactSupportPg1.this,"Data added. We are going to solve it ASAP",Toast.LENGTH_LONG).show();
                            Log.d("ContactSupport002", "Ticket registered" + "Email: " + needLoginHelpEmail + ", " + "Phone: " + needLoginHelpPhone + ", " + "Message: " + needLoginHelpMessage);
                        }
                        else {
                            Toast.makeText(ContactSupportPg1.this,"An error occurred. Please call our support instead." + needLoginHelpEmail,Toast.LENGTH_LONG).show();
                            Log.d("ContactSupport003", "Failed to register a ticket");
                        }


                // Sending a message on screen

                //Toast.makeText(ContactSupportPg1.this,"Data added. Wait for your password reset or call us. " + needLoginHelpEmail,Toast.LENGTH_LONG).show();
                Log.d("ContactSupport",  "Email: " + needLoginHelpEmail + ", " + "Phone: " + needLoginHelpPhone + ", " + "Message: " + needLoginHelpMessage);


            }
        });

    }
}