package com.example.nextdoordocapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DocMessagesPg1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    List<EmailData> mEmailData = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_messages_pg1);

        databaseHelper = new DatabaseHelper(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(DocMessagesPg1.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(DocMessagesPg1.this,
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        EmailData mEmail = new EmailData("Facebook", "James, you have 1 new notification",
                "A lot has happened on Facebook since",
                "16:04pm");

        databaseHelper.viewNewMessageDoc();
        Cursor c = databaseHelper.viewNewMessageDoc();
        if(c.getCount()>0){
            while (c.moveToNext()){
                String sender = c.getString(0);
                String title = c.getString(1);
                String Details = c.getString(2);
                String time = c.getString(3);

                mEmail = new EmailData("Patient: " + sender, "Message/Question",
                        Details,
                        time);
            }
            mEmailData.add(mEmail);
        }





        mEmail = new EmailData("Facebook", "James, you have 1 new notification",
                "A lot has happened on Facebook since",
                "16:04pm");

        mEmailData.add(mEmail);

        mEmail = new EmailData("Google+", "Top suggested Google+ pages for you",
                "Top suggested Google+ pages for you",
                "18:44pm");

        mEmailData.add(mEmail);

        mEmail = new EmailData("Twitter", "Follow T-Mobile, Samsung Mobile U",
                "James, some people you may know",
                "20:04pm");

        mEmailData.add(mEmail);

        mEmail = new EmailData("Pinterest Weekly", "Pins you’ll love!",
                "Have you seen these Pins yet? Pinterest",
                "09:04am");

        mEmailData.add(mEmail);

        mEmail = new EmailData("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut",
                "01:04am");
        mEmailData.add(mEmail);

        mEmail = new EmailData("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut",
                "01:04am");
        mEmailData.add(mEmail);

        mEmail = new EmailData("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut",
                "01:04am");
        mEmailData.add(mEmail);


        MailAdapter mMailAdapter = new MailAdapter(DocMessagesPg1.this, mEmailData);
        mRecyclerView.setAdapter(mMailAdapter);
    }

}