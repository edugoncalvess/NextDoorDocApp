package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class PatientSendMessageToDoctorPg1 extends AppCompatActivity {


    int pId =1;
    int docId =1;
    String time ="10:20";
    String date = "11:50";
    DatabaseHelper databaseHelper;
    EditText messageToDoc;
    Button btn;
    boolean isInserted;
    String message;
    TextView insuranceAmount;
    TextView txtPatDollarSign;
    TextView txtPatAmountToPayLabel;

    int patientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_send_message_to_doctor_pg1);

        //add today Date
     /*   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTime = sdf.format(new Date());*/

       // SimpleDateFormat sdf = new SimpleDateFormat()
        Intent findDoctorPg1Intent = getIntent();
        int patientIdSMP1 = findDoctorPg1Intent.getIntExtra("patientId",0);
        patientId = patientIdSMP1;
        Log.d("PatientIdInSM" , String.valueOf(patientId));

        messageToDoc = findViewById(R.id.editTextPatMessage);
        btn = findViewById(R.id.btnPatSendMessage);
        insuranceAmount = findViewById(R.id.txtPatAmountToPay);


        txtPatDollarSign = findViewById(R.id.txtPatDollarSign);
        txtPatAmountToPayLabel =findViewById(R.id.txtPatAmountToPayLabel);


        databaseHelper = new DatabaseHelper(this);

        Boolean patientHasInsuranceCursor = databaseHelper.checkPatientHasInsurance(patientId);
        if (patientHasInsuranceCursor)
        {
            insuranceAmount.setText("15");

        }
        else
        {

            insuranceAmount.setText("The Patient Has Insurance");
            txtPatDollarSign.setVisibility(View.INVISIBLE);
            txtPatAmountToPayLabel.setVisibility(View.INVISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             message = messageToDoc.getText().toString();
//---------------------Checked to see if data is updating to database------------------

                isInserted = databaseHelper.addRecordPatient_leaveMessage_Doctor(pId,docId,time,date,message.toString(),"","");
                if (isInserted){
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"added",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"not added",Toast.LENGTH_LONG).show();

                }

                //------------------------------------------------------------------
            }
        });

    }
}
/*     boolean ifUpdated = databaseHelper.updateMessagePatientTable(MessageId,messageToDoc.toString());
                if (ifUpdated){
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"updated",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"not updated",Toast.LENGTH_LONG).show();

                }*/