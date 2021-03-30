package com.example.nextdoordocapp;

public class ListData {
    private String mFname;
    private String mLname;
    private String mAddress;

    public ListData(String mFname, String mLname, String mAddress) {
        this.mFname = mFname;
        this.mLname = mLname;
        this.mAddress = mAddress;
    }

    public String getmFname() {
        return mFname;
    }

    public String getmLname() {
        return mLname;
    }

    public String getmAddress() {
        return mAddress;
    }
}

