package com.example.salebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "salebookdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Accounts (id_account INTEGER PRIMARY KEY, username TEXT NOT NULL, password TEXT NOT NULL, loai_acc TEXT NOT NULL)");
        db.execSQL("create Table KhachHang (id_kh INTEGER PRIMARY KEY, ho_ten TEXT NOT NULL, so_dt TEXT NOT NULL, dia_chi TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Accounts");
        db.execSQL("drop Table if exists KhachHang");
    }

    public Boolean addAccount(String username, String password, String loai_acc) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("loai_acc", loai_acc);

        long result = DB.insert("Accounts", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean addKhachHang(String hoTen, String soDT, String diaChi) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ho_ten", hoTen);
        contentValues.put("so_dt", soDT);
        contentValues.put("dia_chi", diaChi);

        long result = DB.insert("KhachHang", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
