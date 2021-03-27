package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocMessagesPg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_messages_pg1);

        Button button = findViewById(R.id.btnReplyMessageDoc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (DocMessagesPg1.this,DocMessageReply.class));
            }
        });
    }
}