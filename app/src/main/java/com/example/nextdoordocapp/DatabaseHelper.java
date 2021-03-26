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
    //Please look at the tables before creating them
//You can insert "done" before each table in comments to indicate you have created them
    /*Pay attention to
                 -the Primary keys, Foreign keys, Composite keys - check with the main file
                 -name of each entity */
/* Patient (Email, FirstName, LastName, Birthdate, Gender, Height, Weight, Phone, Country, State, City, Street, Postal Code, Password, Insurance Number, DiseasName, AllergyName, MedicineName)
Patient _LoginHistory (LogId, Email, loginDate, loginStartTime,loginEndTime)
Payment (PaymentId, Email, PDate, PTime, Amount, Method)
Daily Calories (DCId, Email, Amount, Date)
FoodItem (FoodId, CaloryAmount, FoodName)
Patient_BookAppointment_Doctor (DoctorEmail, PatientEmail, AppointmentDate, AppointmentTime)
Patient_leaveMessage_Doctor (PatientEmail, DoctorEmail, Date, Time, Message, Reply )
Cashier (Email_ID, FirstName, LastName, Gender, Phone, SIN, Password)
Payment (PaymentId, PDate, Ptime, PMethod, PAmount, AppointmentID)
Doctor (docEmail, first name, last name, password, postal code, phone number, address)
Schedule (ScheduleID, Sdata, Stime, availability, appointment date, appointment time, patient ID)
Messages (MessageID, Mdata, Mtime, description, recommendation)
Doctor_Check_Schedule (docEmail, Schedule ID)
Doctor_Reply_Messages(docEmail, Message ID)
*/
    final static String DATABASE_NAME = "NextDoorDocInfo.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Patient_loginHistory";
    final static String TABLE2_NAME = "FoodItem";
    final static String T1COL_1 = "LogId";
    final static String T1COL_2 = "Email";
    final static String T1COL_3 = "loginDate";
    final static String T1COL_4 = "loginStartTime";
    final static String T1COL_5 = "loginEndTime";

    final static String T2COL_1 = "FoodId";
    final static String T2COL_2 = "CalorieAmount";
    final static String T2COL_3 = "FoodName";

    /*  final static String */

//Database created - no need fo update here
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
//update onCreate for each table you create
//better to name the query after the table so we can distinguish them
    //!!!!!!!!!!!!!!!!!!!!!!!BE CARE FULL ABOUT SPACES!!!!!!!!!!!!!!!!
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Patient_loginHistory
        String query = "CREATE TABLE " +  TABLE1_NAME + " (" + T1COL_1 + " INTEGER PRIMARY KEY,"
                + T1COL_2 + " TEXT," + T1COL_3 + " TEXT," + T1COL_4 + " TEXT, " + T1COL_5 + " TEXT)";
        db.execSQL(query);
        //Table FoodItem
        String foodItemQuery = "CREATE TABLE " + TABLE2_NAME + " (" + T2COL_1 + " INTEGER PRIMARY KEY,"
                + T2COL_2 + " TEXT," + T2COL_3 + " TEXT)";
        db.execSQL(foodItemQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE1_NAME);
        db.execSQL("DROP table if exists " + TABLE2_NAME);
        onCreate(db);

    }

    //----------------------------ADD YOUR METHODS HERE -----------------------
    //add add record method for table Patient_loginHistory
    //add user patient history
    //this method can be used with Toast to make sure our data has been stored in database
    public boolean addRecordPatHist (String email, String date, String sTime , String eTime){
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

    //add add record method for table Patient_loginHistory
    public boolean addRecordFoodItem (String cAmount, String fName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL_2,cAmount);
        values.put(T2COL_3,fName);

        long r = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;

    }

//add cursor method to view data
 /*   public Cursor viewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        return c;
    }*/
//Delete food ID from table Food Item
  /*  public boolean deleteRec(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE2_NAME, "id=?", new String[]{Integer.toString(id)});
        if(d>0)
            return  true;
        else
            return false;
    }*/

/*    public boolean updateRec(int id,String C) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


    }*/
}
