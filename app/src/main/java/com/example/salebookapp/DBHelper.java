package com.example.salebookapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    //Database name
    private static final String DATABASE_NAME = "salebook";
    private static final int DATABASE_VERSION = 1;

    //Table Account
    public static final String TABLE_ACCOUNT = "accounts";
    public static final String ACCOUNT_COLUMN_ID = "acc_id";
    public static final String ACCOUNT_COLUMN_USER = "username";
    public static final String ACCOUNT_COLUMN_PASS = "password";
    public static final String ACCOUNT_COLUMN_TYPEACC = "acc_type";
    private final String CREATE_TABLE_ACC = "create table " + TABLE_ACCOUNT + "(" + ACCOUNT_COLUMN_ID + " integer primary key autoincrement, "
                                            + ACCOUNT_COLUMN_USER + " text not null, "
                                            + ACCOUNT_COLUMN_PASS + " text not null, "
                                            + ACCOUNT_COLUMN_TYPEACC + " text not null);";
    //Another tables
    // ...

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table account
        db.execSQL(CREATE_TABLE_ACC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        onCreate(db);
    }
}
