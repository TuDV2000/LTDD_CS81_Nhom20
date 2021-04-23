package com.example.salebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//print console debug
import android.util.Log;
//

public class AccountService {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = {DBHelper.ACCOUNT_COLUMN_ID, DBHelper.ACCOUNT_COLUMN_USER,
                                    DBHelper.ACCOUNT_COLUMN_PASS, DBHelper.ACCOUNT_COLUMN_TYPEACC};

    public AccountService(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean addAccount(Account account) {
        ContentValues cv = new ContentValues();

        cv.put(allColumns[1], account.getUsername());
        cv.put(allColumns[2], account.getPassword());
        cv.put(allColumns[3], account.getTypeAcc());

        long result = db.insert(DBHelper.TABLE_ACCOUNT, null, cv);

        if (result == -1) {
            Log.d("Add account", "False");
            return false;
        } else {
            Log.d("Add account", "True");
            return true;
        }
    }

//    public boolean deleteAccount(int accountID) {
//
//    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
