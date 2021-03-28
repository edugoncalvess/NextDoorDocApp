package com.example.nextdoordocapp;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocMessageReply extends AppCompatActivity {


    String reply;
    DatabaseHelper databaseHelper;
    EditText replyText;
    Button btnReply;
    EditText idPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_message_reply);
        databaseHelper = new DatabaseHelper(this);
        replyText = findViewById(R.id.txtReplyMessageDoc);
        btnReply = findViewById(R.id.btnMessageSendDoc);
        idPatient = findViewById(R.id.txtInsertPatIDReply);


        btnReply.setOnClickListener(new View.OnClickListener() {
            int intId=1;
            @Override
            public void onClick(View v) {
                    reply = replyText.getText().toString();
                    Log.d("reply", reply);
                    //Having error in here
                   // intId = Integer.parseInt(idPatient.toString());


                   // Log.d("reply", reply);
                  boolean isUpdated = databaseHelper.updateReplyFieldDoc(intId, reply);
                  if (isUpdated) {
                      Toast.makeText(DocMessageReply.this, "Sent", Toast.LENGTH_LONG).show();
                  } else
                      Toast.makeText(DocMessageReply.this, "not sent", Toast.LENGTH_LONG).show();

              }

        });

        }
    }
