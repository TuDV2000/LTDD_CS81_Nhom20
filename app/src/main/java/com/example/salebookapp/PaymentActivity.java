package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void BillInsert(int CusID, double total, String dateOfExport){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bill bill = new Bill(CusID,total,dateOfExport);
                AppDatabase.getDatabase(getApplicationContext()).dao().billInsert(bill);
            }
        });
    }
    public void BillDetailInsert(int CusID, int BookID, double Price){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                BillDetail billDetail = new BillDetail(CusID, BookID, Price);
                AppDatabase.getDatabase(getApplicationContext()).dao().billDetailInsert(billDetail);
            }
        });
    }
    public void UpdateQuantitiesOfBook(int BookID){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int quantities = AppDatabase.getDatabase(getApplicationContext()).dao().getBookByID(BookID).getQuantities();
                int newQuantities = quantities - 1;
                AppDatabase.getDatabase(getApplicationContext()).dao().updateQuantitiesOfBook(newQuantities, BookID);
            }
        });
    }

}