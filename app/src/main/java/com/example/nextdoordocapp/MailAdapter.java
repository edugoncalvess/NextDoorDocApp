package com.example.nextdoordocapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailViewHolder> {

    private List<EmailData> mEmailData  ;
    private Context mContext;

    public MailAdapter(Context mContext, List<EmailData> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_mail_item,
                parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailViewHolder holder, int position) {
        holder.mIcon.setText(mEmailData.get(position).getmSender().substring(0, 1));
        holder.mSender.setText(mEmailData.get(position).getmSender());
        holder.mEmailTitle.setText(mEmailData.get(position).getmTitle());
        holder.mEmailDetails.setText(mEmailData.get(position).getmDetails());
        holder.mEmailTime.setText(mEmailData.get(position).getmTime());

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DocDetailViewMessage.class);
                mIntent.putExtra("sender", holder.mSender.getText().toString());
                mIntent.putExtra("title", holder.mEmailTitle.getText().toString());
                mIntent.putExtra("details", holder.mEmailDetails.getText().toString());
                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
                mIntent.putExtra("icon", holder.mIcon.getText().toString());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }
}
