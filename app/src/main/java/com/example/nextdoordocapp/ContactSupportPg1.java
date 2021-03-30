package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactSupportPg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support_pg1);


        Button btnContactSupportSend = findViewById(R.id.btnContactSupportSend);
        EditText txtContactSupportEmail = findViewById(R.id.txtContactSupportEmail);
        EditText txtContactSupportPhone = findViewById(R.id.txtContactSupportPhone);
        EditText txtContactSuppMultiLine2 = findViewById(R.id.txtContactSuppMultiLine2);


        btnContactSupportSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                // For future Implementations:
                String needLoginHelpEmail = txtContactSupportEmail.getText().toString();
                String needLoginHelpPhone = txtContactSupportPhone.getText().toString();
                String needLoginHelpMessage = txtContactSuppMultiLine2.getText().toString();




                // Sending a message on screen

                Toast.makeText(ContactSupportPg1.this,"Data added. Wait for your password reset or call us. " + needLoginHelpEmail,Toast.LENGTH_LONG).show();
                Log.d("ContactSupport",  "Email: " + needLoginHelpEmail + ", " + "Phone: " + needLoginHelpPhone + ", " + "Message: " + needLoginHelpMessage);


            }
        });

    }
}