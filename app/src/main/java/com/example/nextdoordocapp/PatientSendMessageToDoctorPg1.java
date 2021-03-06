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

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;

public class PatientSendMessageToDoctorPg1 extends AppCompatActivity {


    int pId =1;
    int docId =1;

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


        databaseHelper = new DatabaseHelper(this);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());

        Format fTime = new SimpleDateFormat("HH:mm:ss");
        String currentTimeSt = fTime.format(new Date());

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
        Log.d("InsuranceAvailibility", String.valueOf(patientHasInsuranceCursor));
        if (patientHasInsuranceCursor)
        {
            insuranceAmount.setText("15");
            btn.setText("Pay and Send Message");


            boolean isInserted = databaseHelper.addRecordPatToPayment(patientId, currentDate, currentTimeSt,
                    15, "", "Pending");

            if (isInserted){
                Toast.makeText(PatientSendMessageToDoctorPg1.this,"added to database",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(PatientSendMessageToDoctorPg1.this,"not added to database",Toast.LENGTH_LONG).show();

            }


        }
        else
        {

            insuranceAmount.setText("The Patient Has Insurance");
            txtPatDollarSign.setVisibility(View.INVISIBLE);
            txtPatAmountToPayLabel.setVisibility(View.INVISIBLE);
            btn.setText("Send Message");
            boolean isInserted = databaseHelper.addRecordPatToPayment(patientId, currentDate, currentTimeSt,
                    0, "Insurance", "Insured");

            if (isInserted){
                Toast.makeText(PatientSendMessageToDoctorPg1.this,"added to database",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(PatientSendMessageToDoctorPg1.this,"not added to database",Toast.LENGTH_LONG).show();

            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             message = messageToDoc.getText().toString();
                //---------------------Checked to see if data is updating to database------------------

                isInserted = databaseHelper.addRecordPatient_leaveMessage_Doctor(pId,docId,currentDate,currentTimeSt,message.toString(),"","");
                if (isInserted){
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"SENT",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(PatientSendMessageToDoctorPg1.this,"not sent",Toast.LENGTH_LONG).show();

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