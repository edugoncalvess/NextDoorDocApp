package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CashierRegisterPmtPg1 extends AppCompatActivity {

    String paymentMethodType = "";
    String paymentDateToday;
    String paymentTimeToday;
    double totalPmtAmount = 0;
    int rcvdPaymentId;
    int rcvdParameterUserId;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_register_pmt_pg1);



        databaseHelper = new DatabaseHelper(this);


        RadioButton pmtMethdCash = findViewById(R.id.rdBtnCash);
        RadioButton pmtMethdCard = findViewById(R.id.rdBtnCard);
        RadioButton pmtMethdDeposit = findViewById(R.id.rdBtnDeposit);
        TextView txtPatientIdRegstAct = findViewById(R.id.txtPatientID2);
        TextView txtPaymentID = findViewById(R.id.txtPaymentID);
        TextView txtPmtPreTaxes = findViewById(R.id.txtPmtPreTaxes);
        TextView txtPmtPlusTaxes = findViewById(R.id.txtPmtPlusTaxes);

        DecimalFormat format = new DecimalFormat(("####.##"));


        Button btnRegisterPmtIntoSystem = findViewById(R.id.btnRegisterPmtIntoSystem);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        btnBackToHome.setVisibility(View.INVISIBLE);

        // Receiving username, payment amount, and payment ID from the "payment check" activity
        Intent toPaymentAcvt = getIntent();
        Bundle extraInfo = toPaymentAcvt.getExtras();
        if((toPaymentAcvt!=null) && (extraInfo!=null)) {

            double rcvdParameterAmount = extraInfo.getDouble("OwedValue", 0);
            double enteredPmtAmountTemp  = (double) (rcvdParameterAmount * 1.12);

            BigDecimal bd = new BigDecimal(enteredPmtAmountTemp).setScale(2, RoundingMode.HALF_UP);
            totalPmtAmount = bd.doubleValue();

            rcvdParameterUserId = extraInfo.getInt("User_ID");
            rcvdPaymentId = extraInfo.getInt("PaymentID");

            txtPatientIdRegstAct.setText(String.valueOf(rcvdParameterUserId));
            txtPaymentID.setText(String.valueOf(rcvdPaymentId));
            txtPmtPreTaxes.setText(String.valueOf(rcvdParameterAmount));
            txtPmtPlusTaxes.setText(String.valueOf(totalPmtAmount)); // Passing the payment due from the "check payment" activity
        }
        else {
            Log.d("CshrPmtErr","Failed to import Intent and or bundle");
        }

                /*
                // Possible values from the Payment Table ( TABLE4_NAME )
                T4COL_5     T4COL_6     T4COL_7
                Amount      Method      PmtStatus
                -------     ----------  -------------
                0.00	    MSP     	Insured
                != 0        Cash        Pending
                                        Paid
                            Card    	Pending
                                        Paid
                            Deposit     Pending
                                        Paid
                */


        double finalEnteredPmtAmount = totalPmtAmount;
        btnRegisterPmtIntoSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Format fTime = new SimpleDateFormat("HH:mm:ss");
                String strFormated = fTime.format(new Date());
                paymentTimeToday = strFormated;

                Format fDate = new SimpleDateFormat("yyyy/MM/dd");
                strFormated = fDate.format(new Date());
                paymentDateToday  = strFormated;

                // Defining Payment Method checked on the Radio Button
                if (pmtMethdCash.isChecked()) {
                    paymentMethodType = "Cash";
                    //String mesasageToBeDisplayed = "Registered payment on Cash with the total of " + format.format(finalEnteredPmtAmount);
                    Toast.makeText(CashierRegisterPmtPg1.this,"Registered payment successfully",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }
                else if (pmtMethdCard.isChecked()){
                    paymentMethodType = "Card";
                    //mesasageToBeDisplayed = "Registered payment on Card with the total of " + format.format(finalEnteredPmtAmount);
                    Toast.makeText(CashierRegisterPmtPg1.this,"Registered payment successfully",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }
                else if (pmtMethdDeposit.isChecked()){
                    paymentMethodType = "Deposit";
                    //String mesasageToBeDisplayed = "Registered payment by Deposit/Transfer with the total of " + format.format(finalEnteredPmtAmount);
                    Toast.makeText(CashierRegisterPmtPg1.this,"Registered payment successfully",Toast.LENGTH_LONG).show();
                    paymentMethodType = "cash";
                }
                else {
                    paymentMethodType = "Other";
                    Log.d("CshrPmtErr", "Error selecting Payment Method");
                }

                // Calling the Databesehelper which will update payment fields
                boolean isUpdated;

                isUpdated = databaseHelper.updatePaymentStatus(rcvdPaymentId, paymentDateToday, paymentTimeToday,
                        String.valueOf(totalPmtAmount), paymentMethodType, "Paid");

                if(isUpdated) {
                    Toast.makeText(CashierRegisterPmtPg1.this,"Registered payment successfully",Toast.LENGTH_LONG).show();
                    btnBackToHome.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(CashierRegisterPmtPg1.this,"Could not register payment=",Toast.LENGTH_LONG).show();
                }

            }
        });


        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CashierRegisterPmtPg1.this,CashierHomePg1.class));
            }
        });








    }
}

   /* @Override
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
}*/