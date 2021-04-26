package com.example.salebookapp.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//print console debug
import android.util.Log;

import com.example.salebookapp.DBHelper;
import com.example.salebookapp.entities.Accounts;
//

public class AccountService {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private String[] allColumns = {DBHelper.ACCOUNT_COLUMN_ID, DBHelper.ACCOUNT_COLUMN_USER,
                                    DBHelper.ACCOUNT_COLUMN_PASS, DBHelper.ACCOUNT_COLUMN_TYPEACC};

    public AccountService(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean addAccount(Accounts account) {
        open();
        ContentValues cv = new ContentValues();

        cv.put(allColumns[1], account.getUsername());
        cv.put(allColumns[2], account.getPassword());
        cv.put(allColumns[3], account.getTypeAcc());

        long result = db.insert(DBHelper.TABLE_ACCOUNT, null, cv);
        close();

        if (result == -1) {
            Log.d("Add account", "False");
            return false;
        } else {
            Log.d("Add account", "True");
            return true;
        }
    }

    public Cursor getAccount(String[] username) {
        Cursor c = db.rawQuery("select * from account where ?", username);

        return c;
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
