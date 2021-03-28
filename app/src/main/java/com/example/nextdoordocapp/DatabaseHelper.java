package com.example.nextdoordocapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Please look at the tables before creating them
//You can insert "done" before each table in comments to indicate you have created them
    /*Pay attention to
                 -the Primary keys, Foreign keys, Composite keys - check with the main file
                 -name of each entity */
/*  Patient (Email, FirstName, LastName, Birthdate, Gender, Height, Weight, Phone, Country, State, City, Street, Postal Code, Password, Insurance Number, DiseasName, AllergyName, MedicineName)
Patient _LoginHistory (LogId, Email, loginDate, loginStartTime,loginEndTime)
Payment (PaymentId, Email, PDate, PTime, Amount, Method)
Daily Calories (DCId, Email, Amount, Date)
FoodItem (FoodId, CaloryAmount, FoodName)
Patient_BookAppointment_Doctor (DoctorEmail, PatientEmail, AppointmentDate, AppointmentTime)
Patient_leaveMessage_Doctor (MessageID, patientID, docID, Date, Time, Message, Reply)
Cashier (Email_ID, FirstName, LastName, Gender, Phone, SIN, Password)
Payment (PaymentId, PDate, Ptime, PMethod, PAmount, AppointmentID)
Doctor (docID, docEmail, docFName, docLName, docPassword, docPostalCode, docPhoneNumber, docAddress, docCity)
Doctor_Availabilty (docID ,docAvailabiltyID, DocDate, DocStime, DocEtime )

*/
    final static String DATABASE_NAME = "NextDoorDocInfo.db";
    final static int DATABASE_VERSION = 11;
    final static String TABLE1_NAME = "Patient_loginHistory";
    final static String TABLE2_NAME = "FoodItem";
    final static String TABLE3_NAME = "patient";
    final static String TABLE4_NAME = "Payment";
    final static String TABLE5_NAME = "DailyCalories";
    final static String TABLE6_NAME = "Patient_BookAppointment_Doctor";
    final static String TABLE7_NAME = "Patient_leaveMessage_Doctor";

    final static String TABLE8_NAME = "Doctor";
    final static String TABLE9_NAME = "Doctor_Availability";

    final static String TABLE10_NAME = "Login_Table";
    final static String TABLE11_NAME = "Cashier";
    final static String TABLE12_NAME = "Admin";

    //Patient_loginHistory table columns
    final static String T1COL_1 = "LogId";
    final static String T1COL_2 = "patientId";
    final static String T1COL_3 = "loginDate";
    final static String T1COL_4 = "loginStartTime";
    final static String T1COL_5 = "loginEndTime";

    //FoodItem table columns
    final static String T2COL_1 = "FoodId";
    final static String T2COL_2 = "CalorieAmount";
    final static String T2COL_3 = "FoodName";

    //Patient table columns
    final static String T3COL_0 = "PatientId";
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
    final static String T4COL_2 = "patientId";
    final static String T4COL_3 = "PDate";
    final static String T4COL_4 = "PTime";
    final static String T4COL_5 = "Amount";
    final static String T4COL_6 = "Method";

    //DailyCalories table columns
    final static String T5COL_1 = "DCId";
    final static String T5COL_2 = "patientId";
    final static String T5COL_3 = "Amount";
    final static String T5COL_4 = "Date";

    //Patient_BookAppointment_Doctor table columns
    final static String T6COL_1 = "DoctorId";
    final static String T6COL_2 = "patientId";
    final static String T6COL_3 = "AppointmentDate";
    final static String T6COL_4 = "AppointmentTime";


    //Patient_leaveMessage_Doctor table columns
    final static String T7COL_1 = "PatientId";
    final static String T7COL_2 = "DoctorId";
    final static String T7COL_3 = "Date";
    final static String T7COL_4 = "Time";
    final static String T7COL_5 = "Message";
    final static String T7COL_6 = "Reply";
    final static String T7COL_7 = "Fee";

    //Doctor table columns
    final static String T8COL_1 = "docID";
    final static String T8COL_2 = "docEmail";
    final static String T8COL_3 = "docFName";
    final static String T8COL_4 = "docLName";
    final static String T8COL_5 = "docPassword";
    final static String T8COL_6 = "docPostalCode";
    final static String T8COL_7 = "docPhoneNumber";
    final static String T8COL_8 = "docAddress";
    final static String T8COL_9 = "docCity";

    //Doctor_Availability table columns

    final static String T9COL_1 = "docAvailabiltyID";
    final static String T9COL_2 = "docDate";
    final static String T9COL_3 = "docStime";
    final static String T9COL_4 = "docEtime";
    final static String T9COL_5 = "docID";

    //    Login Table
    final static String T10COL_1 = "loginId";
    final static String T10COL_2 = "emailID";
    final static String T10COL_3 = "password";

    //Cashier table columns
    final static String T11COL_1 = "casID";
    final static String T11COL_2 = "casEmail";
    final static String T11COL_3 = "casFName";
    final static String T11COL_4 = "casLName";
    final static String T11COL_5 = "casPassword";
    final static String T11COL_6 = "SIN";
    final static String T11COL_7 = "casPhoneNumber";
    final static String T11COL_8 = "casAddress";
    final static String T11COL_9 = "casDOB";

    //Admin table columns
    final static String T12COL_1 = "admID";
    final static String T12COL_2 = "admEmail";
    final static String T12COL_3 = "admFName";
    final static String T12COL_4 = "admLName";
    final static String T12COL_5 = "admPassword";
    final static String T12COL_6 = "SIN";
    final static String T12COL_7 = "admPhoneNumber";
    final static String T12COL_8 = "admAddress";
    final static String T12COL_9 = "admDOB";


    //Database created - no need fo update here
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //update onCreate for each table you create
//better to name the query after the table so we can distinguish them
    //!!!!!!!!!!!!!!!!!!!!!!!BE CARE FULL ABOUT SPACES!!!!!!!!!!!!!!!!
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Patient_loginHistory
        String query = "CREATE TABLE " + TABLE1_NAME + " (" + T1COL_1 + " INTEGER PRIMARY KEY,"
                + T1COL_2 + " TEXT," + T1COL_3 + " TEXT," + T1COL_4 + " TEXT, " + T1COL_5 + " TEXT)";
        db.execSQL(query);

        //Table FoodItem
        String foodItemQuery = "CREATE TABLE " + TABLE2_NAME + " (" + T2COL_1 + " INTEGER PRIMARY KEY,"
                + T2COL_2 + " TEXT," + T2COL_3 + " TEXT)";
        db.execSQL(foodItemQuery);

        //Table Patient
        String patientQuery = "CREATE TABLE " + TABLE3_NAME + " (" + T3COL_0 + " INTEGER PRIMARY KEY,"
                + T3COL_1 + " TEXT, " + T3COL_2 + " TEXT, " + T3COL_3 + " TEXT, " + T3COL_4 + " TEXT, " + T3COL_5 + " TEXT, "
                + T3COL_6 + " TEXT, " + T3COL_7 + " TEXT, " + T3COL_8 + " TEXT, " + T3COL_9 + " TEXT, "
                + T3COL_10 + " TEXT, " + T3COL_11 + " TEXT, " + T3COL_12 + " TEXT, " + T3COL_13 + " TEXT, "
                + T3COL_14 + " TEXT, " + T3COL_15 + " TEXT, " + T3COL_16 + " TEXT, "
                + T3COL_17 + " TEXT, " + T3COL_18 + " TEXT)";
        db.execSQL(patientQuery);

        //Table Payment
        String paymentQuery = "CREATE TABLE " + TABLE4_NAME + " (" + T4COL_1 + " INTEGER PRIMARY KEY,"
                + T4COL_2 + " TEXT," + T4COL_3 + " TEXT, " + T4COL_4 + " TEXT, " + T4COL_5 + " INTEGER, "
                + T4COL_6 + " TEXT)";
        db.execSQL(paymentQuery);

        //Table DailyCalories
        String DailyCaloriesQuery = "CREATE TABLE " + TABLE5_NAME + " (" + T5COL_1 + " INTEGER PRIMARY KEY,"
                + T5COL_2 + " TEXT," + T5COL_3 + " TEXT, " + T5COL_4 + " TEXT,"
                + " FOREIGN KEY (" + T5COL_2 + ") REFERENCES " + TABLE3_NAME + " (" + T3COL_1 + "));";

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

        //Table Doctor
        String DoctorQuery = "CREATE TABLE " + TABLE8_NAME + " (" + T8COL_1 + " INTEGER PRIMARY KEY,"
                + T8COL_2 + " TEXT," + T8COL_3 + " TEXT," + T8COL_4 + " TEXT," + T8COL_5 + " TEXT,"
                + T8COL_6 + " TEXT," + T8COL_7 + " TEXT," + T8COL_8 + " TEXT," + T8COL_9 + " TEXT)";
        db.execSQL(DoctorQuery);

        String Doctor_AvailabilityQuery = "CREATE TABLE " + TABLE9_NAME + " (" + T9COL_1 + " INTEGER PRIMARY KEY,"
                + T9COL_2 + " TEXT," + T9COL_3 + " TEXT," + T9COL_4 + " TEXT," + T9COL_5 + " TEXT,"
                + " FOREIGN KEY (" + T9COL_5 + ") REFERENCES " + TABLE8_NAME + " (" + T8COL_1 + "));";
        ;
        db.execSQL(Doctor_AvailabilityQuery);

//   Table for LOgin
        String Login_Table = "CREATE TABLE " + TABLE10_NAME + " (" + T10COL_1 + " INTEGER PRIMARY KEY,"
                + T10COL_2 + " TEXT," + T10COL_3 + " TEXT)";
        ;

        db.execSQL(Login_Table);

        //   Table for Cashier
        String CashierQuery = "CREATE TABLE " + TABLE11_NAME + " (" + T11COL_1 + " INTEGER PRIMARY KEY,"
                + T11COL_2 + " TEXT," + T11COL_3 + " TEXT," + T11COL_4 + " TEXT," + T11COL_5 + " TEXT,"
                + T11COL_6 + " TEXT," + T11COL_7 + " TEXT," + T11COL_8 + " TEXT," + T11COL_9 + " TEXT)";

        db.execSQL(CashierQuery);

        //   Table for Admin
        String AdminQuery = "CREATE TABLE " + TABLE12_NAME + " (" + T12COL_1 + " INTEGER PRIMARY KEY,"
                + T12COL_2 + " TEXT," + T12COL_3 + " TEXT," + T12COL_4 + " TEXT," + T12COL_5 + " TEXT,"
                + T12COL_6 + " TEXT," + T12COL_7 + " TEXT," + T12COL_8 + " TEXT," + T12COL_9 + " TEXT)";

        db.execSQL(AdminQuery);
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
        db.execSQL("DROP table if exists " + TABLE8_NAME);
        db.execSQL("DROP table if exists " + TABLE9_NAME);
        db.execSQL("DROP table if exists " + TABLE10_NAME);
        db.execSQL("DROP table if exists " + TABLE11_NAME);
        db.execSQL("DROP table if exists " + TABLE12_NAME);
        onCreate(db);

    }

    //----------------------------ADD YOUR METHODS HERE -----------------------
    //add  record method for table Patient_loginHistory
    //add user patient history
    //this method can be used with Toast to make sure our data has been stored in database
    public boolean addRecordPatHist(String email, String date, String sTime, String eTime) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, email);
        values.put(T1COL_3, date);
        values.put(T1COL_4, sTime);
        values.put(T1COL_5, eTime);

        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }

    //add record method for table FoodItem
    public boolean addRecordFoodItem(String cAmount, String fName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL_2, cAmount);
        values.put(T2COL_3, fName);

        long r = sqLiteDatabase.insert(TABLE2_NAME, null, values);

        if (r > 0)
            return true;
        else
            return false;

    }

    //add  record method for table Patient
    public boolean addRecordPatient(String pMail, String pFName, String pLName, String pBD, String pGender,
                                    String pHeight, String pWeight, String pPhone, String pCountry, String pState,
                                    String pCity, String pStreet, String pPostalCode, String pPassword,
                                    String pInsuranceNumber, String pDiseaseName, String pAllergyName,
                                    String pMedicineName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_1, pMail);
        values.put(T3COL_2, pFName);
        values.put(T3COL_3, pLName);
        values.put(T3COL_4, pBD);
        values.put(T3COL_5, pGender);
        values.put(T3COL_6, pHeight);
        values.put(T3COL_7, pWeight);
        values.put(T3COL_8, pPhone);
        values.put(T3COL_9, pCountry);
        values.put(T3COL_10, pState);
        values.put(T3COL_11, pCity);
        values.put(T3COL_12, pStreet);
        values.put(T3COL_13, pPostalCode);
        values.put(T3COL_14, pPassword);
        values.put(T3COL_15, pInsuranceNumber);
        values.put(T3COL_16, pDiseaseName);
        values.put(T3COL_17, pAllergyName);
        values.put(T3COL_18, pMedicineName);


        long r = sqLiteDatabase.insert(TABLE3_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }

    //add record method for table patient payment
    public boolean addRecordPayment(String pEmail, String PayDate, String PayTime, String PayAmount,
                                    String Method) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL_2, pEmail);
        values.put(T4COL_3, PayDate);
        values.put(T4COL_4, PayTime);
        values.put(T4COL_5, PayAmount);
        values.put(T4COL_6, Method);

        long r = sqLiteDatabase.insert(TABLE4_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;
    }

    //add record method for table DailyCalorie
    public boolean addRecordDailyCalorie(String pEmail, String PAmount, String PDate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL_2, pEmail);
        values.put(T5COL_3, PAmount);
        values.put(T5COL_4, PDate);

        long r = sqLiteDatabase.insert(TABLE5_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;
    }

    //add record method for table Patient_BookAppointment_Doctor
    public boolean addRecordPatient_BookAppointment_Doctor(String dEmail, String PEmail, String appDate,
                                                           String appTime) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T6COL_1, dEmail);
        values.put(T6COL_2, PEmail);
        values.put(T6COL_3, appDate);
        values.put(T6COL_4, appTime);

        long r = sqLiteDatabase.insert(TABLE6_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;
    }

    //add record method for table Patient_leaveMessage_Doctor
    public boolean addRecordPatient_leaveMessage_Doctor(String pEmail, String dEmail, String messageDate,
                                                        String messageTime, String msg, String Rpl, String msgFee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T7COL_1, pEmail);
        values.put(T7COL_2, dEmail);
        values.put(T7COL_3, messageDate);
        values.put(T7COL_4, messageTime);
        values.put(T7COL_5, msg);
        values.put(T7COL_6, Rpl);
        values.put(T7COL_7, msgFee);

        long r = sqLiteDatabase.insert(TABLE7_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;
    }


    //Test if data is adding to patient table
    public boolean addRecordPatientTest() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_1, "nazanin.binesh.nb@gmail.com");
        values.put(T3COL_2, "Nazanin");
        values.put(T3COL_3, "Binesh");
        values.put(T3COL_4, "1988/05/23");
        values.put(T3COL_5, "Female");
        values.put(T3COL_6, "160");
        values.put(T3COL_7, "60");
        values.put(T3COL_8, "778831111");
        values.put(T3COL_9, "Canada");
        values.put(T3COL_10, "BC");
        values.put(T3COL_11, "Coquitlam");
        values.put(T3COL_12, "Eagle mountain");
        values.put(T3COL_13, "V3E2Z2");
        values.put(T3COL_14, "123456");
        values.put(T3COL_15, "");
        values.put(T3COL_16, "");
        values.put(T3COL_17, "");
        values.put(T3COL_18, "");


        long r = sqLiteDatabase.insert(TABLE3_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }


    //Test if data is adding to doctor table
    public boolean addRecordDocTest() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T8COL_1, "1");
        values.put(T8COL_2, "tabannik@gmail.com");
        values.put(T8COL_3, "Taban");
        values.put(T8COL_4, "Nikdel");
        values.put(T8COL_5, "111111");
        values.put(T8COL_6, "V3E2Z2");
        values.put(T8COL_7, "6047150000");
        values.put(T8COL_8, "2576 JadePlace");
        values.put(T8COL_9, "Coquitlam");

        long r = sqLiteDatabase.insert(TABLE8_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }


    //Add record for doctor_Availability
    public boolean addRecordDoctorAvailability(String DocDate, String DocStime, String DocEtime) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T9COL_2, DocDate);
        values.put(T9COL_3, DocStime);
        values.put(T9COL_4, DocEtime);

        long r = sqLiteDatabase.insert(TABLE9_NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;
    }

    //    inserting the login table into the database
    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T10COL_2, email);
        contentValues.put(T10COL_3, password);
        long r = db.insert("Login_Table", null, contentValues);
        if (r == -1)
            return false;
        else
            return true;
    }

    ////    Registering new user Doctor
    public boolean addDoctorRecords(String dMail, String dFName, String dLName,String dPassword,
                                    String dPostalCode,String dPhone, String daddress,String dCity){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(T8COL_2,dMail);
        contentValues.put(T8COL_3,dFName);
        contentValues.put(T8COL_4,dLName);
        contentValues.put(T8COL_5,dPassword);
        contentValues.put(T8COL_6,dPostalCode);
        contentValues.put(T8COL_7,dPhone);
        contentValues.put(T8COL_8,daddress);
        contentValues.put(T8COL_9,dCity);

        long r= sqLiteDatabase.insert(TABLE8_NAME,null,contentValues);
        if (r > 0)
            return true;
        else
            return false;

    }
    ////    Registering new user Cashier
    ////    Registering new user Admin
    //add cursor method to view data
    //View Added FoodItems

    public Cursor viewPatientAddedFoodItems() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String addedFoodItemQuery = "SELECT * FROM " + TABLE2_NAME;
        Cursor c = sqLiteDatabase.rawQuery(addedFoodItemQuery, null);
        return c;
    }
    //View Added Doctor_Availability

    public Cursor viewDoctorAvailability() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String addedDoctorAvailability = "SELECT * FROM " + TABLE9_NAME;
        Cursor c = sqLiteDatabase.rawQuery(addedDoctorAvailability, null);
        return c;
    }

    //checking for the email if that exists
    public Boolean valEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE10_NAME + " where emailID=?",
                new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

//    valdidating emial and password

    public boolean valEmailPassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE10_NAME + " where emailID=? and password=?",
                new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


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
