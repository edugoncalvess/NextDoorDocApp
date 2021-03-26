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
Patient_leaveMessage_Doctor (PatientEmail, DoctorEmail, Date, Time, Message, Reply, fee)
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
    final static String TABLE3_NAME = "patient";
    final static String TABLE4_NAME = "Payment";
    final static String TABLE5_NAME = "DailyCalories";
    final static String TABLE6_NAME = "Patient_BookAppointment_Doctor";
    final static String TABLE7_NAME = "Patient_leaveMessage_Doctor";


    //Patient_loginHistory table columns
    final static String T1COL_1 = "LogId";
    final static String T1COL_2 = "Email";
    final static String T1COL_3 = "loginDate";
    final static String T1COL_4 = "loginStartTime";
    final static String T1COL_5 = "loginEndTime";

    //FoodItem table columns
    final static String T2COL_1 = "FoodId";
    final static String T2COL_2 = "CalorieAmount";
    final static String T2COL_3 = "FoodName";

    //Patient table columns
    final static String T3COL_1 = "Email";
    final static String T3COL_2 = "FirstName";
    final static String T3COL_3 = "LastName";
    final static String T3COL_4 = "BirthDate";
    final static String T3COL_5 = "Gender";
    final static String T3COL_6 = "Height";
    final static String T3COL_7 = "Weight";
    final static String T3COL_8 = "Phone";
    final static String T3COL_9 = "Country";
    final static String T3COL_10 = "State";
    final static String T3COL_11 = "City";
    final static String T3COL_12 = "Street";
    final static String T3COL_13 = "PostalCode";
    final static String T3COL_14 = "Password";
    final static String T3COL_15 = "InsuranceNumber";
    final static String T3COL_16 = "DiseaseName";
    final static String T3COL_17 = "AllergyName";
    final static String T3COL_18 = "MedicineName";

    //Payment table columns
    final static String T4COL_1 = "PaymentId";
    final static String T4COL_2 = "Email";
    final static String T4COL_3 = "PDate";
    final static String T4COL_4 = "PTime";
    final static String T4COL_5 = "Amount";
    final static String T4COL_6 = "Method";

    //DailyCalories table columns
    final static String T5COL_1 = "DCId";
    final static String T5COL_2 = "Email";
    final static String T5COL_3 = "Amount";
    final static String T5COL_4 = "Date";

    //Patient_BookAppointment_Doctor table columns
    final static String T6COL_1 = "DoctorEmail";
    final static String T6COL_2 = "PatientEmail";
    final static String T6COL_3 = "AppointmentDate";
    final static String T6COL_4 = "AppointmentTime";


    //Patient_leaveMessage_Doctor table columns
    final static String T7COL_1 = "Email";
    final static String T7COL_2 = "DoctorEmail";
    final static String T7COL_3 = "Date";
    final static String T7COL_4 = "Time";
    final static String T7COL_5 = "Message";
    final static String T7COL_6 = "Reply";
    final static String T7COL_7 = "Fee";

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

        //Table Patient
        String patientQuery = "CREATE TABLE " + TABLE3_NAME + " (" + T3COL_1 + " String PRIMARY KEY,"
                + T3COL_2 + " TEXT, " + T3COL_3 + " TEXT, " + T3COL_4 + " TEXT, " + T3COL_5 + " TEXT, "
                + T3COL_6 + " TEXT, " + T3COL_7 + " TEXT, " + T3COL_8 + " TEXT, " + T3COL_9 + " TEXT, "
                + T3COL_10 + " TEXT, " + T3COL_11 + " TEXT, " + T3COL_12 + " TEXT, " + T3COL_13 + " TEXT, "
                + T3COL_14 + " TEXT, " + T3COL_14 + " TEXT, " + T3COL_15 + " TEXT, " + T3COL_16 + " TEXT, "
                + T3COL_17 + " TEXT, " + T3COL_18 + " TEXT)";
        db.execSQL(patientQuery);

        //Table Payment
        String paymentQuery = "CREATE TABLE " + TABLE4_NAME + " (" + T4COL_1 + " INTEGER PRIMARY KEY,"
                + T4COL_2 + " TEXT," + T4COL_3 + " TEXT, " + T4COL_4 + " TEXT, " + T4COL_5 + " INTEGER, "
                + T4COL_6 + " TEXT)";
        db.execSQL(paymentQuery);

        //Table DailyCalories
        String DailyCaloriesQuery = "CREATE TABLE " + TABLE5_NAME + " (" + T5COL_1 + " INTEGER PRIMARY KEY,"
                + T5COL_2 + " TEXT," + T5COL_3 + " TEXT, " + T5COL_4 + " TEXT)";
        db.execSQL(DailyCaloriesQuery);

        //Table Patient_BookAppointment_Doctor
        String Patient_BookAppointment_DoctorQuery = "CREATE TABLE " + TABLE6_NAME + " (" + T6COL_1 + " TEXT,"
                + T6COL_2 + " TEXT," + T6COL_3 + " TEXT, " + T6COL_4 + " TEXT)";
        db.execSQL(Patient_BookAppointment_DoctorQuery);

        //Table Patient_leaveMessage_Doctor
        String Patient_leaveMessage_DoctorQuery = "CREATE TABLE " + TABLE7_NAME + " (" + T7COL_1 + " TEXT,"
                + T7COL_2 + " TEXT," + T7COL_3 + " TEXT, " + T7COL_4 + " TEXT, " + T7COL_5 + " TEXT, "
                + T7COL_6 + " TEXT, " + T7COL_7 + " TEXT)";
        db.execSQL(Patient_leaveMessage_DoctorQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE1_NAME);
        db.execSQL("DROP table if exists " + TABLE2_NAME);
        db.execSQL("DROP table if exists " + TABLE3_NAME);
        db.execSQL("DROP table if exists " + TABLE4_NAME);
        db.execSQL("DROP table if exists " + TABLE5_NAME);
        db.execSQL("DROP table if exists " + TABLE6_NAME);
        db.execSQL("DROP table if exists " + TABLE7_NAME);
        onCreate(db);

    }

    //----------------------------ADD YOUR METHODS HERE -----------------------
    //add  record method for table Patient_loginHistory
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

    //add record method for table FoodItem
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

    //add  record method for table Patient
    public boolean addRecordPatient (String pMail, String pFName, String pLName, String pBD, String pGender,
                                     String pHeight, String pWeight, String pPhone, String pCountry, String pState,
                                     String pCity, String pStreet, String pPostalCode, String pPassword,
                                     String pInsuranceNumber, String pDiseaseName, String pAllergyName,
                                     String pMedicineName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_1,pMail);
        values.put(T3COL_2,pFName);
        values.put(T3COL_3,pLName);
        values.put(T3COL_4,pBD);
        values.put(T3COL_5,pGender);
        values.put(T3COL_6,pHeight);
        values.put(T3COL_7,pWeight);
        values.put(T3COL_8,pPhone);
        values.put(T3COL_9,pCountry);
        values.put(T3COL_10,pState);
        values.put(T3COL_11,pCity);
        values.put(T3COL_12,pStreet);
        values.put(T3COL_13,pPostalCode);
        values.put(T3COL_14,pPassword);
        values.put(T3COL_15,pInsuranceNumber);
        values.put(T3COL_16,pDiseaseName);
        values.put(T3COL_17,pAllergyName);
        values.put(T3COL_18,pMedicineName);


        long r = sqLiteDatabase.insert(TABLE3_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;

    }

    //add record method for table patient payment
    public boolean addRecordPayment (String pEmail, String PayDate, String PayTime, String PayAmount,
                                     String Method){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL_2,pEmail);
        values.put(T4COL_3,PayDate);
        values.put(T4COL_4,PayTime);
        values.put(T4COL_5,PayAmount);
        values.put(T4COL_6,Method);

        long r = sqLiteDatabase.insert(TABLE4_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;
    }
    //add record method for table DailyCalorie
    public boolean addRecordDailyCalorie (String pEmail, String PAmount, String PDate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL_2,pEmail);
        values.put(T5COL_3,PAmount);
        values.put(T5COL_4,PDate);

        long r = sqLiteDatabase.insert(TABLE5_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;
    }

    //add record method for table Patient_BookAppointment_Doctor
    public boolean addRecordPatient_BookAppointment_Doctor (String dEmail, String PEmail, String appDate,
                                                            String appTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T6COL_1,dEmail);
        values.put(T6COL_2,PEmail);
        values.put(T6COL_3,appDate);
        values.put(T6COL_4,appTime);

        long r = sqLiteDatabase.insert(TABLE6_NAME,null,values);
        if(r>0)
            return  true;
        else
            return false;
    }
    //add record method for table Patient_leaveMessage_Doctor
    public boolean addRecordPatient_leaveMessage_Doctor (String pEmail, String dEmail, String messageDate,
                                                            String messageTime, String msg, String Rpl, String msgFee){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T7COL_1,pEmail);
        values.put(T7COL_2,dEmail);
        values.put(T7COL_3,messageDate);
        values.put(T7COL_4,messageTime);
        values.put(T7COL_5,msg);
        values.put(T7COL_6,Rpl);
        values.put(T7COL_7,msgFee);

        long r = sqLiteDatabase.insert(TABLE7_NAME,null,values);
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
