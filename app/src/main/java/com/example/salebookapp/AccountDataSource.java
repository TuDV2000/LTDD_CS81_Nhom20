package com.example.salebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.salebookapp.entities.Account;

import java.util.ArrayList;
import java.util.List;


public class AccountDataSource {
//    private SQLiteDatabase database;
//    private DBHelper dbHelper;
//    private String[] allColumns = {dbHelper.ACCOUNT_COLUMN_ID, dbHelper.ACCOUNT_COLUMN_USER,
//                    dbHelper.ACCOUNT_COLUMN_PASS, dbHelper.ACCOUNT_COLUMN_USER};
//
//    public AccountDataSource(Context context) {
//        dbHelper = new DBHelper(context);
//    }
//    public void openToWrite() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void openToRead() {
//        database = dbHelper.getReadableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }

//    public boolean addAccount(Account account) {
//        ContentValues values = new ContentValues();
//        values.put(dbHelper.ACCOUNT_COLUMN_ID, account.getAccID());
//        values.put(dbHelper.ACCOUNT_COLUMN_USER, account.getUsername());
//        values.put(dbHelper.ACCOUNT_COLUMN_PASS, account.getPassword());
//        values.put(dbHelper.ACCOUNT_COLUMN_TYPEACC, account.getAccType());
//
//        long resultIns = database.insert(dbHelper.TABLE_ACCOUNT, null,
//                values);
//
//        if (resultIns == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public boolean deleteAccount(Account account) {
//        long id = account.getAccID();
//        System.out.println("Comment deleted with id: " + id);
//        long resultDel = database.delete(dbHelper.TABLE_ACCOUNT, dbHelper.ACCOUNT_COLUMN_ID + " = " + id, null);
//
//        if (resultDel == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public List<Account> getAllAccounts() {
//        List<Account> accounts = new ArrayList<Account>();
//
//        Cursor cursor = database.query(dbHelper.TABLE_ACCOUNT,
//                allColumns, null, null, null, null, null);
//
//        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
//            Account account = cursorToAccount(cursor);
//            accounts.add(account);
//        }
//        cursor.close();
//        return accounts;
//    }
//
//    private Account cursorToAccount(Cursor cursor) {
//        Account account = new Account(cursor.getInt(0), cursor.getString(1),
//                cursor.getString(2), cursor.getString(3));
//        return account;
//    }
}
