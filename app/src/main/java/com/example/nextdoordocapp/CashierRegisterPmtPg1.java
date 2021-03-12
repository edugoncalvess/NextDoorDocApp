package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CashierRegisterPmtPg1 extends AppCompatActivity {

    String paymentMethodType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_register_pmt_pg1);

        String enteredUserID;
        float enteredPmtAmount;
        RadioButton pmtMethdCash = findViewById(R.id.rdBtnCash);
        RadioButton pmtMethdCard = findViewById(R.id.rdBtnCard);
        RadioButton pmtMethdDeposit = findViewById(R.id.rdBtnDeposit);
        TextView txtPmtPlusTaxes = findViewById(R.id.txtPmtPlusTaxes);
        TextView txtPatientIdRegstAct = findViewById(R.id.txtPatientIdRegstAct);


        Button btnRegisterPmtIntoSystem = findViewById(R.id.btnRegisterPmtIntoSystem);

        // The method with Intent and Bundle from the "Check payment" activity is not working yet.
        Intent toPaymentAcvt = getIntent();
        Bundle extraInfo = toPaymentAcvt.getExtras();
        if((toPaymentAcvt!=null) && (extraInfo!=null)) {
//            //float rcvdParameterAmount = getIntent().getFloatExtra("OwedValue",0);
            float rcvdParameterAmount = extraInfo.getFloat("OwedValue", 0);
                    enteredPmtAmount = (float) (rcvdParameterAmount * 1.12);

            //String rcvdParameterUserId = getIntent().getExtras().getString("transferedPatientId");
            String rcvdParameterUserId = extraInfo.getString("transferedPatientId");

            txtPatientIdRegstAct.setText(rcvdParameterUserId); // IT'S NOT WORKING YET

            txtPmtPlusTaxes.setText(String.valueOf(enteredPmtAmount)); // Passing the payment due from the "check payment" activity
        }
        else {
                 // enteredUserID = "";
        }


        btnRegisterPmtIntoSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (pmtMethdCash.isChecked()) {
                    Toast.makeText(CashierRegisterPmtPg1.this,"Chosen Method is Cash",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }
                else if (pmtMethdCard.isChecked()){
                    Toast.makeText(CashierRegisterPmtPg1.this,"Chosen Method is Card",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }
                else {
                    Toast.makeText(CashierRegisterPmtPg1.this,"Chosen Method is Deposit/Transfer",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }

                // PREPARE THE SQL STATEMENT


            }
        });









    }
}