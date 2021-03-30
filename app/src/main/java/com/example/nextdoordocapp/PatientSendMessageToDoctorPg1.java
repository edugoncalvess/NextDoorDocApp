package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientSendMessageToDoctorPg1 extends AppCompatActivity {

    int MessageId =1;
    int pId =1;
    int docId =1;
    String time ="";
    String date = "";
    DatabaseHelper databaseHelper;
    EditText messageToDoc;
    Button btn;
    boolean isInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_send_message_to_doctor_pg1);

        messageToDoc = findViewById(R.id.editTextPatMessage);
        btn = findViewById(R.id.btnPatSendMessage);
        databaseHelper = new DatabaseHelper(this);
        isInserted = databaseHelper.addRecordPatient_leaveMessage_Doctor(MessageId,pId,docId,time,date,messageToDoc.toString(),"","");
        if (isInserted){
            Toast.makeText(PatientSendMessageToDoctorPg1.this,"added",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(PatientSendMessageToDoctorPg1.this,"not added",Toast.LENGTH_LONG).show();

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//---------------------Checked to see if data is updating to database------------------
                boolean ifUpdated = databaseHelper.updateMessagePatientTable(MessageId,messageToDoc.toString());
                if (ifUpdated){
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"updated",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"not updated",Toast.LENGTH_LONG).show();

                }


                //------------------------------------------------------------------
            }
        });

    }
}