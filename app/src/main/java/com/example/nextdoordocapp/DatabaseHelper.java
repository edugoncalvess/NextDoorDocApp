package com.example.nextdoordocapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "NextDoorDocInfo.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Patient_loginHistory";
    final static String TABLE2_NAME = "FoodItem";
    final static String T1COL_1 = "LogId";
    final static String T1COL_2 = "Email";
    final static String T1COL_3 = "loginDate";
    final static String T1COL_4 = "loginStartTime";
    final static String T1COL_5 = "loginEndTime";


    /*  final static String */


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +  TABLE1_NAME + " (" + T1COL_1 + " INTEGER PRIMARY KEY,"
                + T1COL_2 + " TEXT," + T1COL_3 + " TEXT," + T1COL_4 + " TEXT, " + T1COL_5 + " TEXT)";
        db.execSQL(query);
        //String cQuery =
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE1_NAME);
        onCreate(db);

    }
    //add a method
    public boolean addRecord (String email, String date, String sTime , String eTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2,email);
        values.put(T1COL_3,date);
        values.put(T1COL_4,sTime);
        values.put(T1COL_5,eTime);

        long r = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;

    }
//add a method

    public Cursor viewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }

    public boolean deleteRec(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE1_NAME, "id=?", new String[]{Integer.toString(id)});
        if(d>0)
            return  true;
        else
            return false;
    }

/*    public boolean updateRec(int id,String C) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


    }*/
}
