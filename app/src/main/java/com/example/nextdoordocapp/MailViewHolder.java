package com.example.nextdoordocapp;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class MailViewHolder extends RecyclerView.ViewHolder{

    TextView mIcon;
    TextView mSender;
    TextView mEmailTitle;
    TextView mEmailDetails;
    TextView mEmailTime;
    RelativeLayout mLayout;
    // ImageView mFavorite;

    MailViewHolder(View itemView) {
        super(itemView);

        mIcon = itemView.findViewById(R.id.tvIcon);
        mSender = itemView.findViewById(R.id.tvEmailSender);
        mEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
        mEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
        mEmailTime = itemView.findViewById(R.id.tvEmailTime);
        mLayout = itemView.findViewById(R.id.layout);
        // mFavorite = itemView.findViewById(R.id.ivFavorite);

    }

}