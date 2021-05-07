package com.example.salebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DBHelper extends SQLiteOpenHelper {
    //Database name
    private static final String DATABASE_NAME = "salebook.db";
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

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String bookname, String publication_date,
                           double price, int republish, int quantity, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO books VALUES (NULL, ?, ?, ?, ?, ?, ?) ";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, bookname);
        statement.bindString(2, publication_date);
        statement.bindDouble(3, price);
        statement.bindLong(4, republish);
        statement.bindLong(5, quantity);
        statement.bindBlob(6, image);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    //Table Customer

    //Another tables
    // ...



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

    // x = image location
    public Boolean insertImage(String x, String bookname, String publication_date,
                               double price, int republish, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream(x);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            ContentValues contentValues = new ContentValues();
            contentValues.put("book_name", bookname);
            contentValues.put("publication_date", publication_date);
            contentValues.put("price", price);
            contentValues.put("republish", republish);
            contentValues.put("quantities", quantity);
            contentValues.put("image", imgbyte);
            db.insert("books", null, contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
